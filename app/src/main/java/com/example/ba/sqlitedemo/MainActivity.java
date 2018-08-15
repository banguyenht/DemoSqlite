package com.example.ba.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NoteDbHelper mHelper;
    private EditText mEditTextNote;
    private EditText mEditTexTime;
    private Button mButtonCount;
    private Button mButtonAdd;
    private Button mButtonDelete;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDb();
        initView();
        setOnClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                insert();
                Toast.makeText(this,
                        "added:  "
                                + mEditTextNote.getText(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button_count:
                Toast.makeText(this,
                        "all notes is:  "
                                + mHelper.getCount(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button_delete:
                String[] selectionArg={mEditTextNote.getText().toString()};
                mHelper.delete(selectionArg);
                break;
        }
    }

    private void initDb() {
        mHelper = new NoteDbHelper(this);
        mHelper.setContext(this);
    }

    private void initView() {
        mEditTextNote = findViewById(R.id.edit_note);
        mEditTexTime = findViewById(R.id.edit_time);
        mButtonCount = findViewById(R.id.button_count);
        mButtonAdd = findViewById(R.id.button_add);
        mButtonDelete=findViewById(R.id.button_delete);
    }

    public void insert() {
        mHelper.insertNote(new Note(mEditTextNote.getText().toString(),
                mEditTexTime.getText().toString()));
        Toast.makeText(this,
                " " + mHelper.getAllNote().size(), Toast.LENGTH_LONG).show();
    }

    private void setOnClick() {
        mButtonAdd.setOnClickListener(this);
        mButtonCount.setOnClickListener(this);
        mButtonDelete.setOnClickListener(this);
    }
}
