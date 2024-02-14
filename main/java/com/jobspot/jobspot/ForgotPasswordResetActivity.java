package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_reset);
    }

    public void sendNewPassword(View view) {
        String newPassword = getNewPassword();

        if (!newPassword.isEmpty()) {
            Toast.makeText(this, newPassword, Toast.LENGTH_SHORT).show();
            toResetPasswordSuccessActivity();
        } else {
            Toast.makeText(this, "Masukkan password baru", Toast.LENGTH_SHORT).show();
        }
    }

    public String getNewPassword() {
        EditText newPasswordInputEditText = findViewById(R.id.newPasswordInputEditText);
        String newPassword = newPasswordInputEditText.getText().toString();
        return newPassword;
    }

    public void toResetPasswordSuccessActivity() {
        Intent resetPasswordSuccessActivity = new Intent(ForgotPasswordResetActivity.this, ForgotPasswordResetSuccessActivity.class);
        startActivity(resetPasswordSuccessActivity);
        finish();
    }

    public void toLoginActivity(View view) {
        Intent loginActivity = new Intent(ForgotPasswordResetActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}