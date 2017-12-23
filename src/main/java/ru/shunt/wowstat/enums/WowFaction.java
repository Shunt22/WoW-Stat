package ru.shunt.wowstat.enums;

public enum WowFaction {
	HORDE("Horde"), ALLIANCE("Alliance"), NEUTRAL("Neutral");

	private final String name;

	WowFaction(String name) {
		this.name = name;
	}

	public String getFactionName() {
		return name;
	}
}
