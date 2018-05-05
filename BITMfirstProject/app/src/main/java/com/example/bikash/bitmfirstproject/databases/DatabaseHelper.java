package com.example.bikash.bitmfirstproject.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bikash.bitmfirstproject.models.Appointment;

/**
 * Created by Bikash on 4/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="project";
    public static final int DATABASE_VERSION=1;

    public static final String APPOINTMENT_TABLE="appoint";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_PAIENT_NAME="paient_name";
    public static final String COLUMN_CONTACT="contact";
    public static final String COLUMN_SPECIALIST_DR="specialist";
    public static final String COLUMN_DATE="date";

    public static final String CREATE_APPOINTMENT_TABLE="create table "+
            APPOINTMENT_TABLE+"( "+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_PAIENT_NAME+" text,"+COLUMN_CONTACT+" text,"+COLUMN_SPECIALIST_DR+" text,"+COLUMN_DATE+" text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_APPOINTMENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists "+APPOINTMENT_TABLE);
        onCreate(sqLiteDatabase);

    }
}
