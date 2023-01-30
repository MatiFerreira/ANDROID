package com.example.realm.modelos;

import com.example.realm.app.Myapplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Board extends RealmObject { //tablero en ingles
    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private Date createdAt;
    private RealmList<Note> notes;

    public Board(String title){
        this.title = title;
        this.createdAt = new Date();
        this.notes = new RealmList<Note>();
        this.id = Myapplication.boardid.getAndIncrement();
    }
    public Board(){

    }

    public int getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    public RealmList<Note> getNotes() {
        return notes;
    }

}
