package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Controls all operations for the Pet Day Care system.
 * Manages a list of pets up to a set maximum capacity.
 */
public class DayCare {

    private ArrayList<Pet> pets = new ArrayList<Pet>();
    private int maxNumberOfPets = 0;
    private String name = "";
    private String file = "Pets.xml";

    /**
     * Creates a DayCare with a name and maximum capacity.
     *
     * @param name             the name of the day care
     * @param maxNumberOfPets  the maximum number of pets allowed
     */
    public DayCare(String name, int maxNumberOfPets) {
        setName(name);
        setMaxNumberOfPets(maxNumberOfPets);
    }

    public DayCare() {
    }

    // -------------------------
    // Getters and Setters
    // -------------------------

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public int getMaxNumberOfPets() {
        return maxNumberOfPets;
    }

    public void setMaxNumberOfPets(int maxNumberOfPets) {
        if (maxNumberOfPets >= 0) {
            this.maxNumberOfPets = maxNumberOfPets;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            if (name.length() <= 20) {
                this.name = name;
            } else {
                this.name = name.substring(0, 20);
            }
        }
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        if (file != null && !file.isEmpty()) {
            this.file = file;
        }
    }

    // -------------------------
    // CRUD
    // -------------------------

    /**
     * Adds a pet if the day care has not reached maximum capacity.
     *
     * @param pet  the pet to add
     * @return true if added, false if at capacity
     */
    public boolean addPet(Pet pet) {
        if (pets.size() >= maxNumberOfPets) {
            return false;
        }
        return pets.add(pet);
    }

    /**
     * Removes and returns the pet at the given index.
     *
     * @param index  the index of the pet
     * @return the removed pet, or null if index is invalid
     */
    public Pet deletePetByIndex(int index) {
        if (isValidPetIndex(index)) {
            return pets.remove(index);
        }
        return null;
    }

    /**
     * Removes and returns the pet with the given ID.
     *
     * @param id  the ID of the pet
     * @return the removed pet, or null if not found
     */
    public Pet deletePetById(int id) {
        int index = getPetIndexById(id);
        return deletePetByIndex(index);
    }

    /**
     * Returns the pet at the given index.
     *
     * @param index  the index of the pet
     * @return the pet, or null if index is invalid
     */
    public Pet getPet(int index) {
        if (isValidPetIndex(index)) {
            return pets.get(index);
        }
        return null;
    }

    /**
     * Returns the pet with the given ID.
     *
     * @param id  the ID to search for
     * @return the pet, or null if not found
     */
    public Pet getPetById(int id) {
        int index = getPetIndexById(id);
        return getPet(index);
    }

    /**
     * Replaces the pet at the given index with an updated pet.
     *
     * @param index       the index to update
     * @param updatedPet  the new pet object
     * @return the updated pet, or null if index is invalid
     */
    public Pet updatePet(int index, Pet updatedPet) {
        if (isValidPetIndex(index) && updatedPet != null) {
            pets.set(index, updatedPet);
            return updatedPet;
        }
        return null;
    }

    /**
     * Returns the total number of pets currently in the day care.
     *
     * @return number of pets
     */
    public int numberOfPets() {
        return pets.size();
    }

    /**
     * Returns true if the given index is valid for the pets list.
     *
     * @param index  the index to check
     * @return true if valid
     */
    public boolean isValidPetIndex(int index) {
        return index >= 0 && index < pets.size();
    }

    // -------------------------
    // Listing / Reporting
    // -------------------------

    /**
     * Returns a numbered list of all pets, or "No Pets" if empty.
     *
     * @return formatted string of all pets
     */
    public String listAllPets() {
        if (pets.isEmpty()) {
            return "No Pets";
        }
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            output += i + ": " + pets.get(i) + "\n";
        }
        return output;
    }

    /**
     * Returns a numbered list of all dogs, or "No Dogs" if none.
     *
     * @return formatted string of dogs
     */
    public String listAllDogs() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog) {
                output += i + ": " + pets.get(i) + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Dogs";
        }
        return output;
    }

    /**
     * Returns a numbered list of all cats, or "No cats" if none.
     *
     * @return formatted string of cats
     */
    public String listAllCats() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Cat) {
                output += i + ": " + pets.get(i) + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No cats";
        }
        return output;
    }

    /**
     * Returns a numbered list of all parrots, or "No Parrots" if none.
     *
     * @return formatted string of parrots
     */
    public String listAllParrots() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Parrot) {
                output += i + ": " + pets.get(i) + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Parrots";
        }
        return output;
    }

    /**
     * Returns a numbered list of all dangerous dogs.
     *
     * @return formatted string, or message if none found
     */
    public String listAllDangerousDogs() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog) {
                Dog dog = (Dog) pets.get(i);
                if (dog.isDangerousBreed()) {
                    output += i + ": " + dog + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            if (numberOfDogs() == 0) {
                return "No Dogs";
            }
            return "No Dangerous Dogs in the Kennels";
        }
        return output;
    }

    /**
     * Returns a numbered list of all indoor cats.
     *
     * @return formatted string, or "No Indoor Cats" if none
     */
    public String listAllIndoorCats() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Cat) {
                Cat cat = (Cat) pets.get(i);
                if (cat.isIndoorCat()) {
                    output += i + ": " + cat + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            return "No Indoor Cats";
        }
        return output;
    }

    /**
     * Returns a numbered list of all mammals that are neutered.
     *
     * @return formatted string, or "No Neutered Animals" if none
     */
    public String listAllMammalsThatAreNeutered() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Mammal) {
                Mammal mammal = (Mammal) pets.get(i);
                if (mammal.isNeutered()) {
                    output += i + ": " + mammal + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            return "No Neutered Animals";
        }
        return output;
    }

    /**
     * Returns all pets owned by the given owner name.
     *
     * @param owner  the owner's name to search for
     * @return formatted string of matching pets
     */
    public String listAllPetsByOwner(String owner) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet.getOwner() != null && pet.getOwner().getName().equalsIgnoreCase(owner)) {
                output += i + ": " + pet + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Pet with owner " + owner;
        }
        return output;
    }

    /**
     * Returns all pets that attend more than the given number of days per week.
     *
     * @param numDays  the minimum number of days
     * @return formatted string of matching pets
     */
    public String listAllPetsThatStayMoreThanDays(int numDays) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet.numOfDaysAttending() > numDays) {
                output += i + ": " + pet + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Pet stays longer than " + numDays;
        }
        return output;
    }

    /**
     * Returns all dogs older than the given age.
     *
     * @param age  the age threshold
     * @return formatted string of matching dogs
     */
    public String listAllDogsOlderThan(int age) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog) {
                Dog dog = (Dog) pets.get(i);
                if (dog.getAge() > age) {
                    output += i + ": " + dog + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            return "No Dogs older than " + age;
        }
        return output;
    }

    /**
     * Returns all cats whose favourite toy matches the given string.
     *
     * @param favouriteToy  the toy to search for
     * @return formatted string of matching cats
     */
    public String listAllCatsByFavouriteToy(String favouriteToy) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Cat) {
                Cat cat = (Cat) pets.get(i);
                if (cat.getFavouriteToy().equalsIgnoreCase(favouriteToy)) {
                    output += i + ": " + cat + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            return "No Cats with favourite toy " + favouriteToy;
        }
        return output;
    }

    /**
     * Returns all pets attending on the given day index (0=Monday, 6=Sunday).
     *
     * @param dayIndex  the day index (0-6)
     * @return formatted string of attending pets
     */
    public String listPetsAttendingOnDay(int dayIndex) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).isAttendingOnDay(dayIndex)) {
                output += i + ": " + pets.get(i) + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Pets Attending";
        }
        return output;
    }

    // -------------------------
    // Count / Number methods
    // -------------------------

    /**
     * Returns the number of dogs in the day care.
     *
     * @return count of dogs
     */
    public int numberOfDogs() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) count++;
        }
        return count;
    }

    /**
     * Returns the number of cats in the day care.
     *
     * @return count of cats
     */
    public int numberOfCats() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) count++;
        }
        return count;
    }

    /**
     * Returns the number of parrots in the day care.
     *
     * @return count of parrots
     */
    public int numberOfParrots() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Parrot) count++;
        }
        return count;
    }

    /**
     * Returns the number of dangerous dogs in the day care.
     *
     * @return count of dangerous dogs
     */
    public int numberOfDangerousDogs() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                if (((Dog) pet).isDangerousBreed()) count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of indoor cats in the day care.
     *
     * @return count of indoor cats
     */
    public int numberOfIndoorCats() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                if (((Cat) pet).isIndoorCat()) count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of parrots with the given socialisation needs level.
     *
     * @param needs  the socialisation needs value (1-5)
     * @return count of matching parrots
     */
    public int numberOfParrotsBySocialisationNeeds(int needs) {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Parrot) {
                if (((Parrot) pet).getSocialisationNeeds() == needs) count++;
            }
        }
        return count;
    }

    // -------------------------
    // Check In / Check Out
    // -------------------------

    /**
     * Checks in the pet with the given ID for the given day.
     *
     * @param id        the pet's ID
     * @param dayIndex  the day index (0=Monday, 6=Sunday)
     * @return true if successful, false if pet not found
     */
    public boolean checkInPetById(int id, int dayIndex) {
        Pet pet = getPetById(id);
        if (pet == null) return false;
        pet.checkIn(dayIndex);
        return true;
    }

    /**
     * Checks out the pet with the given ID for the given day.
     *
     * @param id        the pet's ID
     * @param dayIndex  the day index (0=Monday, 6=Sunday)
     * @return true if successful, false if pet not found
     */
    public boolean checkOutPetById(int id, int dayIndex) {
        Pet pet = getPetById(id);
        if (pet == null) return false;
        pet.checkOut(dayIndex);
        return true;
    }

    // -------------------------
    // Fees
    // -------------------------

    /**
     * Calculates and returns the total weekly fees for all pets.
     *
     * @return total weekly income
     */
    public double calculateWeeklyFees() {
        double total = 0;
        for (Pet pet : pets) {
            total += pet.calculateWeeklyFee();
        }
        return total;
    }

    /**
     * Returns the average number of days per week that pets attend.
     *
     * @return average days, or 0 if no pets
     */
    public double getAverageNumDaysPerWeek() {
        if (pets.isEmpty()) return 0;
        int totalDays = 0;
        for (Pet pet : pets) {
            totalDays += pet.numOfDaysAttending();
        }
        return (double) totalDays / pets.size();
    }

    // -------------------------
    // Sorting
    // -------------------------

    /**
     * Sorts pets in descending order by ID using bubble sort.
     */
    public void sortPetsById() {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = 0; j < pets.size() - 1 - i; j++) {
                if (pets.get(j).getId() < pets.get(j + 1).getId()) {
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts pets in ascending alphabetical order by name using bubble sort.
     */
    public void sortPetsByName() {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = 0; j < pets.size() - 1 - i; j++) {
                if (pets.get(j).getName().compareToIgnoreCase(pets.get(j + 1).getName()) > 0) {
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

    // -------------------------
    // Persistence
    // -------------------------

    /**
     * Saves the pets list to an XML file using XStream.
     *
     * @throws Exception if writing fails
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(pets);
        out.close();
    }

    /**
     * Loads the pets list from an XML file using XStream.
     *
     * @throws Exception if reading fails
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        Class<?>[] classes = new Class<?>[]{ Pet.class, Mammal.class, Bird.class, Dog.class, Cat.class, Parrot.class, Owner.class };

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        pets = (ArrayList<Pet>) in.readObject();
        in.close();
    }

    // -------------------------
    // Private helpers
    // -------------------------

    private int getPetIndexById(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
