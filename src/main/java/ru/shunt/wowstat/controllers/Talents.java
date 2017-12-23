package ru.shunt.wowstat.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.shunt.wowstat.database.ParsingInfo;
import ru.shunt.wowstat.database.ParsingRepository;
import ru.shunt.wowstat.database.TalentsRepository;
import ru.shunt.wowstat.database.SpecRepository;
import ru.shunt.wowstat.enums.WowClass;
import ru.shunt.wowstat.enums.WowSpec;
import ru.shunt.wowstat.parse.HttpReader.Brackets;
import ru.shunt.wowstat.parse.ParseWorker;
import ru.shunt.wowstat.pojo.SpecVm;

@Controller
public class Talents {

    private static final int DAYS_TO_PARSE = 1;

    private ParseWorker pw;
    private TalentsRepository characterRepository;
    private SpecRepository specsRep;
    private ParsingRepository parsingRepository;

    @Autowired
    public Talents(ParseWorker pw, TalentsRepository cRep, SpecRepository sRep, ParsingRepository pRep) {
        this.pw = pw;
        this.characterRepository = cRep;
        this.specsRep = sRep;
        this.parsingRepository = pRep;

    }

    @RequestMapping("/talents")
    public String showTalents(@RequestParam(value = "bracket", required = true, defaultValue = "3v3") String bracket,
                              @RequestParam(value = "class", required = true, defaultValue = "1") String classId, Model model) {

        Brackets selectedBracket = Brackets.forName(bracket);
        if (selectedBracket == null) {
            selectedBracket = Brackets.ThreeVThree;
        }

        ParsingInfo pInfo = parsingRepository.findById(1);
        if (pInfo == null) {
            pw.start();
            return "parsing";
        } else {
            Instant dateToParse = pInfo.getDate().plus(DAYS_TO_PARSE, ChronoUnit.DAYS);
            Duration d = Duration.between(Instant.now(), dateToParse);
            long days = ChronoUnit.DAYS.between(pInfo.getDate(),Instant.now());
            if (days >= DAYS_TO_PARSE) {
                pw.start();
                model.addAttribute("eta", "Parsing right now!");
            } else {
                model.addAttribute("eta", d.toHours() + " hours "+d.minusHours(d.toHours()).toMinutes()+" minutes");
            }
        }

        WowClass clazz = WowClass.forId(Integer.valueOf(classId));

        List<WowSpec> specsAccordingToClassList = new ArrayList<WowSpec>();
        Map<SpecVm, List<List<Map<Integer, Double>>>> specToTalentsMap = new LinkedHashMap<SpecVm, List<List<Map<Integer, Double>>>>();
        for (WowSpec spec : EnumSet.allOf(WowSpec.class)) {

            if (spec.getClazz().equals(clazz)) {

                specsAccordingToClassList.add(spec);

                Long thisSpecPlayersAmount = 1L;
                try {
                    thisSpecPlayersAmount = specsRep.findBySpecIdAndBracketName(spec.getSpecID(), selectedBracket.toString())
                            .getPlayersAmount();
                } catch (AopInvocationException | NullPointerException e) {
                    thisSpecPlayersAmount = 0L;
                }




                specToTalentsMap.put(new SpecVm(spec.getSpecID(), thisSpecPlayersAmount),
                        getModelList(spec, thisSpecPlayersAmount, selectedBracket));
            }
        }

        long total=0;
        try {
            total = specsRep.getTotalNumberOfPlayers(selectedBracket.toString());
        }catch (AopInvocationException | NullPointerException e){
            total = 0;
        }
        model.addAttribute("classes", WowClass.values()); // For Drop-Down menu
        model.addAttribute("specs", specsAccordingToClassList);
        model.addAttribute("totalNum",total);
        model.addAttribute("date", pInfo.getDate().toEpochMilli());
        model.addAttribute("talents", specToTalentsMap);
        model.addAttribute("url", selectedBracket.toString());

        return "talents";
    }

    private List<List<Map<Integer, Double>>> getModelList(WowSpec spec, Long thisSpecPlayersAmount, Brackets selectedBracket) {
        List<List<Map<Integer, Double>>> modelList = new ArrayList<List<Map<Integer, Double>>>();

        // To prevent division by zero
        if (thisSpecPlayersAmount == null || thisSpecPlayersAmount == 0) {
            thisSpecPlayersAmount = 1L;
        }


        for (List<Integer> listEntry : spec.getDefaultMap()) {
            List<Map<Integer, Double>> mapList = new ArrayList<Map<Integer, Double>>();

            for (Integer secondListEntry : listEntry) {
                Map<Integer, Double> talMap = new HashMap<Integer, Double>();

                Integer value;
                try {
                    value = (int) characterRepository.findByBracketAndSpecIdAndTalentId(selectedBracket.toString(), spec.getSpecID(),
                            secondListEntry).getPlayersAmount();
                } catch (AopInvocationException | NullPointerException e) {
                    value = 0;
                }
                if (value == null) {
                    value = 0;
                }
                talMap.put(secondListEntry,
                        new BigDecimal((double) value / thisSpecPlayersAmount).setScale(4, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal(100)).doubleValue());

                mapList.add(talMap);

            }
            modelList.add(mapList);
        }

        return modelList;
    }

}
