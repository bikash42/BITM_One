package com.example.bikash.bitmfirstproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.databases.TableDatabaseManager;
import com.example.bikash.bitmfirstproject.models.Appointment;
import com.example.bikash.bitmfirstproject.recyclerviews.RecyclerActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView namePaientText, contactText, specialistText, dateText;
    TableDatabaseManager tableDatabaseManager;
    int id;
    Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        namePaientText = findViewById(R.id.namepaientTv);
        contactText = findViewById(R.id.contacttTv);
        specialistText = findViewById(R.id.specialistTv);
        dateText = findViewById(R.id.dateTv);
        id = getIntent().getIntExtra("id", 0);
        tableDatabaseManager = new TableDatabaseManager(this);

        appointment = tableDatabaseManager.getSinglePaientInfo(id);

        namePaientText.setText(appointment.getPaient_name());
        //namePaientText.setText(getIntent().getStringExtra("name"));
        contactText.setText(appointment.getPhoneNumber());
        specialistText.setText(appointment.getSpecialist_Dr());
        dateText.setText(appointment.getDate());

    }

    //TODO:UPDATE DATA ON DATABASES
    public void UpdateScreen(View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    //TODO:DELETE DATA ON DATABASES
    public void DeleteScreen(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure?");
        builder.setIcon(R.drawable.ic_error_outline_black_24dp);
        builder.setMessage("You will not be able to recover this file!");
        builder.setPositiveButton("Yes,Delete it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long deleted = tableDatabaseManager.deletePaientInfo(id);
                if (deleted > 0) {
                    //Intent intent=new Intent(DetailsActivity.this,DataShowActivity.class);
                    Intent intent = new Intent(DetailsActivity.this, RecyclerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailsActivity.this, "unable to delete data", Toast.LENGTH_SHORT).show();
                }
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Intent intent = new Intent(DetailsActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }
}
