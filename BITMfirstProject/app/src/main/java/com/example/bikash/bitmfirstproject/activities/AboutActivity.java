package com.example.bikash.bitmfirstproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bikash.bitmfirstproject.R;

public class AboutActivity extends AppCompatActivity {

    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        image = findViewById(R.id.imageAbout);
        text = findViewById(R.id.showTv);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        text.startAnimation(animation);
        image.startAnimation(animation);

        final Intent in = new Intent(this,MenuActivity.class);

        Thread time = new Thread(){
            public void run()
            {
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(in);
                }
            }
        };

        time.start();

    }
}
