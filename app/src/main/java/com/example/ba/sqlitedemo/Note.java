package com.example.ba.sqlitedemo;

public class Note {

    public static final String TABLE_NAME = "note";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIME = "time";

    private int mId;
    private String mNote;
    private String mTime;

    public Note() {
    }

    public Note(String mNote, String mTime) {
        this.mNote = mNote;
        this.mTime = mTime;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        this.mNote = note;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        this.mTime = time;
    }
}
