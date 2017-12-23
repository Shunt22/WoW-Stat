package ru.shunt.wowstat.parse;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import ru.shunt.wowstat.database.Talents;
import ru.shunt.wowstat.database.TalentsRepository;
import ru.shunt.wowstat.enums.WowSpec;
import ru.shunt.wowstat.pojo.WowCharacter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JsonWorker {

	private class CharactersInfoStorage {
		@SerializedName("talents")
		private ArrayList<Talents> talentsStorage;

		private class Talents {
			@SerializedName("talents")
			private ArrayList<TalentInfo> talentsInfo;
			private SpecInfo spec;

			private class SpecInfo {
				private String name;
			}

			private class TalentInfo {
				private Spell spell;

				private class Spell {
					private int id;
				}
			}
		}
	}

	public void parseCharacter(WowCharacter character, InputStream characterJson, TalentsRepository talentsRepository, String bracket) throws IOException {

		boolean parsed = false;
		long specId = character.getSpecId();
		WowSpec spec = WowSpec.forID((int) specId);
		String specName = spec.getSpecName();

		Gson gson = new Gson();

		JsonReader reader = new JsonReader(new InputStreamReader(characterJson, "UTF-8"));

		CharactersInfoStorage storage = gson.fromJson(reader, CharactersInfoStorage.class);

		reader.close();

		for (int i = 0; i < storage.talentsStorage.size(); ++i) {

			ru.shunt.wowstat.parse.JsonWorker.CharactersInfoStorage.Talents talents = storage.talentsStorage.get(i);

			ru.shunt.wowstat.parse.JsonWorker.CharactersInfoStorage.Talents.SpecInfo specInfo = talents.spec;
			if (specInfo == null || !specName.equals(specInfo.name) || parsed) {
				continue;
			}

			parsed = true;

			for (int j = 0; j < talents.talentsInfo.size(); ++j) {

				ru.shunt.wowstat.parse.JsonWorker.CharactersInfoStorage.Talents.TalentInfo talentInfo = talents.talentsInfo
						.get(j);

				Talents tal = talentsRepository.findByBracketAndSpecIdAndTalentId(bracket, spec.getSpecID(), talentInfo.spell.id);
				if (tal == null) {
					talentsRepository.save(new Talents(spec.getSpecID(), talentInfo.spell.id, 1, bracket));
				} else {
					tal.increasePlayersAmount();
					talentsRepository.save(tal);
				}

			}

		}

	}
}
