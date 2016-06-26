package com.jbh5310.domain.db1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jbh5310 on 2016-06-26.
 */
@Entity
public class Article {

    @Id
    @GeneratedValue
    int id;

    String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}