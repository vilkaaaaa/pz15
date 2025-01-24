package com.example.pz15;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {
EditText Name;
EditText Year;
Button Del;
    Button Save;
    DatabaseHalper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Name = findViewById(R.id.name);
        Year = findViewById(R.id.year);
        Del = findViewById(R.id.del);
        Save = findViewById(R.id.save);
        sqlHelper = new DatabaseHalper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            userId = extras.getLong("_id");
        }
        if(userId >0) {
            userCursor = db.rawQuery("select* from " + DatabaseHalper.TABLE + " where " +
                    DatabaseHalper.COLUM_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            Name.setText(userCursor.getString(1));
            Year.setText(String.valueOf(userCursor.getInt(2)));
            userCursor.close();
        }
        else {
            Del.setVisibility(View.GONE);
        }
    }
    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHalper.COLUM_NAME,Name.getText().toString());
        cv.put(DatabaseHalper.COLUM_YEAR,Integer.parseInt(Year.getText().toString()));
        if(userId>0) {
            db.update(DatabaseHalper.TABLE, cv, DatabaseHalper.COLUM_ID + "=" + userId, null);
        }else{
            db.insert(DatabaseHalper.TABLE,null,cv);
        }
        goHome();
    }
    private void goHome() {
    db.close();
        Intent intent =  new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void delete(View view) {
        db.delete(DatabaseHalper.TABLE,"_id = ?",new String[]{String.valueOf(userId)});
    }
}