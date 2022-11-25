package com.example.tugassharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    public boolean isLoggedIn = false;
    private SharedPreferences sSharedPref;
    private final String sharedPrefFile = "com.example.sharedpreferenceapp";
    private final String STATUS_KEY = "status-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button logoutBtn = findViewById(R.id.btn_submit_logout);

        sSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        isLoggedIn = sSharedPref.getBoolean(STATUS_KEY,false);

        logoutBtn.setOnClickListener(view -> {
            isLoggedIn = false;
            saveStatus();
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void saveStatus(){
        SharedPreferences.Editor editor = sSharedPref.edit();
        editor.putBoolean(STATUS_KEY,isLoggedIn);
        editor.apply();
    }
}