package ru.shunt.wowstat.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WowClass {
	// @formatter:off
	WARRIOR(1, "Warrior", "#c69b6d", "images/classes/warrior.jpg"),
	PALADIN(2, "Paladin", "#f48cba", "images/classes/paladin.jpg"),
	HUNTER(3, "Hunter", "#aad372", "images/classes/hunter.jpg"),
	ROGUE(4, "Rogue", "#fff468", "images/classes/rogue.jpg"),
	PRIEST(5, "Priest", "#f0ebe0", "images/classes/priest.jpg"),
	DEATHKNIGHT(6, "Death Knight", "#c41e3b", "images/classes/dk.jpg"),
	SHAMAN(7, "Shaman", "#2359ff", "images/classes/shaman.jpg"),
	MAGE(8, "Mage", "#68ccef", "images/classes/mage.jpg"),
	WARLOCK(9, "Warlock", "#9382c9", "images/classes/warlock.jpg"),
	MONK(10, "Monk", "#00ffba", "images/classes/monk.jpg"),
	DRUID(11, "Druid", "#ff7c0a", "images/classes/druid.jpg"),
	DEMONHUNTER(12, "Demon Hunter", "#a330c9", "images/classes/dh.jpg");

	// @formatter:on
	private final int classID;
	private final String className;
	private final String color;
	private final String url;

	private static final Map<Integer, WowClass> raceIdToValueMap = new HashMap<Integer, WowClass>();

	static {
		for (WowClass value : EnumSet.allOf(WowClass.class)) {
			raceIdToValueMap.put(value.classID, value);
		}

	}

	public static WowClass forId(int id) {
		return raceIdToValueMap.get(id);
	}

	WowClass(int classID, String className, String color, String url) {
		this.classID = classID;
		this.className = className;
		this.color = color;
		this.url = url;
	}

	public int getClassID() {
		return classID;
	}

	public String getUrl() {
		return url;
	}

	public String getClassName() {
		return className;
	}

	public String getColor() {
		return color;
	}

	public static List<String> getClassesList() {
		List<String> returnList = new ArrayList<String>();
		for (WowClass value : EnumSet.allOf(WowClass.class)) {
			returnList.add(value.className);
		}
		return returnList;
	}
}
