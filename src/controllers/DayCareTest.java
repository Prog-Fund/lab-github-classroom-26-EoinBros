package controllers;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayCareTest {

    private DayCare dayCare;
    private Owner owner;

    @BeforeEach
    void setUp() {
        dayCare = new DayCare("Urban Tails", 20);
        owner = new Owner("Mary", "123");
    }

    // ---------------- ADD PET TESTS ----------------

    @Test
    void addPet_validPet_returnsTrue() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Labrador", false);
        assertTrue(dayCare.addPet(dog));
    }

    @Test
    void addPet_increasesCount() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Labrador", false);
        dayCare.addPet(dog);
        assertEquals(1, dayCare.numberOfPets());
    }

    @Test
    void addPet_multiplePets() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        dayCare.addPet(new Cat("Kitty", 2, owner, 2, 'F', true, 4.0, true, true, "Mouse"));
        assertEquals(2, dayCare.numberOfPets());
    }

    // ---------------- DELETE TESTS ----------------

    @Test
    void deletePetByIndex_validIndex() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        assertEquals(dog, dayCare.deletePetByIndex(0));
    }

    @Test
    void deletePetByIndex_invalidIndex() {
        assertNull(dayCare.deletePetByIndex(0));
    }

    @Test
    void deletePetById_validId() {
        Dog dog = new Dog("Rex", 3, owner, 99, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        assertEquals(dog, dayCare.deletePetById(99));
    }

    @Test
    void deletePetById_notFound() {
        assertNull(dayCare.deletePetById(999));
    }

    // ---------------- GET TESTS ----------------

    @Test
    void getPet_validIndex() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        assertEquals(dog, dayCare.getPet(0));
    }

    @Test
    void getPet_invalidIndex() {
        assertNull(dayCare.getPet(0));
    }

    @Test
    void getPetById_found() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        assertEquals(dog, dayCare.getPetById(1));
    }

    @Test
    void getPetById_notFound() {
        assertNull(dayCare.getPetById(5));
    }

    // ---------------- LIST TESTS ----------------

    @Test
    void listAllPets_empty() {
        assertEquals("No Pets", dayCare.listAllPets());
    }

    @Test
    void listAllDogs_empty() {
        assertEquals("No Dogs", dayCare.listAllDogs());
    }

    @Test
    void listAllPets_withPets() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertNotEquals("No Pets", dayCare.listAllPets());
    }

    // ---------------- CAT TESTS ----------------

    @Test
    void numberOfIndoorCats_none() {
        assertEquals(0, dayCare.numberOfIndoorCats());
    }

    @Test
    void numberOfIndoorCats_countsCorrectly() {
        Cat indoor = new Cat("Kitty", 2, owner, 1, 'F', true, 4.0, true, true, "Mouse");
        Cat outdoor = new Cat("Tom", 2, owner, 2, 'M', true, 4.0, true, false, "Ball");

        dayCare.addPet(indoor);
        dayCare.addPet(outdoor);

        assertEquals(1, dayCare.numberOfIndoorCats());
    }

    // ---------------- ATTENDANCE TESTS ----------------

    @Test
    void checkInPet_valid() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);

        assertTrue(dayCare.checkInPetById(1, 0)); // Monday
    }

    @Test
    void checkInPet_invalidId() {
        assertFalse(dayCare.checkInPetById(999, 0));
    }

    @Test
    void checkOutPet_valid() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        dayCare.checkInPetById(1, 0);

        assertTrue(dayCare.checkOutPetById(1, 0));
    }

    @Test
    void listPetsAttendingOnDay_none() {
        assertEquals("No Pets Attending", dayCare.listPetsAttendingOnDay(0));
    }

    // ---------------- FEES ----------------

    @Test
    void calculateFees_basic() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);

        dayCare.checkInPetById(1, 0); // one day

        assertTrue(dayCare.calculateWeeklyFees() > 0);
    }

    // ---------------- STRESS TEST ----------------

    @Test
    void addMaxPets() {
        for (int i = 0; i < 20; i++) {
            dayCare.addPet(new Dog("Dog" + i, 2, owner, i, 'M', true, 5.0, true, "Lab", false));
        }
        assertEquals(20, dayCare.numberOfPets());
    }

    @Test
    void cannotExceedCapacity() {
        for (int i = 0; i < 20; i++) {
            dayCare.addPet(new Dog("Dog" + i, 2, owner, i, 'M', true, 5.0, true, "Lab", false));
        }
        assertFalse(dayCare.addPet(new Dog("Extra", 2, owner, 99, 'M', true, 5.0, true, "Lab", false)));
    }

    // ---------------- LIST CATS ----------------

    @Test
    void listAllCats_empty() {
        assertEquals("No cats", dayCare.listAllCats());
    }

    @Test
    void listAllCats_withCat() {
        dayCare.addPet(new Cat("Kitty", 2, owner, 1, 'F', true, 4.0, true, true, "Mouse"));
        assertNotEquals("No cats", dayCare.listAllCats());
    }

    // ---------------- LIST PARROTS ----------------

    @Test
    void listAllParrots_empty() {
        assertEquals("No Parrots", dayCare.listAllParrots());
    }

    @Test
    void listAllParrots_withParrot() {
        dayCare.addPet(new Parrot("Polly", 5, owner, 1, 0.3, true, 3, 3));
        assertNotEquals("No Parrots", dayCare.listAllParrots());
    }

    // ---------------- DANGEROUS DOGS ----------------

    @Test
    void listAllDangerousDogs_noDogs() {
        assertEquals("No Dogs", dayCare.listAllDangerousDogs());
    }

    @Test
    void listAllDangerousDogs_noDangerous() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertEquals("No Dangerous Dogs in the Kennels", dayCare.listAllDangerousDogs());
    }

    @Test
    void listAllDangerousDogs_withDangerous() {
        dayCare.addPet(new Dog("Spike", 4, owner, 1, 'M', false, 30.0, true, "Rottweiler", true));
        assertNotEquals("No Dangerous Dogs in the Kennels", dayCare.listAllDangerousDogs());
    }

    // ---------------- COUNTS ----------------

    @Test
    void numberOfDogs_countsCorrectly() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        dayCare.addPet(new Dog("Buddy", 2, owner, 2, 'M', true, 8.0, true, "Beagle", false));
        dayCare.addPet(new Cat("Kitty", 2, owner, 3, 'F', true, 4.0, true, true, "Mouse"));
        assertEquals(2, dayCare.numberOfDogs());
    }

    @Test
    void numberOfCats_countsCorrectly() {
        dayCare.addPet(new Cat("Kitty", 2, owner, 1, 'F', true, 4.0, true, true, "Mouse"));
        dayCare.addPet(new Dog("Rex", 3, owner, 2, 'M', true, 10.0, true, "Lab", false));
        assertEquals(1, dayCare.numberOfCats());
    }

    @Test
    void numberOfParrots_countsCorrectly() {
        dayCare.addPet(new Parrot("Polly", 5, owner, 1, 0.3, true, 3, 3));
        dayCare.addPet(new Parrot("Rio", 3, owner, 2, 0.4, true, 2, 4));
        assertEquals(2, dayCare.numberOfParrots());
    }

    // ---------------- NEUTERED MAMMALS ----------------

    @Test
    void listAllMammalsThatAreNeutered_none() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', false, 10.0, true, "Lab", false));
        assertEquals("No Neutered Animals", dayCare.listAllMammalsThatAreNeutered());
    }

    @Test
    void listAllMammalsThatAreNeutered_withNeutered() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertNotEquals("No Neutered Animals", dayCare.listAllMammalsThatAreNeutered());
    }

    // ---------------- FILTER BY OWNER ----------------

    @Test
    void listAllPetsByOwner_found() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertNotEquals("No Pet with owner Mary", dayCare.listAllPetsByOwner("Mary"));
    }

    @Test
    void listAllPetsByOwner_notFound() {
        dayCare.addPet(new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertEquals("No Pet with owner John", dayCare.listAllPetsByOwner("John"));
    }

    // ---------------- FILTER DOGS BY AGE ----------------

    @Test
    void listAllDogsOlderThan_found() {
        dayCare.addPet(new Dog("Rex", 5, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertNotEquals("No Dogs older than 3", dayCare.listAllDogsOlderThan(3));
    }

    @Test
    void listAllDogsOlderThan_none() {
        dayCare.addPet(new Dog("Rex", 2, owner, 1, 'M', true, 10.0, true, "Lab", false));
        assertEquals("No Dogs older than 3", dayCare.listAllDogsOlderThan(3));
    }

    // ---------------- SORT ----------------

    @Test
    void sortPetsById_sortsDescending() {
        Dog dog1 = new Dog("Alpha", 2, owner, 1, 'M', true, 5.0, true, "Lab", false);
        Dog dog2 = new Dog("Beta", 2, owner, 3, 'M', true, 5.0, true, "Lab", false);
        Dog dog3 = new Dog("Gamma", 2, owner, 2, 'M', true, 5.0, true, "Lab", false);
        dayCare.addPet(dog1);
        dayCare.addPet(dog2);
        dayCare.addPet(dog3);
        dayCare.sortPetsById();
        assertEquals(3, dayCare.getPet(0).getId());
    }

    // ---------------- WEEKLY INCOME ----------------

    @Test
    void calculateWeeklyFees_withAttendance() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        dayCare.checkInPetById(1, 0);
        dayCare.checkInPetById(1, 1);
        assertTrue(dayCare.calculateWeeklyFees() > 0);
    }

    // ---------------- AVERAGE DAYS ----------------

    @Test
    void getAverageNumDaysPerWeek_empty() {
        assertEquals(0, dayCare.getAverageNumDaysPerWeek(), 0.001);
    }

    @Test
    void getAverageNumDaysPerWeek_withPets() {
        Dog dog1 = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        Dog dog2 = new Dog("Buddy", 2, owner, 2, 'M', true, 8.0, true, "Beagle", false);
        dayCare.addPet(dog1);
        dayCare.addPet(dog2);
        dayCare.checkInPetById(1, 0);
        dayCare.checkInPetById(2, 0);
        dayCare.checkInPetById(2, 1);
        assertEquals(1.5, dayCare.getAverageNumDaysPerWeek(), 0.001);
    }

    // ---------------- UPDATE PET ----------------

    @Test
    void updatePet_validIndex() {
        Dog original = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        Dog updated = new Dog("Max", 4, owner, 2, 'M', true, 12.0, true, "Poodle", false);
        dayCare.addPet(original);
        dayCare.updatePet(0, updated);
        assertEquals(updated, dayCare.getPet(0));
    }

    // ---------------- ATTENDANCE WITH PET ----------------

    @Test
    void listPetsAttendingOnDay_withAttendingPet() {
        Dog dog = new Dog("Rex", 3, owner, 1, 'M', true, 10.0, true, "Lab", false);
        dayCare.addPet(dog);
        dayCare.checkInPetById(1, 2);
        assertNotEquals("No Pets Attending", dayCare.listPetsAttendingOnDay(2));
    }
}