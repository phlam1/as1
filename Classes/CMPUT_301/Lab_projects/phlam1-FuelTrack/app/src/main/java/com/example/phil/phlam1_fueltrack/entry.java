package com.example.phil.phlam1_fueltrack;

/**
 * Created by phil on 31/01/16.
 */
public class entry {
    public String date;
    public String station;
    public double odometer;
    public String grade;
    public double amount;
    public double unit;
    public double cost;

    public entry(String date, String station, double odometer, String grade, double amount, double unit, double cost) {
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.grade = grade;
        this.amount = amount;
        this.unit = unit;
        this.cost = cost;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setStation(String station){
        this.station = station;
    }

    public void setOdometer(double odometer){
        this.odometer = odometer;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setUnit(double unit){
        this.amount = unit;
    }

    public void setCost(double unit){
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Date: " + date + "|Station: " + station + "|odometer: " + odometer + "|grade: " + grade + "|amount: " + amount + "|cost: " + cost;

    }

}