/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.modul;

/**
 *
 * @author Frederick
 */
public class Birthday {

    private int day;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    private int month;
    private int year;

    public Birthday() {
        day = 01;
        month = 01;
        year = 1900;
    }

    public Birthday(int pDay, int pMonth, int pYear) {
        day = pDay;
        month = pMonth;
        year = pYear;
    }
}
