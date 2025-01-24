package com.example.pz15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHalper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user1.db";
    private static final int SCHEMA = 1;
    static  final String TABLE = "users";

    public static final  String COLUM_ID = "_id";
    public static final  String COLUM_NAME = "_name";
    public static final  String COLUM_YEAR = "_year";


    public DatabaseHalper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("CREATE TABLE users("+COLUM_ID
        +" Integer PRIMARY KEY AUTOINCREMENT,"+ COLUM_NAME
+" TEXT,"+ COLUM_YEAR+" INTEGER);");
db.execSQL(" insert INTO "+ TABLE+"("+ COLUM_NAME+","+COLUM_YEAR+")values('Вероника Степановна',1999),('Чернова Григориана',2002);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
db.execSQL(" DROP TABLE IF EXISTS "+ TABLE);
onCreate(db);
    }
}
