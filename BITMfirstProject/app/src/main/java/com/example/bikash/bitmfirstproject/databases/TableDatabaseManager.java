package com.example.bikash.bitmfirstproject.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bikash.bitmfirstproject.models.Appointment;

import java.util.ArrayList;

/**
 * Created by Bikash on 4/20/2018.
 */

public class TableDatabaseManager {


    private Context context;
    DatabaseHelper databaseHelper;

    public TableDatabaseManager(Context context) {
        this.context = context;

        databaseHelper = new DatabaseHelper(context);
    }

    //TODO: insert Data on Databases
    public long addPaientInfo(Appointment appointment){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_PAIENT_NAME,appointment.getPaient_name());
        contentValues.put(DatabaseHelper.COLUMN_CONTACT,appointment.getPhoneNumber());
        contentValues.put(DatabaseHelper.COLUMN_SPECIALIST_DR,appointment.getSpecialist_Dr());
        contentValues.put(DatabaseHelper.COLUMN_DATE,appointment.getDate());
        long insert=sqLiteDatabase.insert(DatabaseHelper.APPOINTMENT_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return insert;
    }

    // TODO: Update Data on Databases
    public long updatePaientInfo(Appointment appointment){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_PAIENT_NAME,appointment.getPaient_name());
        contentValues.put(DatabaseHelper.COLUMN_CONTACT,appointment.getPhoneNumber());
        contentValues.put(DatabaseHelper.COLUMN_SPECIALIST_DR,appointment.getSpecialist_Dr());
        long update=sqLiteDatabase.update(DatabaseHelper.APPOINTMENT_TABLE,contentValues,DatabaseHelper.COLUMN_ID+" =? ",new String[]{String.valueOf(appointment.getId())});
        sqLiteDatabase.close();
        return update;
    }

    //TODO: DELETE DATA ON DATABASES
    public long deletePaientInfo(int id){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        long delete=sqLiteDatabase.delete(DatabaseHelper.APPOINTMENT_TABLE,DatabaseHelper.COLUMN_ID+" =? ",new String[]{String.valueOf(id)});
        return delete;
    }

    // TODO: SHOW DATABASE LIST
    public ArrayList<Appointment> getAllPaientInfo(){
        ArrayList<Appointment>allPaientsInfo=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        String selectQuery="select * from "+DatabaseHelper.APPOINTMENT_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String paientname=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PAIENT_NAME));
                String contact=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTACT));
                String specialistDr=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SPECIALIST_DR));
                String date=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
                Appointment appointment=new Appointment(id,paientname,contact,specialistDr,date);
                allPaientsInfo.add(appointment);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return allPaientsInfo;
    }

    //TODO: SHOW SINGLE INSTANCES
    public Appointment getSinglePaientInfo(int id){
        Appointment appointment=null;
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        String selectQuery="select * from "+DatabaseHelper.APPOINTMENT_TABLE+" where "+DatabaseHelper.COLUMN_ID +" = "+id;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            String paientname=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PAIENT_NAME));
            String contact=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTACT));
            String specialistDr=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SPECIALIST_DR));
            String date=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
            appointment=new Appointment(id,paientname,contact,specialistDr,date);
        }
        return appointment;
    }

    //TODO:Single Patient Information
    public ArrayList<Appointment> getSinglePaient(){
        ArrayList<Appointment>allPaientsInfo=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        String selectQuery="select * from "+DatabaseHelper.APPOINTMENT_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String paientname=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PAIENT_NAME));
                Appointment appointment=new Appointment(id,paientname);
                allPaientsInfo.add(appointment);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return allPaientsInfo;
    }


}
