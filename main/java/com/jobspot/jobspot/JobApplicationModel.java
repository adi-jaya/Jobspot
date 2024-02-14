package com.jobspot.jobspot;

public class JobApplicationModel {

    // Data Perusahaan
    private final String companyName;
    private final String industry;
    private final String city;
    private final String address;
    private final String aboutCompany;

    // Data Lowongan
    private final String position;
    private final String jobType;
    private final String gender;
    private final String age;
    private final String education;
    private final String experience;
    private final String jobDescription;

    // Data Lamaran
    private final String status;
    private final String submissionDate;
    private final String decisionDate;

    // Data Notification
    private final String dateSend;
    private final String timeSend;
    private final String date;
    private final String time;
    private final String location;
    private final String message;

    public JobApplicationModel(String companyName, String industry, String city, String address,
                               String aboutCompany, String position, String jobType, String gender,
                               String age, String education, String experience, String jobDescription,
                               String status, String submissionDate, String decisionDate, String dateSend,
                               String timeSend, String date, String time, String location, String message) {
        this.companyName = companyName;
        this.industry = industry;
        this.city = city;
        this.address = address;
        this.aboutCompany = aboutCompany;
        this.position = position;
        this.jobType = jobType;
        this.gender = gender;
        this.age = age;
        this.education = education;
        this.experience = experience;
        this.jobDescription = jobDescription;
        this.status = status;
        this.submissionDate = submissionDate;
        this.decisionDate = decisionDate;
        this.dateSend = dateSend;
        this.timeSend = timeSend;
        this.date = date;
        this.time = time;
        this.location = location;
        this.message = message;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public String getPosition() {
        return position;
    }

    public String getJobType() {
        return jobType;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getDecisionDate() {
        return decisionDate;
    }

    public String getDateSend() {
        return dateSend;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }
}
