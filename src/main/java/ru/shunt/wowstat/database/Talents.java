package ru.shunt.wowstat.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Talents")
public class Talents {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long specId;
	private long talentId;
	private long playersAmount;
	private String bracket;

	protected Talents() {

	}

	public Talents(long specId, long talentId, long playerAmount, String bracket) {
		this.specId = specId;
		this.talentId = talentId;
		this.playersAmount = playerAmount;
		this.bracket = bracket;

	}

	public long getSpecId() {
		return specId;
	}

	public void setSpecId(long specId) {
		this.specId = specId;
	}

	public long getTalentId() {
		return talentId;
	}

	public void setTalentId(long talentId) {
		this.talentId = talentId;
	}

	public long getId() {
		return id;
	}

	public long getPlayersAmount() {
		return playersAmount;
	}

	public void setPlayersAmount(long playersAmount) {
		this.playersAmount = playersAmount;
	}

	public String getBracket() {
		return bracket;
	}

	public void setBracket(String bracketName) {
		this.bracket = bracketName;
	}

	public void increasePlayersAmount(){
		playersAmount++;
	}
}
