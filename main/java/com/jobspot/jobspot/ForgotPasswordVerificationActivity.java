package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_verification);

        setEmailPendaftarTextView();
    }

    public void sendVerifikasiResetPassword(View view) {
        String codeVerification = getCodeVerification();

        if (codeVerification.isEmpty()) {
            Toast.makeText(this, "Masukkan kode verifikasi", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, codeVerification, Toast.LENGTH_SHORT).show();
            toResetPasswordActivity();
        }
    }

    public String getCodeVerification() {
        EditText inputCodeEditText = findViewById(R.id.inputCodeEditText);
        String code = inputCodeEditText.getText().toString();
        return code;
    }

    public void setEmailPendaftarTextView() {
        TextView emailPendaftarTextView2 = findViewById(R.id.emailPendaftarTextView2);
        String email = getIntent().getStringExtra("email");
        emailPendaftarTextView2.setText(email);
    }

    public void toResetPasswordActivity() {
        Intent resetPasswordActivity = new Intent(ForgotPasswordVerificationActivity.this, ForgotPasswordResetActivity.class);
        startActivity(resetPasswordActivity);
        finish();
    }

    public void toForgotPasswordActivity(View view) {
        Intent forgotPasswordActivity = new Intent(ForgotPasswordVerificationActivity.this, ForgotPasswordActivity.class);
        startActivity(forgotPasswordActivity);
        finish();
    }
}