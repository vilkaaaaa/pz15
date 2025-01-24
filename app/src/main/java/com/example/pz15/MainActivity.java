package com.example.pz15;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
ListView List;
    DatabaseHalper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
SimpleCursorAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List = findViewById(R.id.listView);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHalper(getApplicationContext());
    }
    public void onResume(){
        super.onResume();
        db=databaseHelper.getReadableDatabase();
        userCursor = db.rawQuery(" select* from " + DatabaseHalper.TABLE, null);
        String [] headers = new String[]{DatabaseHalper.COLUM_NAME,DatabaseHalper.COLUM_YEAR};
        userAdapter= new SimpleCursorAdapter(this, android.R.layout.activity_list_item,
                userCursor, headers, new int []{android.R.id.text1,android.R.id.text2},0);
        List.setAdapter(userAdapter);
    }
    public void Add(View view)
    {
        Intent intent= new Intent(this,UserActivity.class);
        startActivity(intent);
    }
    public void onDestroy()
    {
        super.onDestroy();
        db.close();
        userCursor.close();
    }
}