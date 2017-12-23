package ru.shunt.wowstat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.shunt.wowstat.enums.WowClass;
import ru.shunt.wowstat.parse.HttpReader.Brackets;

@Controller
public class SpecAndRaces {

	@RequestMapping("/races-and-specs")
	public String getLeaders(Model model) {
		model.addAttribute("classes", WowClass.getClassesList());
		model.addAttribute("brackets", Brackets.values());
		return "specandrace";
	}
}
