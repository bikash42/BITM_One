package com.example.bikash.bitmfirstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.models.Doctor;
import com.example.bikash.bitmfirstproject.models.MedicalList;
import com.example.bikash.bitmfirstproject.recyclerviews.DoctorAdapter;
import com.example.bikash.bitmfirstproject.recyclerviews.MediacalListAdapter;

import java.util.ArrayList;

public class MedicalListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MedicalList> medicalLists;
    private MediacalListAdapter mediacalListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list);

        recyclerView = findViewById(R.id.medicalRecyler);

        medicalLists = new ArrayList<>();
        medicalLists.add(new MedicalList("Appolo Hospital"));
        medicalLists.add(new MedicalList("Dhaka Medical College & Hospital"));
        medicalLists.add(new MedicalList("Bangabandhu Shiekh Mujib Medical University"));
        medicalLists.add(new MedicalList("Bangladesh Medical College"));
        medicalLists.add(new MedicalList("CMH (Dhaka Cantonment)"));
        medicalLists.add(new MedicalList("Ibn Sina Hospital"));
        medicalLists.add(new MedicalList("Squar Diagnostic & Hospital Services Ltd"));
        medicalLists.add(new MedicalList("Holy Family R. C. Hospital"));
        medicalLists.add(new MedicalList("Shahid Suhrawardy Hospital"));
        medicalLists.add(new MedicalList("Sir Salimullah Medical College & Mitford Hospital"));

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        mediacalListAdapter = new MediacalListAdapter(medicalLists,this);
        recyclerView.setAdapter(mediacalListAdapter);


    }
}
