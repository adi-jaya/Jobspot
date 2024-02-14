package com.jobspot.jobspot;

public class JobVacancyModel {

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
    private final String expDate;

    public JobVacancyModel(String companyName, String industry, String city, String address, String aboutCompany, String position, String jobType, String gender, String age, String education, String experience, String jobDescription, String expDate) {
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
        this.expDate = expDate;
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

    public String getExpDate() {
        return expDate;
    }
}
