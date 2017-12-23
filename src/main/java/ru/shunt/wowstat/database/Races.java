package ru.shunt.wowstat.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Shunt on 30-Sep-16.
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Races {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long raceId;
	private long playersAmount;
	private String bracketName;

	public Races(long raceId, long playersAmount, String bracketName) {
		this.raceId = raceId;
		this.playersAmount = playersAmount;
		this.bracketName = bracketName;
	}

	public void increasePlayersAmount() {
		playersAmount++;
	}
}
