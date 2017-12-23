package ru.shunt.wowstat.pojo;


public class RacesDto {
	private final String name;
	private final Double percent;
	private final long amount;
	private final String faction;
	private final String url;

	public RacesDto(String name, Double percent, long amount, String faction, String url) {
		this.name = name;
		this.percent = percent;
		this.amount = amount;
		this.faction = faction;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public Double getPercent() {
		return percent;
	}

	public long getAmount() {
		return amount;
	}

	public String getFaction() {
		return faction;
	}

	public String getUrl() {
		return url;
	}
}
