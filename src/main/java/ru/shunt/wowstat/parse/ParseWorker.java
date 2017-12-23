package ru.shunt.wowstat.parse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shunt.wowstat.database.ParsingRepository;
import ru.shunt.wowstat.database.RacesRepository;
import ru.shunt.wowstat.database.SpecRepository;
import ru.shunt.wowstat.database.TalentsRepository;
import ru.shunt.wowstat.parse.HttpReader.Brackets;
import ru.shunt.wowstat.pojo.WowCharacter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
public class ParseWorker {
	private TalentsRepository talentsRepository;
	private SpecRepository specRep;
	private ParsingRepository parsingRepository;
	private RacesRepository racesRepository;
	private boolean parsingStarted = false;

	@Autowired
	public ParseWorker(TalentsRepository talentsRepository, SpecRepository specRepository, ParsingRepository parsingRepository, RacesRepository racesRepository) {
		this.racesRepository = racesRepository;
		this.talentsRepository = talentsRepository;
		this.specRep = specRepository;
		this.parsingRepository = parsingRepository;
	}

	public synchronized void start() {
		if (!parsingStarted) {
			parsingStarted = true;

			talentsRepository.deleteAll();
			specRep.deleteAll();
			racesRepository.deleteAll();

			runThreads();
		}
	}

	private synchronized void stop() {
		parsingStarted = false;
		log.info("Parsing has been ended at " + LocalDateTime.now());
	}

	private void runThreads() {
		// EU HOST
		Parse twoVsTwo = new Parse(Brackets.TwoVTwo, HttpReader.EU_HOST);

		Parse threeVsThree = new Parse(Brackets.ThreeVThree, HttpReader.EU_HOST);

		Parse rbg = new Parse(Brackets.RBG, HttpReader.EU_HOST);

		final Thread twoVsTwoThread = new Thread(twoVsTwo);
		final Thread threeVsThreeThread = new Thread(threeVsThree);
		final Thread rbgThread = new Thread(rbg);

		twoVsTwoThread.setName("2v2 parsing thread");
		threeVsThreeThread.setName("3v3 parsing thread");
		rbgThread.setName("RBG parsing thread");

		// US HOST

		// TODO

		// starting
		twoVsTwoThread.start();
		threeVsThreeThread.start();
		rbgThread.start();

		new Thread(() -> {
			try {
				twoVsTwoThread.join();
				threeVsThreeThread.join();
				rbgThread.join();

			} catch (InterruptedException e) {
				log.error("Some error with parsing threads: " + e);
			} finally {
				stop();
			}
		}).start();

	}

	@AllArgsConstructor
	private class Parse implements Runnable {
		private Brackets bracket;
		private String host;

		@Override
		public void run() {
			HttpReader httpReader = new HttpReader(bracket, host, talentsRepository, specRep, parsingRepository, racesRepository);
			log.info("New parsing (" + bracket.toString() + " " + host + ") has been started at " + LocalDateTime.now());
			try {
				ArrayList<WowCharacter> charactersNames = httpReader.readLeaderBoard();
				httpReader.readCharacters(charactersNames);

			} catch (IOException e) {
				log.error("Error in: " + this + " while parsing" + e);
			}

		}

		@Override
		public String toString() {
			return "Parsing thread: " + bracket + " " + host;

		}

	}

}
