package ru.shunt.wowstat.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSpecId() {
        return specId;
    }

    public void setSpecId(long specId) {
        this.specId = specId;
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

    private long specId;
    private long playersAmount;
    private String bracketName;

    protected Specialization() {

    }

    public Specialization(long specId, long playersAmount, String bracketName) {
        this.specId = specId;
        this.playersAmount = playersAmount;
        this.bracketName = bracketName;
    }

    public long getPlayersAmount() {
        return playersAmount;
    }

    public void increasePlayersAmount() {
        playersAmount++;
    }

}
