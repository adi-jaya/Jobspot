package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class JobseekerProfileEditActivity extends AppCompatActivity {
    EditText fullNameEditText, expertiseEditText, aboutMeEditText, ageEditText, phoneEditText, addressEditText, documentURLEditText;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton, femaleRadioButton;

    String fullName;
    String expertise;
    String aboutMe;
    Integer age;
    String gender;
    String phone;
    String address;
    String documentURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobseeker_profile_edit);

        fullNameEditText = findViewById(R.id.fullNameEditText);
        expertiseEditText = findViewById(R.id.expertiseEditText);
        aboutMeEditText = findViewById(R.id.aboutMeEditText);
        ageEditText = findViewById(R.id.ageEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        addressEditText = findViewById(R.id.addressEditText);
        documentURLEditText = findViewById(R.id.documentURLEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);

        getJobseekerProfileData();
    }

    public void save(View view) {
        getFormValue();
        toJobseekerProfileActivity();
    }

    public void getFormValue() {
        fullName = fullNameEditText.getText().toString();
        expertise = expertiseEditText.getText().toString();
        aboutMe = aboutMeEditText.getText().toString();
        age = Integer.parseInt(ageEditText.getText().toString());
        phone = phoneEditText.getText().toString();
        address = addressEditText.getText().toString();
        documentURL = documentURLEditText.getText().toString();

        switch (genderRadioGroup.getCheckedRadioButtonId()) {
            case R.id.maleRadioButton:
                gender = "Laki-laki";
                break;
            case R.id.femaleRadioButton:
                gender = "Perempuan";
                break;
            default:
                gender = "";
        }
    }

    public void setFormValue() {
        fullNameEditText.setText(fullName);
        expertiseEditText.setText(expertise);
        aboutMeEditText.setText(aboutMe);
        ageEditText.setText(age.toString());
        phoneEditText.setText(phone);
        addressEditText.setText(address);
        documentURLEditText.setText(documentURL);

        switch (gender) {
            case "Laki-laki":
                maleRadioButton.setChecked(true);
                femaleRadioButton.setChecked(false);
                break;
            case "Perempuan":
                maleRadioButton.setChecked(false);
                femaleRadioButton.setChecked(true);
                break;
            default:
                maleRadioButton.setChecked(false);
                femaleRadioButton.setChecked(false);
        }
    }

    public void checkFormValue() {
        //
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

            setFormValue();
        }
    }

    public void toJobseekerProfileActivity() {
        Intent jobseekerProfileActivity = new Intent(JobseekerProfileEditActivity.this, JobseekerProfileActivity.class);

        jobseekerProfileActivity.putExtra("fullName", fullName);
        jobseekerProfileActivity.putExtra("expertise", expertise);
        jobseekerProfileActivity.putExtra("aboutMe", aboutMe);
        jobseekerProfileActivity.putExtra("age", age);
        jobseekerProfileActivity.putExtra("gender", gender);
        jobseekerProfileActivity.putExtra("phone", phone);
        jobseekerProfileActivity.putExtra("address", address);
        jobseekerProfileActivity.putExtra("documentURL", documentURL);

        startActivity(jobseekerProfileActivity);
        finish();
    }
}