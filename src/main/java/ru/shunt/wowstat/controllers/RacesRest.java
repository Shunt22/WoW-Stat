package ru.shunt.wowstat.controllers;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shunt.wowstat.database.RacesRepository;
import ru.shunt.wowstat.enums.WowRace;
import ru.shunt.wowstat.parse.HttpReader.Brackets;
import ru.shunt.wowstat.pojo.RacesDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class RacesRest {

	private RacesRepository rRep;

	@Autowired
	public RacesRest(RacesRepository rRep) {
		this.rRep=rRep;
	}

	@RequestMapping("/racesRest")
	public List<RacesDto> getLeaders(
			@RequestParam(value = "bracket", required = true, defaultValue = "3v3") String bracket)
			throws ClassNotFoundException, IOException {

		Brackets selectedBracket = Brackets.forName(bracket);


		Long totalPlayersAmount = 1L;
		try {
			totalPlayersAmount = rRep.getTotalNumberOfPlayers(selectedBracket.toString());
		} catch (AopInvocationException | NullPointerException e) {
			totalPlayersAmount = 1L;
		}

		List<RacesDto> pojoList = new ArrayList<RacesDto>();

		for (WowRace race : WowRace.values()) {
			long playersAmount = 0;
			try {
				playersAmount = rRep.findByRaceIdAndBracketName(race.getRaceId(), selectedBracket.toString()).getPlayersAmount();
			} catch (AopInvocationException | NullPointerException e) {
				continue;
			}

			pojoList.add(new RacesDto(race.getRaceName(), new BigDecimal((double) playersAmount / totalPlayersAmount).setScale(4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).doubleValue(), playersAmount, race.getFaction().getFactionName(), race.getUrl()));

		}


		Collections.sort(pojoList, Comparator.comparing(RacesDto::getFaction));

		return pojoList;
	}
}
