package com.example.ba.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteDbHelper extends SQLiteOpenHelper implements IDatabseManager {
    private Context mContext;
    public static final String DB_NAME = "BD_NOTE";
    public static final int DB_VERSION = 2;
    public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Note.TABLE_NAME
            + "(" + Note.COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + Note.COLUMN_NOTE
            + " TEXT," + Note.COLUMN_TIME + " TEXT);";

    public NoteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        Log.d("ddd ", DB_NAME);
        Toast.makeText(mContext, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    @Override
    public void insertNote(Note note) {
        SQLiteDatabase mDb = this.getWritableDatabase();
        ContentValues mValue = new ContentValues();
        mValue.put(Note.COLUMN_NOTE, note.getNote());
        mValue.put(Note.COLUMN_TIME, note.getTime());
        mDb.insert(Note.TABLE_NAME, null, mValue);
        mDb.close();
    }

    @Override
    public List<Note> getAllNote() {
        List<Note> mListNote = new ArrayList<>();
        SQLiteDatabase mDb = this.getReadableDatabase();
        String[] projection = {Note.COLUMN_NOTE, Note.COLUMN_TIME};
        Cursor mCursor = mDb.
                query(Note.TABLE_NAME, projection,
                        null, null, null, null, null);
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            Note mNote = new Note();
            mNote.setNote(mCursor.getString(mCursor.getColumnIndex(Note.COLUMN_NOTE)));
            mNote.setTime(mCursor.getString(mCursor.getColumnIndex(Note.COLUMN_TIME)));
            mListNote.add(mNote);
            mCursor.moveToNext();
        }
        mCursor.close();
        return mListNote;
    }

    @Override
    public int getCount() {
        SQLiteDatabase mDb = this.getReadableDatabase();
        String mQuery = "SELECT * FROM " + Note.TABLE_NAME;
        int mCount = mDb.rawQuery(mQuery, null).getCount();
        return mCount;
    }

    @Override
    public void delete(String[] selectionArg) {
        SQLiteDatabase mDb = this.getReadableDatabase();
        String selection= Note.COLUMN_NOTE  + " LIKE ?";
        mDb.delete(Note.TABLE_NAME, selection ,selectionArg);
    }

}
