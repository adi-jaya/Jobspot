package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationVerificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_verification);

        setEmailPendaftarTextView();
    }

    public void sendOTPVerificationn(View view) {
        String OTPCode = getOTPCode();

        if (OTPCode.isEmpty()) {
            Toast.makeText(this, "Masukkan kode OTP", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, OTPCode, Toast.LENGTH_SHORT).show();
            toLoginActivity();
        }
    }

    public String getOTPCode() {
        EditText inputCodeEditText = findViewById(R.id.inputCodeEditText);
        String OTPCode = inputCodeEditText.getText().toString();
        return OTPCode;
    }

    public void setEmailPendaftarTextView() {
        TextView emailPendaftarTextView = findViewById(R.id.emailPendaftarTextView);
        String email = getIntent().getStringExtra("email");
        emailPendaftarTextView.setText(email);
    }

    public void toLoginActivity() {
        Intent loginActivity = new Intent(RegistrationVerificationActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    public String getRoleUser() {
        String roleUser = getIntent().getStringExtra("role");
        return roleUser;
    }

    public void toRegistrationActivity(View view) {
        Intent registrationActivity = new Intent(RegistrationVerificationActivity.this, RegistrationActivity.class);

        String roleUser = getIntent().getStringExtra("role");
        registrationActivity.putExtra("role", roleUser);
        startActivity(registrationActivity);
        finish();
    }
}