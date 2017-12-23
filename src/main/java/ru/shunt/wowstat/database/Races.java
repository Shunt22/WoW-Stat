package ru.shunt.wowstat.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Shunt on 30-Sep-16.
 */

@Entity
public class Races {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long raceId;
    private long playersAmount;
    private String bracketName;

    protected Races() {

    }

    public Races(long raceId, long playersAmount, String bracketName) {
        this.raceId = raceId;
        this.playersAmount = playersAmount;
        this.bracketName = bracketName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRaceId() {
        return raceId;
    }

    public void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    public long getPlayersAmount() {
        return playersAmount;
    }

    public void setPlayersAmount(long playersAmount) {
        this.playersAmount = playersAmount;
    }

    public String getBracketName() {
        return bracketName;
    }

    public void setBracketName(String bracketName) {
        this.bracketName = bracketName;
    }
    public void increasePlayersAmount(){
        playersAmount++;
    }
}
