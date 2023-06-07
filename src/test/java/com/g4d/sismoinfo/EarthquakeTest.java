package com.g4d.sismoinfo;

import com.g4d.sismoinfo.earthquakedata.Earthquake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EarthquakeTest {
    @Test
    public void canInstanciate() {
        assertDoesNotThrow(() -> new Earthquake(760009,"1142/01/07","6 h","BASSE-VALLEE DE LA SEINE (ROUEN)","NORMANDIE","",49.48,0.93,5,"INCERTAINE"));
    }
}

