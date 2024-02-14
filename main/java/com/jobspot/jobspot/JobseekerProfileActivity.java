package com.jobspot.jobspot;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class JobseekerProfileActivity extends AppCompatActivity {
    ConstraintLayout documentFileCard;
    TextView fullNameTextView, expertiseTextView, aboutMeTextView, genderTextView, ageTextView,
    phoneTextView, emailTextView, addressTextView, noteTextView;

    String fullName = "Adi Jaya";
    String expertise = "Software Engineer";
    String aboutMe = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab assumenda " +
            "corporiseveniet fugit harum illo, illum impedit natus, nisi odio officiis optio, " +
            "quod repellendus repudiandae sapiente? Aliquid cumque laboriosam voluptatum!";
    Integer age = 19;
    String gender = "Laki-laki";
    String phone = "087850048520";
    String email = "adijaya@gmail.com";
    String address = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias, aut.";
    String documentURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseeker_profile);

        getJobseekerProfileData();

        fullNameTextView = findViewById(R.id.fullNameTextView);
        expertiseTextView = findViewById(R.id.expertiseTextView);
        aboutMeTextView = findViewById(R.id.aboutMeTextView);
        genderTextView = findViewById(R.id.genderTextView);
        ageTextView = findViewById(R.id.ageTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        emailTextView = findViewById(R.id.emailTextView);
        addressTextView = findViewById(R.id.addressTextView);
        noteTextView = findViewById(R.id.noteTextView);
        documentFileCard = findViewById(R.id.documentFileCard);

        fullNameTextView.setText(fullName);
        expertiseTextView.setText(expertise);
        aboutMeTextView.setText(aboutMe);
        genderTextView.setText(gender);
        ageTextView.setText(age.toString());
        phoneTextView.setText(phone);
        emailTextView.setText(email);
        addressTextView.setText(address);

        // Jika documentURL tidak tersedia
        if (TextUtils.isEmpty(documentURL)) {
            documentFileCard.setVisibility(View.GONE);
            noteTextView.setVisibility(View.VISIBLE);
        } else {
            documentFileCard.setVisibility(View.VISIBLE);
            noteTextView.setVisibility(View.INVISIBLE);
            setDocumentFileCard(documentURL);
        }

        setBottomNavigationView();
        setLogoutConfirmDialogBox();
    }

    public void getJobseekerProfileData() {
        if (getIntent().getExtras() != null) {
            fullName = getIntent().getExtras().getString("fullName");
            expertise = getIntent().getExtras().getString("expertise");
            aboutMe = getIntent().getExtras().getString("aboutMe");
            gender = getIntent().getExtras().getString("gender");
            age = getIntent().getExtras().getInt("age");
            phone = getIntent().getExtras().getString("phone");
            address = getIntent().getExtras().getString("address");
            documentURL = getIntent().getExtras().getString("documentURL");
        }
    }

    public void setDocumentFileCard(String documentURL) {
        // Jika documentURL tidak valid
        if ( !URLUtil.isValidUrl(documentURL) ) {
            noteTextView.setVisibility(View.VISIBLE);
            noteTextView.setText("URL tidak valid");
        } else {
            documentFileCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(documentURL);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }
    }

    public void toEditProfileActivity(View view) {
        Intent editProfileActivity = new Intent(JobseekerProfileActivity.this, JobseekerProfileEditActivity.class);

        editProfileActivity.putExtra("fullName", fullName);
        editProfileActivity.putExtra("expertise", expertise);
        editProfileActivity.putExtra("aboutMe", aboutMe);
        editProfileActivity.putExtra("age", age);
        editProfileActivity.putExtra("gender", gender);
        editProfileActivity.putExtra("phone", phone);
        editProfileActivity.putExtra("address", address);
        editProfileActivity.putExtra("documentURL", documentURL);

        startActivity(editProfileActivity);
    }

    public void logout() {
        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        Intent loginActivity = new Intent(JobseekerProfileActivity.this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    public void setLogoutConfirmDialogBox() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_dialog_box));
        dialog.setCancelable(false);

        ImageButton logoutButton = findViewById(R.id.logoutButton);
        MaterialButton confirmButton = dialog.findViewById(R.id.confirmButton);
        MaterialButton cancelButton = dialog.findViewById(R.id.cancelButton);
        TextView titleTextView = dialog.findViewById(R.id.titleTextView);
        TextView subtitleTextView = dialog.findViewById(R.id.subtitleTextView);

        titleTextView.setText("Logout");
        subtitleTextView.setText("Apakah Anda yakin ingin keluar dari aplikasi ini?");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                logout();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void setBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.profileMenuItem);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.homeMenuItem:
                    startActivity( new Intent(getApplicationContext(), JobVacancyActivity.class) );
                    finish();
                    return true;
                case R.id.applicationMenuItem:
                    startActivity( new Intent(getApplicationContext(), JobApplicationActivity.class) );
                    finish();
                    return true;
                case R.id.profileMenuItem:
                    startActivity( new Intent(getApplicationContext(), JobseekerProfileActivity.class) );
                    finish();
                    return true;
            }

            return false;
        });
    }
}