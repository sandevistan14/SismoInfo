package com.g4d.sismoinfo;

import com.g4d.sismoinfo.earthquakedata.Shock;
import com.g4d.sismoinfo.earthquakedata.epicentralCode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class enumsTest {
    @Test
    public void shockCanGetEnumFromString(){
        assertSame(Shock.BLANK,Shock.fromValue(""));
    }

    @Test
    public void epicentralCodeCanGetEnumFromString(){
        assertSame(epicentralCode.A,epicentralCode.fromValue("SURE"));
    }
}
