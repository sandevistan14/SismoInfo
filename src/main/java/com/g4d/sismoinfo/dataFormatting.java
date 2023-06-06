package com.g4d.sismoinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class dataFormatting {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H' h'[' 'm' min']");

    private final static String DEFAULT_MONTH_AND_DAY = "01";

    public static LocalTime timeParsing(String time){
        LocalTime hour = null;
        if (time.equals(""))
            return hour;
        else
            hour = LocalTime.parse(time, timeFormatter);
        return hour;
    }

    public static LocalDate dateParsing(String dateString){
        String exploitableDateString = null;
        switch (dateString.length()){
            case 5:
                exploitableDateString = dateString+DEFAULT_MONTH_AND_DAY+"/"+DEFAULT_MONTH_AND_DAY; break;
            case 7:
                exploitableDateString = dateString+"/"+DEFAULT_MONTH_AND_DAY; break;
            default:
                exploitableDateString = dateString;
        }
        LocalDate date = LocalDate.parse(exploitableDateString,dateFormatter);
        return date;
    }
}
