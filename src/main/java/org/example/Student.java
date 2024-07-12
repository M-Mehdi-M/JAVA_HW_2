package org.example;

public class Student {
    private String nume;
    private String studentTip;
    private double medie;
    private String preferinte;

    private Boolean areUnCurs;

    public Student(String nume, String studentTip) {
        this.nume = nume;
        this.studentTip = studentTip;
        this.medie = 0.0;
        this.preferinte = "";
        this.areUnCurs = false;
    }

    public String getNume() {
        return this.nume;
    }

    public String getStudentTip() {
        return this.studentTip;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public double getMedie() {
        return this.medie;
    }

    public void setPreferinte(String preferinte) {
        this.preferinte += preferinte + ", ";
    }

    public String getPreferinte() {
        return this.preferinte;
    }

    public void setAreUnCurs(boolean areUnCurs) {
        this.areUnCurs = areUnCurs;
    }

    public boolean getAreUnCurs() {
        return this.areUnCurs;
    }
}
