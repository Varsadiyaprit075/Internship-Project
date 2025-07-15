package com.example.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class FifthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = findViewById(R.id.pimage);

        Picasso.get()
                .load("https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?w=600")
                .placeholder(R.drawable.custom)
                .error(R.drawable.screenshot_2025_06_17_145734)
                .into(imageView);


    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
