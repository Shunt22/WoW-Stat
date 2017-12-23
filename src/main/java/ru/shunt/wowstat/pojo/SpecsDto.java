package ru.shunt.wowstat.pojo;

public class SpecsDto {
	private final String name;
	private final Double percent;
	private final long amount;
	private final String url;
	private final String color;

	public SpecsDto(String name, Double percent, long amount, String url, String color) {
		this.name = name;
		this.percent = percent;
		this.amount = amount;
		this.url = url;
		this.color = color;
	}

	public String getColor() {
		return color;
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

	public String getUrl() {
		return url;
	}

}
