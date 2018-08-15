package com.example.ba.sqlitedemo;

import java.util.List;

public interface IDatabseManager {
     void insertNote(Note note);
     List<Note> getAllNote();
     int getCount();
     void delete(String[] selectionArg);

}
