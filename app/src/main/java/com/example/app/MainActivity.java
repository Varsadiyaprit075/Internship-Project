package com.example.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private ProgressDialog progressDialog;
    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch themeSwitch = findViewById(R.id.themeSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            themeSwitch.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            themeSwitch.setChecked(false);
        }

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("isDarkMode", true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("isDarkMode", false);
            }

            editor.apply();
            recreate();
        });



        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);
                imageView.setImageResource(0);

                Toast.makeText(MainActivity.this, "Refreshed!", Toast.LENGTH_SHORT).show();

            }, 1000);
        });


        progressDialog = new ProgressDialog(this);
        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            progressDialog.setMessage("Loading, please wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });


        Button galleryButton = findViewById(R.id.gallerybutton);
        galleryButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ThirdActivity.class)));

        Button toastButton = findViewById(R.id.bt);
        toastButton.setOnClickListener(v -> {

            toastButton.animate()
                    .scaleX(0.4f)
                    .scaleY(0.4f)
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction(() -> toastButton.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(200));
            Snackbar.make(v, "This is a Snackbar inside CardView button", Snackbar.LENGTH_SHORT).show();
        });

        Button apiButton = findViewById(R.id.btn);
        apiButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FourthActivity.class)));

        Button openCameraBtn = findViewById(R.id.openCameraBtn);
        openCameraBtn.setOnClickListener(v -> {
            openCameraBtn.animate()
                    .scaleX(0.7f)
                    .scaleY(0.7f)
                    .alpha(0.4f)
                    .setDuration(200)
                    .withEndAction(() -> openCameraBtn.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(300));
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA);
            }
        });

        ImageButton menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this::showDrawer);

        Button picasso = findViewById(R.id.picasso);
        picasso.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FifthActivity.class));
        });

    }

    private void showDrawer(View anchor) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View drawerView = inflater.inflate(R.layout.drawer_layout, null);

        PopupWindow popupWindow = new PopupWindow(drawerView,
                500,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true);

        popupWindow.showAtLocation(anchor, Gravity.TOP | Gravity.END, 20, 150);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Camera permission is needed", Toast.LENGTH_SHORT).show();
                }
            });

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if (photo != null) {
                imageView.setImageBitmap(photo);
            }
        }
    }
}