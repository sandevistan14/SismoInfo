package com.g4d.sismoinfo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * The `dataFormatting` class provides utility methods for parsing and formatting date and time values.
 */
public class dataFormatting {

    /**
     * The date format pattern used for parsing date strings.
     */
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("[yyy/MM/dd][yyyy/MM/dd]");

    /**
     * The time format pattern used for parsing time strings.
     */
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H' h'[' 'm' min'[' 's' sec']");

    /**
     * The default month and day used when parsing incomplete date strings.
     */
    private final static String DEFAULT_MONTH_AND_DAY = "01";

    /**
     * Parses a time string and returns the corresponding `LocalTime` object.
     *
     * @param time The time string to be parsed.
     * @return The parsed `LocalTime` object, or `null` if the time string is empty.
     */
    public static LocalTime timeParsing(String time){
        LocalTime hour = null;
        if (time.equals(""))
            return hour;
        else
            hour = LocalTime.parse(time, timeFormatter);
        return hour;
    }

    /**
     * Parses a date string and returns the corresponding `LocalDate` object.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed `LocalDate` object.
     */
    public static LocalDate dateParsing(String dateString){
        String exploitableDateString = null;
        switch (dateString.length()){
            case 4:
            case 5:
                exploitableDateString = dateString+DEFAULT_MONTH_AND_DAY+"/"+DEFAULT_MONTH_AND_DAY; break;
            case 6:
            case 7:
                exploitableDateString = dateString+"/"+DEFAULT_MONTH_AND_DAY; break;
            default:
                exploitableDateString = dateString;
        }
        LocalDate date = LocalDate.parse(exploitableDateString,dateFormatter);
        return date;
    }
}
