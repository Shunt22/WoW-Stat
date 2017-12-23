package ru.shunt.wowstat.database;


import javax.persistence.*;
import java.time.Instant;

/**
 * Created by Shunt on 20-Aug-16.
 */
@Entity
@Table(name = "ParsingInfo")
public class ParsingInfo {

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Instant date;
    @Id
    private int id;

    protected ParsingInfo() {

    }

    public ParsingInfo(int id, Instant date) {
        this.id = id;
        this.date = date;

    }


}