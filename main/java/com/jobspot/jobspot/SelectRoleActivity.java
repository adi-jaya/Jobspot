package com.jobspot.jobspot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SelectRoleActivity extends AppCompatActivity {

    Intent registrationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
    }

    public void registerAsPelamar(View view) {
        registrationActivity = new Intent(SelectRoleActivity.this, RegistrationActivity.class);
        registrationActivity.putExtra("role", "pelamar");
        startActivity(registrationActivity);
        finish();
    }

    public void registerAsPerusahaan(View view) {
        registrationActivity = new Intent(SelectRoleActivity.this, RegistrationActivity.class);
        registrationActivity.putExtra("role", "perusahaan");
        startActivity(registrationActivity);
        finish();
    }

    public void toLoginActivity(View view) {
        Intent loginActivity = new Intent(SelectRoleActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}