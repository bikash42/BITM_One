package com.example.bikash.bitmfirstproject.recyclerviews;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.activities.AboutActivity;
import com.example.bikash.bitmfirstproject.activities.AppointmentActivity;
import com.example.bikash.bitmfirstproject.activities.DetailsActivity;
import com.example.bikash.bitmfirstproject.activities.MainActivity;
import com.example.bikash.bitmfirstproject.activities.MenuActivity;
import com.example.bikash.bitmfirstproject.databases.TableDatabaseManager;
import com.example.bikash.bitmfirstproject.models.Appointment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements SearchAdapter.SearchAdapterListener{

    /*private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<AppointInfo> appointInfos;
    TableDatabaseManager tableDatabaseManager;
    ArrayList<Appointment>appointments;
    ArrayList<String>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointInfos = new ArrayList<>();


        appointments = new ArrayList<>();
        list = new ArrayList<>();
        tableDatabaseManager = new TableDatabaseManager(this);

        appointments = tableDatabaseManager.getSinglePaient();

        for(Appointment appointment: appointments)
        {
            AppointInfo appointInfo = new AppointInfo(appointment.getPaient_name());

            appointInfos.add(appointInfo);
        }
        adapter = new SingleAdapter(appointInfos,this);

        recyclerView.setAdapter(adapter);

    }
    //TODO:MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //TODO:ADD ON DATABASE
    public void add(View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
        finish();
    }*/

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ArrayList<AppointInfo>list;
    private SearchAdapter dataAdapter;

    private RecyclerView.Adapter adapter;
    TableDatabaseManager tableDatabaseManager;
    ArrayList<Appointment>appointments;

    ArrayList<String>data;

    private FirebaseAuth mAuth;

    int id;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        toolbar = findViewById(R.id.patientToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Patient Info");

        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        /*list = new ArrayList<>();
        list.add(new Data("Bikash"));
        list.add(new Data("halder"));
        list.add(new Data("chandra"));
        list.add(new Data("Shree"));*/

        appointments = new ArrayList<>();
        list = new ArrayList<>();
        tableDatabaseManager = new TableDatabaseManager(this);

        appointments = tableDatabaseManager.getSinglePaient();

        for(Appointment appointment: appointments)
        {
            AppointInfo appointInfo = new AppointInfo(appointment.getPaient_name());

            list.add(appointInfo);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new SearchAdapter(list,this,this);
        recyclerView.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dataAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dataAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                SignOut();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
    private void SignOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.logout);
        builder.setTitle("Hey There!");
        builder.setMessage("Are You Sure to Sign Out?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(RecyclerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(RecyclerActivity.this, MenuActivity.class);
                startActivity(intent);
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void onNameSelected(AppointInfo appointInfo) {

        /*String name = appointInfo.getPaient_name();

        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("id",name);
        startActivity(intent);*/

    }

    //TODO:ADD ON DATABASE
    public void add(View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
        finish();
    }
}
