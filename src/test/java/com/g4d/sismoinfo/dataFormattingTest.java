package com.g4d.sismoinfo;

import com.g4d.sismoinfo.model.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class dataFormattingTest {
    @Test
    public void timeParsingCanParseEmptyString() {
        assertDoesNotThrow(() -> dataFormatting.timeParsing(""));
    }

    @Test
    public void timeParsingCanParseWithOnlyHour() {
        String time = "9 h";
        assertDoesNotThrow(() -> dataFormatting.timeParsing(time));
        assertEquals(dataFormatting.timeParsing(time),LocalTime.of(9,0));
    }

    @Test
    public void timeParsingCanParseWithHourAndMinute() {
        String time = "9 h 12 min";
        assertDoesNotThrow(() -> dataFormatting.timeParsing(time));
        assertEquals(dataFormatting.timeParsing(time),LocalTime.of(9,12));
    }

    @Test
    public void timeParsingCanParseWithHourMinuteAndSeconds() {
        String time = "9 h 12 min 6 sec";
        assertDoesNotThrow(() -> dataFormatting.timeParsing(time));
        assertEquals(dataFormatting.timeParsing(time),LocalTime.of(9,12,6));
    }

    @Test
    public void dateParsingCanParseWithOnlyYear() {
        String year = "1015/";
        assertEquals(dataFormatting.dateParsing(year), LocalDate.of(1015,1,1));
    }

    @Test
    public void dateParsingCanParseWithYearAndMonth() {
        String year = "1015/08";
        assertEquals(dataFormatting.dateParsing(year), LocalDate.of(1015,8,1));
    }

    @Test
    public void dateParsingCanParseWithYearMonthAndDay() {
        String year = "1015/08/12";
        assertEquals(dataFormatting.dateParsing(year), LocalDate.of(1015,8,12));
    }
}
