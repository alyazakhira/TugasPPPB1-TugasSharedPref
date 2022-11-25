package com.example.tugassharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String uName;
    private String pass;
    private EditText uNameEdit;
    private EditText passEdit;

    public boolean isLoggedIn = false;
    private SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.sharedpreferenceapp";
    private final String STATUS_KEY = "status-key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uNameEdit = findViewById(R.id.uname_edit);
        passEdit = findViewById(R.id.pass_edit);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        isLoggedIn = mSharedPref.getBoolean(STATUS_KEY,false);

        if (isLoggedIn){
            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);
        }

        findViewById(R.id.btn_submit_login).setOnClickListener(view -> {
            uName = uNameEdit.getText().toString();
            pass = passEdit.getText().toString();

            if (uName.equals("username") && pass.equals("123")){
                isLoggedIn = true;
                saveStatus();
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                startActivity(intent);
            } else {
                isLoggedIn = false;
                saveStatus();
            }

        });
    }

    private void saveStatus(){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(STATUS_KEY,isLoggedIn);
        editor.apply();
    }
}