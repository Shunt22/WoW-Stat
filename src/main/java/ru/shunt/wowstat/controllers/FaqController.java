package ru.shunt.wowstat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class FaqController {

	private final static Map<String, String> faqMap = new LinkedHashMap<String, String>();
	static {
		faqMap.put("Where am I?", "This website collects statistic from WoW PvP ladder.");
		faqMap.put("What things can I find here?",
				"So far there are statistics for talents, races and specs from 2400 rating and above. ");
		faqMap.put("From PvP? What ladder?", "2v2, 3v3 and RBG ladders. All from EU host so far.");
		faqMap.put("How often does statistic update?", "It updates every 24 hours.");
		faqMap.put(
				"Is this an open-source project?",
				"Yes. You can watch source code on Github. <a href=\"https://github.com/Shunt22/WoW-Stat\">Here</a>. Feel free to laugh,comment and critisize the code:)");
		faqMap.put("How can i message you?", "My email is on the bottom part of the page.");
	}

	@RequestMapping("/faq")
	public String returnFaq(Model model) {
		model.addAttribute("q", faqMap);
		return "faq";

	}
}
