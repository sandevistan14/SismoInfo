package com.g4d.sismoinfo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import javax.management.InvalidApplicationException;
import org.junit.jupiter.api.Test;

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
}
