package domain;

import businessLogic.service.ConvertSV;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RhombusTest {
    private Shape rhombus;
    private Rhombus secondRhombus;

    @Before
    public void setUp() {
        rhombus = new Rhombus(15, 5);
        secondRhombus = new Rhombus(5, 15);
    }

    @Test
    public void toStringTest() {
        assertEquals("Ruit 15.0 5.0 75.0", rhombus.toString());
    }

    @Test
    public void calculate() {
        assertEquals(ConvertSV.staticRound(secondRhombus.calculate()), secondRhombus.getVolume(), 0);
    }

}
