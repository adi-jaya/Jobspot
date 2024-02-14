package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForgotPasswordResetSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_reset_success);
    }

    public void toLoginActivity(View view) {
        Intent loginActivity = new Intent(ForgotPasswordResetSuccessActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}