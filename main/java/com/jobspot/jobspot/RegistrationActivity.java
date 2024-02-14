package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setCaptionTextView1();
    }

    public void registration(View view) {
        String[] dataRegistration = getDataRegistration();

        String fullName = dataRegistration[0];
        String email = dataRegistration[1];
        String password = dataRegistration[2];

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Data tidak lengkap", Toast.LENGTH_SHORT).show();
        }
        else {
            toOtpVerificationActivity(email);
        }
    }

    public String getRoleUser() {
        String roleUser = getIntent().getStringExtra("role");
        return roleUser;
    }

    public String[] getDataRegistration() {
        EditText namaLengkapInputEditText = findViewById(R.id.namaLengkapInputEditText);
        EditText emailInputEditText = findViewById(R.id.emailInputEditText);
        EditText passwordInputEdit = findViewById(R.id.passwordInputTextView);

        String fullName = namaLengkapInputEditText.getText().toString();
        String email = emailInputEditText.getText().toString();
        String password = passwordInputEdit.getText().toString();
        String role = getRoleUser();

        String[] dataRegistration = {fullName, email, password, role};
        return dataRegistration;
    }

    public void setCaptionTextView1() {
        TextView captionTextView1 = findViewById(R.id.captionTextView1);

        switch (getRoleUser()) {
            case "pelamar":
                captionTextView1.setText("Anda mendaftar sebagai pelamar kerja");
                break;
            case "perusahaan":
                captionTextView1.setText("Anda mendaftar sebagai pemiliki perusahaan");
                break;
        }
    }

    public void toOtpVerificationActivity(String email) {
        Intent otpVerificationActivity = new Intent(RegistrationActivity.this, RegistrationVerificationActivity.class);
        otpVerificationActivity.putExtra("email", email);
        otpVerificationActivity.putExtra("role", getRoleUser());
        startActivity(otpVerificationActivity);
        finish();
    }

    public void toLoginActivity(View view) {
        Intent loginActivity = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    public void toSelectRoleActivity(View view) {
        Intent selectRoleActivity = new Intent(RegistrationActivity.this, SelectRoleActivity.class);
        startActivity(selectRoleActivity);
        finish();
    }
}