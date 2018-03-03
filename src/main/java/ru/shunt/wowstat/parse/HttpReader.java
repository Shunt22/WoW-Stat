package ru.shunt.wowstat.parse;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import ru.shunt.wowstat.config.Config;
import ru.shunt.wowstat.database.*;
import ru.shunt.wowstat.pojo.WowCharacter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.*;

@Slf4j
public class HttpReader {

	public static final String EU_HOST = "eu.api.battle.net";

	public static final String US_HOST = "us.api.battle.net";

	private static final String API_URL_LEADERBORARD = "/wow/leaderboard/";

	private static final String API_URL_CHARACTER = "/wow/character/";

	private JsonWorker jsonWorker = new JsonWorker();

	public enum Brackets {
		TwoVTwo("2v2"), ThreeVThree("3v3"), RBG("rbg");

		private static final Map<String, Brackets> stringToBracketMap = new HashMap<String, Brackets>();

		static {
			for (Brackets value : EnumSet.allOf(Brackets.class)) {
				stringToBracketMap.put(value.toString(), value);
			}
		}

		public static Brackets forName(String name) {
			return stringToBracketMap.get(name);
		}

		private final String text;

		Brackets(String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	private class CharacterStorage {
		private ArrayList<WowCharacter> rows;

	}

	private Brackets bracket;
	private String host;
	private final String API_KEY;
	private TalentsRepository talentsRepository;
	private SpecRepository specRepository;
	private ParsingRepository parsingRepository;
	private RacesRepository racesRepository;

	public HttpReader(Brackets bracket, String host, TalentsRepository talentsRepository, SpecRepository specRepository, ParsingRepository parsingRepository, RacesRepository racesRepository) {
		this.bracket = bracket;
		this.host = host;
		this.API_KEY = Config.getInstance().getApiKey();
		this.talentsRepository = talentsRepository;
		this.specRepository = specRepository;
		this.parsingRepository = parsingRepository;
		this.racesRepository = racesRepository;

	}

	public ArrayList<WowCharacter> readLeaderBoard() throws IOException {

		String url = "https://" + host + API_URL_LEADERBORARD + bracket.toString() + "?apikey=" + API_KEY;

		Gson gson = new Gson();

		JsonReader reader = new JsonReader(new InputStreamReader(checkForJson(url), "UTF-8"));

		CharacterStorage storage = gson.fromJson(reader, CharacterStorage.class);

		reader.close();

		return storage.rows;
		//return new ArrayList<WowCharacter>(storage.rows.subList(0, 100)); // For local server testing

	}

	private InputStream checkForJson(String url) throws IOException {

		for (int errCounter = 0; errCounter < 3; errCounter++) {

			HttpEntity response = Request.Get(url).execute().returnResponse().getEntity();

			if (!ContentType.get(response).getMimeType().equals(ContentType.APPLICATION_JSON.getMimeType())) {
				System.out.println("wrong content-type");
				continue;
			}

			return response.getContent();

		}
		return null; // TODO FIX THIS
	}


	public void readCharacters(List<WowCharacter> charactersList) throws IOException {

		for (WowCharacter character : charactersList) {
			if (character.getRating() < 2500) {
				continue;
			}
			try {

				String url = "https://" + host + API_URL_CHARACTER
						+ URLEncoder.encode(character.getRealmSlug(), "UTF-8").replace("+", "%20") + "/"
						+ URLEncoder.encode(character.getName(), "UTF-8").replace("+", "%20") + "?fields=talents,items"
						+ "&apikey=" + API_KEY;

				InputStream characterJson = checkForJson(url);
				// If characterJson is null, there is something wrong with talents api right now,
				// but, anyway, this character is in leader board, so we only add race and spec info
				if (characterJson != null) {
					try {
						jsonWorker.parseCharacter(character, characterJson, talentsRepository, bracket.toString());
						log.info("Character " + character.getName() + "-" + character.getRealmSlug() + " has been parsed!");
					} catch (NullPointerException e) {
						/*
						 * Something wrong with this character
						 * Usually it happens when character was transferee to other realm
						 */
					}

				}


				/*
				 * Adding spec info
				 */
				Specialization spec = specRepository.findBySpecIdAndBracketName(character.getSpecId(), bracket.toString());
				if (spec == null) {
					specRepository.save((new Specialization(character.getSpecId(), 1, bracket.toString())));
				} else {
					spec.increasePlayersAmount();
					specRepository.save(spec);

				}

				/*
				 * Adding race info
				 */
				Races race = racesRepository.findByRaceIdAndBracketName(character.getRaceId(), bracket.toString());
				if (race == null) {
					racesRepository.save(new Races(character.getRaceId(), 1, bracket.toString()));
				} else {
					race.increasePlayersAmount();
					racesRepository.save(race);
				}


				/*
				 * Okay, character has been fully parsed!
				 */

			} catch (NullPointerException e) {
				continue;
			}
		}

		parsingRepository.save(new ParsingInfo(1, Instant.now()));

	}
}
