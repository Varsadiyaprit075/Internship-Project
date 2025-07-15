package com.example.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourthActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.outputTextView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fruityvice.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi api = retrofit.create(MyApi.class);
        Call<List<Fruit>> call = api.getFruits();

        call.enqueue(new Callback<>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<Fruit>> call, Response<List<Fruit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Fruit> fruits = response.body();
                    StringBuilder builder = new StringBuilder();

                    for (Fruit fruit : fruits) {
                        builder.append(fruit.name)
                                .append(" (")
                                .append(fruit.family)
                                .append(")\n");

                    }

                    textView.setText(builder.toString());
                    Log.d("API loaded","success");
                } else {
                    textView.setText("Error: " + response.code());
                    Log.e("API Error", "Code: " + response.code());
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<List<Fruit>> call, @NonNull Throwable t) {
                textView.setText("Failed: " + t.getMessage());
                Log.e("API Failure", t.getMessage(), t);
            }
        });
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
