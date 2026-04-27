package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BirdClassTest {

    @Test
    void calculateFee_canFlyIs10() {
        BirdClass bird = new BirdClass("Kiwi", 1, "Pat", true);
        assertEquals(10.0, bird.calculateFee(), 0.0001);
    }

    @Test
    void calculateFee_cannotFlyIs12() {
        BirdClass bird = new BirdClass("Kiwi", 1, "Pat", false);
        assertEquals(12.0, bird.calculateFee(), 0.0001);
    }
}
