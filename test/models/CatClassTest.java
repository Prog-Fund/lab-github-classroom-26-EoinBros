package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CatClassTest {

    @Test
    void calculateFee_indoorIs15() {
        CatClass cat = new CatClass("Milo", 2, "Alex", true, true);
        assertEquals(15.0, cat.calculateFee(), 0.0001);
    }

    @Test
    void calculateFee_outdoorIs20() {
        CatClass cat = new CatClass("Milo", 2, "Alex", true, false);
        assertEquals(20.0, cat.calculateFee(), 0.0001);
    }
}
