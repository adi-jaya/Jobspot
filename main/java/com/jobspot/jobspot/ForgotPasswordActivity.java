package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void sendEmailForResetPassword(View view) {
        String email = getEmail();

        if (!email.isEmpty()) {
            toResetPasswordVerificationActivity(email);
        } else {
            Toast.makeText(this, "Masukkan email terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    public String getEmail() {
        EditText emailInputEditText = findViewById(R.id.emailInputEditText);
        String email = emailInputEditText.getText().toString();
        return email;
    }

    public void toResetPasswordVerificationActivity(String email) {
        Intent resetPasswordVerificationActivity = new Intent(ForgotPasswordActivity.this, ForgotPasswordVerificationActivity.class);
        resetPasswordVerificationActivity.putExtra("email", email);
        startActivity(resetPasswordVerificationActivity);
        finish();
    }

    public void backToLoginActivity(View view) {
        Intent loginActivity = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}