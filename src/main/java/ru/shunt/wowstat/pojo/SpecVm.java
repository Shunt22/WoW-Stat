package ru.shunt.wowstat.pojo;

public class SpecVm {
	private final int id;
	private final long num;

	public SpecVm(int id, long num) {
		this.id = id;
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public long getNum() {
		return num;
	}
}
