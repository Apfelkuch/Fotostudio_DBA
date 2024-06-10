package de.hsbi.fotostudio.modul;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents a Birthday with day, month, and year attributes.
 * Provides methods to get and set these attributes.
 * Includes constructors for initializing the Birthday object with default or specified values.
 * 
 * @author Frederick
 */
public class Birthday {

    // Attributes to store the day, month, and year of the birthday
    private int day;
    private int month;
    private int year;

    /**
     * Default constructor for Birthday.
     * Initializes the birthday to January 1, 1900.
     */
    public Birthday() {
        // Default values for the birthday
        this.day = 1;    // Set default day to 1
        this.month = 1;  // Set default month to January
        this.year = 1900;// Set default year to 1900
    }

    /**
     * Parameterized constructor for Birthday.
     * Initializes the birthday with the specified day, month, and year.
     * 
     * @param pDay the day of the birthday
     * @param pMonth the month of the birthday
     * @param pYear the year of the birthday
     */
    public Birthday(int pDay, int pMonth, int pYear) {
        // Initialize the birthday with specified values
        this.day = pDay;     // Set the day
        this.month = pMonth; // Set the month
        this.year = pYear;   // Set the year
    }
    
    public static Birthday convertDateToBirthday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        return new Birthday(calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.YEAR));
    }
    
    public static Date convertBirthdayToDate(Birthday birthday) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.set(Calendar.DAY_OF_MONTH, birthday.getDay());
        calendar.set(Calendar.MONTH, birthday.getMonth());
        calendar.set(Calendar.YEAR, birthday.getYear());
        
        return calendar.getTime();
    }

    /**
     * Gets the day of the birthday.
     * 
     * @return the day of the birthday
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of the birthday.
     * 
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets the month of the birthday.
     * 
     * @return the month of the birthday
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month of the birthday.
     * 
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets the year of the birthday.
     * 
     * @return the year of the birthday
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the birthday.
     * 
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
}
