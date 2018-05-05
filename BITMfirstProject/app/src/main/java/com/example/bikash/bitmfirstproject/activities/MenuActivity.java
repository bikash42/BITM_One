package com.example.bikash.bitmfirstproject.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikash.bitmfirstproject.R;
import com.example.bikash.bitmfirstproject.recyclerviews.RecyclerActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    //Single Patient Info

    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        appBarLayout = findViewById(R.id.appLay);


        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
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

    public void InsertData(View view) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);

    }

    public void onPatientInfo(View view) {

        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);

    }

    public void SignOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.logout);
        builder.setTitle("Hey There!");
        builder.setMessage("Are You Sure to Sign Out?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
                startActivity(intent);
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public void doctorInfo(View view) {

        Intent intent = new Intent(this, SpecialistActivity.class);
        startActivity(intent);
    }

    public void emergency(View view) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MenuActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);

            }else{

                Uri uri = Uri.parse("tel:"+999);
                Intent callIntent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(callIntent);


        }

    }

    public void listShow(View view) {

        Intent intent = new Intent(this, MedicalListActivity.class);
        startActivity(intent);

    }
}
