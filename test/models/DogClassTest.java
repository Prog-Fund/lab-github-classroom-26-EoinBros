package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DogClassTest {

    @Test
    void calculateFee_smallDefaultsTo20() {
        DogClass dog = new DogClass("Rex", 3, "Sam", true, "small");
        assertEquals(20.0, dog.calculateFee(), 0.0001);
    }

    @Test
    void calculateFee_mediumIs25() {
        DogClass dog = new DogClass("Rex", 3, "Sam", true, "medium");
        assertEquals(25.0, dog.calculateFee(), 0.0001);
    }

    @Test
    void calculateFee_largeIs30() {
        DogClass dog = new DogClass("Rex", 3, "Sam", true, "large");
        assertEquals(30.0, dog.calculateFee(), 0.0001);
    }

    @Test
    void calculateFee_nullSizeDefaultsTo20() {
        DogClass dog = new DogClass("Rex", 3, "Sam", true, null);
        assertEquals(20.0, dog.calculateFee(), 0.0001);
    }
}
