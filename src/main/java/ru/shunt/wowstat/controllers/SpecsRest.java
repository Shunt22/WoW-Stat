package ru.shunt.wowstat.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.shunt.wowstat.database.SpecRepository;
import ru.shunt.wowstat.enums.WowSpec;
import ru.shunt.wowstat.parse.HttpReader.Brackets;
import ru.shunt.wowstat.pojo.SpecsDto;

@RestController
public class SpecsRest {

    private SpecRepository specsRep;

    @Autowired
    public SpecsRest(SpecRepository sRep) {
        this.specsRep = sRep;
    }

    @RequestMapping("/specsRest")
    public List<SpecsDto> getSpecs(
            @RequestParam(value = "bracket", required = true, defaultValue = "3v3") String bracket)
            throws ClassNotFoundException, IOException {

        Brackets selectedBracket = Brackets.forName(bracket);

        Long totalPlayersAmount = 1L;
        try {
            totalPlayersAmount = specsRep.getTotalNumberOfPlayers(selectedBracket.toString());
        } catch (AopInvocationException | NullPointerException e) {
            totalPlayersAmount = 1L;
        }

        List<SpecsDto> pojoList = new ArrayList<SpecsDto>();

        for (WowSpec spec : WowSpec.values()) {
            long playersAmount = 0;
            try {
                playersAmount = specsRep.findBySpecIdAndBracketName(spec.getSpecID(), selectedBracket.toString()).getPlayersAmount();
            } catch (AopInvocationException | NullPointerException e) {
                continue;
            }
            pojoList.add(
                    new SpecsDto(
                            spec.getClassSpecName(), new BigDecimal((double) playersAmount / totalPlayersAmount).setScale(4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).doubleValue(),
                            playersAmount, spec.getUrl(), spec.getClazz().getColor()));
        }

        return pojoList;
    }
}
