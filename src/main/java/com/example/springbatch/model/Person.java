package com.example.springbatch.model;



public class Person {

    // properties
    private long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String bankruptcyDate;
    private String bankruptcyReason;
    private String bankruptcyChapter;
    private String startDate;
    private String SSNCode;
    private String zipCode;
    private float yearlyIncome;
    private float creditScore;
    private float amount;
    private float duration;
    private float yearlyInterestRate;
    private int showtrace;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String birthDate, String bankruptcyDate, String bankruptcyReason, String bankruptcyChapter, String startDate, String SSNCode, String zipCode, float yearlyIncome, float creditScore, float amount, float duration, float yearlyInterestRate, int showtrace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.bankruptcyDate = bankruptcyDate;
        this.bankruptcyReason = bankruptcyReason;
        this.bankruptcyChapter = bankruptcyChapter;
        this.startDate = startDate;
        this.SSNCode = SSNCode;
        this.zipCode = zipCode;
        this.yearlyIncome = yearlyIncome;
        this.creditScore = creditScore;
        this.amount = amount;
        this.duration = duration;
        this.yearlyInterestRate = yearlyInterestRate;
        this.showtrace = showtrace;
    }

    public Person () {

    }

    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBankruptcyDate() {
        return bankruptcyDate;
    }

    public void setBankruptcyDate(String bankruptcyDate) {
        this.bankruptcyDate = bankruptcyDate;
    }

    public String getBankruptcyReason() {
        return bankruptcyReason;
    }

    public void setBankruptcyReason(String bankruptcyReason) {
        this.bankruptcyReason = bankruptcyReason;
    }

    public String getBankruptcyChapter() {
        return bankruptcyChapter;
    }

    public void setBankruptcyChapter(String bankruptcyChapter) {
        this.bankruptcyChapter = bankruptcyChapter;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSSNCode() {
        return SSNCode;
    }

    public void setSSNCode(String SSNCode) {
        this.SSNCode = SSNCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public float getYearlyIncome() {
        return yearlyIncome;
    }

    public void setYearlyIncome(float yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public float getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(float creditScore) {
        this.creditScore = creditScore;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(float yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public int getShowtrace() {
        return showtrace;
    }

    public void setShowtrace(int showtrace) {
        this.showtrace = showtrace;
    }
}
