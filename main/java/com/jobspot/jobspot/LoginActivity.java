package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    JobseekerProfileModel jobseekerProfileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        String[] dataLogin = getDataLogin();

        String email = dataLogin[0];
        String password = dataLogin[1];

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Masukkan email dan password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            toHomeActivity();
        }
    }

    public String[] getDataLogin() {
        EditText emailInputEditText = findViewById(R.id.emailInputEditText);
        EditText passwordInputEditText = findViewById(R.id.passwordInputTextView);

        String email = emailInputEditText.getText().toString();
        String password = passwordInputEditText.getText().toString();

        String[] dataLogin = {email, password};
        return dataLogin;
    }

    public void toHomeActivity() {
        Intent homeActivity = new Intent(LoginActivity.this, JobVacancyActivity.class);
        startActivity(homeActivity);
        finish();
    }

    public void toForgotPasswordActivity(View view) {
        Intent forgotPasswordActivity = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(forgotPasswordActivity);
        finish();
    }

    public void toSelectRoleActivity(View view) {
        Intent selectRoleActivity = new Intent(LoginActivity.this, SelectRoleActivity.class);
        startActivity(selectRoleActivity);
        finish();
    }
}