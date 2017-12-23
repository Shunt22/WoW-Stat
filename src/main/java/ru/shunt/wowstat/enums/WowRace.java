package ru.shunt.wowstat.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum WowRace {
	// @formatter:off
	HUMAN(1, WowFaction.ALLIANCE, "Human", "images/races/human_m.jpg"),
	ORC(2, WowFaction.HORDE, "Orc", "images/races/orc_m.jpg"),
	DWARF(3, WowFaction.ALLIANCE, "Dwarf", "images/races/dwarf_f.jpg"),
	NIGHT_ELF(4, WowFaction.ALLIANCE, "Night Elf", "images/races/night_elf_m.jpg"),
	UNDEAD(5, WowFaction.HORDE, "Undead", "images/races/undead_m.jpg"),
	TAUREN(6, WowFaction.HORDE, "Tauren", "images/races/tauren_m.jpg"),
	GNOME(7, WowFaction.ALLIANCE, "Gnome", "images/races/gnome_m.jpg"),
	TROLL(8, WowFaction.HORDE, "Troll", "images/races/troll_m.jpg"),
	GOBLIN(9, WowFaction.HORDE, "Goblin", "images/races/goblin_m.jpg"),
	BLOOD_ELF(10, WowFaction.HORDE, "Blood elf", "images/races/blood_elf_m.jpg"),
	DRAENEI(11, WowFaction.ALLIANCE, "Draenei", "images/races/draenei_m.jpg"),
	WORGEN(22, WowFaction.ALLIANCE, "Worgen", "images/races/worgen_f.jpg"),
	PANDAREN_NEUTRAL(24, WowFaction.NEUTRAL, "Pandaren\n(Neutral)", "images/races/pandaren_m.jpg"),
	PANDAREN_ALLIANCE(25, WowFaction.ALLIANCE, "Pandaren\n(Alliance)", "images/races/pandaren_m.jpg"),
	PANDAREN_HORDE(26, WowFaction.HORDE, "Pandaren\n(Horde)", "images/races/pandaren_f.jpg");
	// @formatter:on
	/*
	 * icons for pandaren horde and pandaren alliance MUST BE different(for chart)
	 */
	private final int raceId;
	private final WowFaction faction;
	private final String raceName;
	private final String url;
	private static final Map<Integer, WowRace> raceIdToValueMap = new HashMap<Integer, WowRace>();

	static {
		for (WowRace value : EnumSet.allOf(WowRace.class)) {
			raceIdToValueMap.put(value.raceId, value);
		}

	}

	@Override
	public String toString() {
		return raceName;
	}

	public static WowRace forId(int id) {
		return raceIdToValueMap.get(id);
	}

	public int getRaceId() {
		return raceId;
	}

	public String getRaceName() {
		return raceName;
	}

	public WowFaction getFaction() {
		return faction;
	}

	public String getUrl() {
		return url;
	}

	WowRace(int raceId, WowFaction faction, String raceName, String url) {
		this.raceId = raceId;
		this.faction = faction;
		this.raceName = raceName;
		this.url = url;
	}
}
