package ru.shunt.wowstat.parse;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.shunt.wowstat.database.ParsingRepository;
import ru.shunt.wowstat.database.RacesRepository;
import ru.shunt.wowstat.database.TalentsRepository;
import ru.shunt.wowstat.database.SpecRepository;
import ru.shunt.wowstat.parse.HttpReader.Brackets;
import ru.shunt.wowstat.pojo.WowCharacter;

@Service
public class ParseWorker {
    private TalentsRepository characterRepository;
    private SpecRepository specRep;
    private ParsingRepository parsingRepository;
    private RacesRepository racesRepository;
    private boolean parsingStarted = false;

    @Autowired
    public ParseWorker(TalentsRepository cRep, SpecRepository sRep, ParsingRepository pRep,RacesRepository rRep) {
        this.racesRepository=rRep;
        this.characterRepository = cRep;
        this.specRep = sRep;
        this.parsingRepository = pRep;
    }

    public synchronized void start() {
        if (!parsingStarted) {
            parsingStarted = true;

            characterRepository.deleteAll();
            specRep.deleteAll();

            runThreads();
        }
    }

    private synchronized void stop() {
        parsingStarted = false;
        System.out.println("Parsing has ended");
    }

    private void runThreads() {
        // EU HOST
        Parse twoVsTwo = new Parse(Brackets.TwoVTwo, HttpReader.EU_HOST, characterRepository, specRep, parsingRepository,racesRepository);

        Parse threeVsThree = new Parse(Brackets.ThreeVThree, HttpReader.EU_HOST, characterRepository, specRep, parsingRepository,racesRepository);

        Parse rbg = new Parse(Brackets.RBG, HttpReader.EU_HOST, characterRepository, specRep, parsingRepository,racesRepository);

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

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    twoVsTwoThread.join();
                    threeVsThreeThread.join();
                    rbgThread.join();

                } catch (InterruptedException e) {
                    System.out.println("Some error with parsing threads: " + e);
                } finally {
                    stop();
                }
            }
        }).start();

    }

    private class Parse implements Runnable {
        private Brackets bracket;
        private String host;
        private TalentsRepository charaterRepository;
        private SpecRepository specsRep;
        private ParsingRepository pRep;
        private RacesRepository rRep;

        Parse(Brackets bracket, String host, TalentsRepository charaterRepository, SpecRepository specsRep, ParsingRepository pRep, RacesRepository rRep) {
            this.bracket = bracket;
            this.host = host;
            this.charaterRepository = charaterRepository;
            this.specsRep = specsRep;
            this.pRep = pRep;
            this.rRep=rRep;
        }

        @Override
        public void run() {
            HttpReader httpReader = new HttpReader(bracket, host, charaterRepository, specsRep, pRep,rRep);
            System.out.println("New parsing ( " + bracket.toString() + " " + host + ") " + "(" + Instant.now()
                    + ") started");
            try {
                ArrayList<WowCharacter> charactersNames = httpReader.readLeaderBoard();
                httpReader.readCharacters(charactersNames);

            } catch (IOException e) {
                System.out.println("Error in: " + this + " while parsing" + e); // For debug
            }

        }

        @Override
        public String toString() {
            return "Parsing thread: " + bracket + " " + host;

        }

    }

}
