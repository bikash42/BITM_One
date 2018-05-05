package com.example.bikash.bitmfirstproject.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.databases.TableDatabaseManager;
import com.example.bikash.bitmfirstproject.models.Appointment;
import com.example.bikash.bitmfirstproject.recyclerviews.RecyclerActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    EditText name_paient, contact_paient;
    TableDatabaseManager tableDatabaseManager;
    int id;
    Button saveBtn;

    Spinner itemSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        name_paient = findViewById(R.id.nameET);
        contact_paient = findViewById(R.id.contactET);
        saveBtn = findViewById(R.id.saveBtn);

        ///TODO:GET DATA ON SPINNER
        itemSelect = findViewById(R.id.specialisSp);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.specialist_Doctor, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSelect.setAdapter(arrayAdapter);

        tableDatabaseManager = new TableDatabaseManager(this);

        id = getIntent().getIntExtra("id", 0);
        if (id > 0) {
            Appointment appointment = tableDatabaseManager.getSinglePaientInfo(id);
            name_paient.setText(appointment.getPaient_name());
            contact_paient.setText(appointment.getPhoneNumber());
            saveBtn.setText("update");
        }


    }

    public void SaveDataOnDatabases(View view) {

        String specialist_dr = itemSelect.getSelectedItem().toString();
        String paient_name = name_paient.getText().toString();
        String contact = contact_paient.getText().toString();
        String date = time();

        int item = itemSelect.getSelectedItemPosition();


        if(paient_name.isEmpty() || contact.isEmpty() || specialist_dr.equals("Select One"))
        {
            Toast.makeText(this, "Please Filling the Form Carefully!!!", Toast.LENGTH_SHORT).show();
        }else{
            if (id > 0) {
                //TODO:DATA UPDATED
                Appointment appointment = new Appointment(id, paient_name, contact, specialist_dr,date);
                //Appointment appointment = new Appointment(id,name_paient.getText().toString(),contact_paient.getText().toString(),specialist_Dr.getText().toString());

                long isUpdated = tableDatabaseManager.updatePaientInfo(appointment);

                if (isUpdated > 0) {
                    //Intent intent = new Intent(this, DataShowActivity.class);
                    //Intent intent = new Intent(AppointmentActivity.this, RecyclerActivity.class);
                    //startActivity(intent);
                    Toast.makeText(this, "Update Data", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "update failed", Toast.LENGTH_SHORT).show();
                }

            } else {

                //TODO : DATA INSERTED
                Appointment appointment = new Appointment(paient_name, contact, specialist_dr,date);
                //Appointment appointment = new Appointment(name_paient.getText().toString(),contact_paient.getText().toString(),specialist_Dr.getText().toString());
                long isInserted = tableDatabaseManager.addPaientInfo(appointment);

                if (isInserted > 0) {
                    //Intent intent = new Intent(AppointmentActivity.this, RecyclerActivity.class);
                    //Intent intent = new Intent(AppointmentActivity.this, DataShowActivity.class);
                    //startActivity(intent);
                    Toast.makeText(this, "Inserted Data", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "failed to save", Toast.LENGTH_SHORT).show();
                }
            }

        }


    }

    public String time()
    {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        return currentDate;
    }
}
