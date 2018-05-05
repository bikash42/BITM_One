package com.example.bikash.bitmfirstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.models.Doctor;
import com.example.bikash.bitmfirstproject.recyclerviews.DoctorAdapter;

import java.util.ArrayList;

public class SpecialistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Doctor>doctor;
    private DoctorAdapter doctorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);

        recyclerView = findViewById(R.id.doctorRecyler);
        doctor = new ArrayList<>();
        doctor.add(new Doctor("Neurologist"));
        doctor.add(new Doctor("Cardiologist"));
        doctor.add(new Doctor("Hematologist"));
        doctor.add(new Doctor("Urologist"));
        doctor.add(new Doctor("Plastic Surgeon"));
        doctor.add(new Doctor("Physiatrist"));
        doctor.add(new Doctor("Pathologist"));
        doctor.add(new Doctor("Gastroenterologist"));
        doctor.add(new Doctor("Medicine Specialist"));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        doctorAdapter = new DoctorAdapter(doctor,this);
        recyclerView.setAdapter(doctorAdapter);


    }
}
