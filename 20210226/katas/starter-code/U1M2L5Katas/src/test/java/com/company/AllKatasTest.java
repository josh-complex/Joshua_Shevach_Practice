package com.company;
import com.company.interfaces.Alarm;
import com.company.interfaces.Clock;
import com.company.interfaces.Shape;
import org.junit.Test;

import static org.junit.Assert.*;

public class AllKatasTest {

     private Shape square = new Square();
     private Shape circle = new Circle();
     private Shape triangle = new Triangle();
     private Clock analogClock = new AnalogClock();
     private Alarm buzzer = new Buzzer();
     private DigitalClock digitalClock = new DigitalClock();

    @Test
    public void shouldImplementShapeInterfaceWhenRun() {

        String failMessage = "Expected to implement the Shape interface";
        assertTrue(failMessage, circle instanceof Shape);
        assertTrue(failMessage, square instanceof Shape);
        assertTrue(failMessage, triangle instanceof Shape);
    }

    @Test
    public void shouldImplementClockInterfaceAndContainExpectedAnalogClockMethodsWhenRun() throws Exception {

        String failMessage = "Expected AnalogClock to implement the Clock interface";
        assertTrue(failMessage, analogClock instanceof Clock);

        Class ana = AnalogClock.class;
        failMessage = "Expected AnalogClock to have displayTime method";
        assertNotNull(failMessage, ana.getMethod("displayTime"));
        failMessage = "Expected AnalogClock to have timer method with two integer parameters";
        assertNotNull(failMessage, ana.getMethod("timer", new Class[] {int.class, int.class}));
    }

    @Test
    public void shouldImplementAlarmInterfaceAndContainExpectedBuzzerMethodsWhenRun() throws Exception {

        String failMessage = "Expected Buzzer to implement the Alarm interface";
        assertTrue(failMessage, buzzer instanceof Alarm);

        Class buzz = Buzzer.class;
        failMessage = "Expected Buzzer to implement the Alarm interface";
        assertNotNull(failMessage, buzz.getMethod("sound"));
    }

    @Test
    public void shouldImplementClockAndAlarmInterfacesAndContainExpectedDigitalClockMethodsWhenRun() throws Exception {

        String failMessage = "Expected DigitalClock to implement the Clock interface";
        assertTrue(failMessage, digitalClock instanceof Clock);
        failMessage = "Expected DigitalClock to implement the Alarm interface";
        assertTrue(failMessage, digitalClock instanceof Alarm);

        Class digi = DigitalClock.class;
        failMessage = "Expected DigitalClock to have diplayTime method";
        assertNotNull(failMessage, digi.getMethod("displayTime"));
        failMessage = "Expected DigitalClock to have timer method with two integer parameters";
        assertNotNull(failMessage, digi.getMethod("timer", new Class[] {int.class, int.class}));
        failMessage = "Expected DigitalClock to implement the Alarm interface";
        assertNotNull(failMessage, digi.getMethod("sound"));
    }
}
