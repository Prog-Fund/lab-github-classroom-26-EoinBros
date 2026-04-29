package controllers;

import models.Cat;
import models.Dog;
import models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayCareTest {

    private DayCare dayCare;

    @BeforeEach
    void setUp() {
        dayCare = new DayCare("Happy Kennels", 10);
    }

    @Test
    void addPet_addsAndCounts() {
        Owner owner = new Owner("Mary", "123");
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Labrador", false);
        assertTrue(dayCare.addPet(dog));
        assertEquals(1, dayCare.numberOfPets());
        assertEquals(dog, dayCare.getPet(0));
    }

    @Test
    void deletePetByIndex_invalidIndexReturnsNull() {
        assertNull(dayCare.deletePetByIndex(0));
        assertNull(dayCare.deletePetByIndex(-1));
    }

    @Test
    void deletePetById_deletesMatchingPet() {
        Owner owner = new Owner("Mary", "123");
        Dog dog = new Dog("Rex", 3, owner, 99, 'M', true, 10.0, true, "Labrador", false);
        dayCare.addPet(dog);

        assertEquals(dog, dayCare.deletePetById(99));
        assertEquals(0, dayCare.numberOfPets());
        assertNull(dayCare.getPetById(99));
    }

    @Test
    void listAllPets_noPetsReturnsNoPets() {
        assertEquals("No Pets", dayCare.listAllPets());
    }

    @Test
    void listAllDogs_noDogsReturnsMessage() {
        assertEquals("No Dogs", dayCare.listAllDogs());
    }

    @Test
    void numberOfIndoorCats_countsCorrectly() {
        Owner owner = new Owner("Mary", "123");
        Cat indoor = new Cat("Kitty", 2, owner, 1, 'F', true, 4.0, true, true, "Mouse");
        Cat outdoor = new Cat("Tom", 2, owner, 2, 'M', true, 4.0, true, false, "Ball");
        dayCare.addPet(indoor);
        dayCare.addPet(outdoor);

        assertEquals(1, dayCare.numberOfIndoorCats());
    }
}

