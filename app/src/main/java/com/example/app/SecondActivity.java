package com.example.app;


import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    boolean running = false;
    long pauseOffset = 0;
    int currentIndex = 0;
    long lastUpdateTime = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // shows back arrow


        //GALLERY --------------------------------------------------------
        int[] imageArray = new int[]{
                R.drawable.boy,
                R.drawable.car,
                R.drawable.apple,
                R.drawable.laptop,
                R.drawable.airplane1
        };

        String[] s = {"boy", "car", "apple", "laptop", "airplane"};

        ImageView mainImage = findViewById(R.id.mainImageView);
        Gallery gallery = findViewById(R.id.gallery);
        ImageAdapter adapter1 = new ImageAdapter(this, imageArray);
        gallery.setAdapter(adapter1);

        mainImage.setImageResource(imageArray[0]);
        gallery.setOnItemClickListener((parent, view, position, id) -> {
            mainImage.setImageResource(imageArray[position]);
            Toast.makeText(getApplicationContext(), s[position], Toast.LENGTH_SHORT).show();

        });


        //CHRONOMETER  -------------------------------------------------------

        Chronometer chronometer;
        Button startButton, stopButton, resetButton;
        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);
        chronometer.setBase(SystemClock.elapsedRealtime());

        lastUpdateTime = SystemClock.elapsedRealtime();

        chronometer.setOnChronometerTickListener(c ->
        {
            long now = SystemClock.elapsedRealtime();
            long elapsedSinceLastUpdate = now - lastUpdateTime;

            if (elapsedSinceLastUpdate >=2000) {
                currentIndex++;
                if (currentIndex >= imageArray.length) {
                    currentIndex = 0;
                    mainImage.setImageResource(imageArray[currentIndex]);
                } else {
                    mainImage.setImageResource(imageArray[currentIndex]);
                }
                gallery.setSelection(currentIndex);

                lastUpdateTime = now;
            }
        });


        startButton.setOnClickListener(v -> {
            if (!running) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                chronometer.start();
                running = true;
            }
        });

        stopButton.setOnClickListener(v -> {
            if (running) {
                chronometer.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;


            }
        });

        resetButton.setOnClickListener(v -> {
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
            running = false;
            chronometer.stop();
        });

        // ALERT DAI LOGE   -----------------------------------------------------------

        Button deletebutton = findViewById(R.id.deletebutton);
        deletebutton.setOnClickListener(v -> showDeleteAlertDialog());


    }

    private void showDeleteAlertDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete Item");
        builder.setMessage("Are you sure you want to delete this item?");

        builder.setPositiveButton("Yes", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "Item Deleted", Toast.LENGTH_SHORT).show());

        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}