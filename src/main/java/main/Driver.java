package main;

import controllers.DayCare;
import models.*;
import utils.ScannerInput;

public class Driver {

    private final DayCare dayCare = new DayCare("Urban Tails", 20);

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.runMenu();
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {
            if (option == 1) runPetsCrudMenu();
            else if (option == 2) runReportsMenu();
            else if (option == 3) searchPets();
            else if (option == 4) sortPets();
            else if (option == 5) runStaffMenu();
            else if (option == 6) saveAll();
            else if (option == 7) loadAll();
            else System.out.println("Invalid option entered: " + option);
            option = mainMenu();
        }
        System.out.println("Exiting...bye");
    }

    private int mainMenu() {
        System.out.println("\n------ Pet Day Care ------");
        System.out.println("1) Pets CRUD Menu");
        System.out.println("2) Reports Menu");
        System.out.println("3) Search Pets");
        System.out.println("4) Sort Pets");
        System.out.println("5) Staff Menu (Check in/out)");
        System.out.println("6) Save all");
        System.out.println("7) Load all");
        System.out.println("0) Exit");
        return ScannerInput.readNextInt("==>> ");
    }

    // ---- CRUD Menu ----

    private void runPetsCrudMenu() {
        int option = petsCrudMenu();
        while (option != 0) {
            if (option == 1) addPet();
            else if (option == 2) deletePet();
            else if (option == 3) listAllPets();
            else if (option == 4) updatePetInfo();
            else System.out.println("Invalid option entered: " + option);
            option = petsCrudMenu();
        }
    }

    private int petsCrudMenu() {
        System.out.println("\n---- Pets CRUD Menu ----");
        System.out.println("1) Add a new Pet");
        System.out.println("2) Delete a Pet");
        System.out.println("3) List all Pets");
        System.out.println("4) Update Pet Information");
        System.out.println("0) Return to main menu");
        return ScannerInput.readNextInt("==>> ");
    }

    private void addPet() {
        System.out.println("\n1) Dog");
        System.out.println("2) Cat");
        System.out.println("3) Parrot");
        int type = ScannerInput.readNextInt("Choose pet type ==>> ");

        String name = ScannerInput.readNextLine("Enter pet name: ");
        int age = ScannerInput.readNextInt("Enter age: ");
        int id = ScannerInput.readNextInt("Enter id: ");
        String temperament = ScannerInput.readNextLine("Enter temperament: ");
        String ownerName = ScannerInput.readNextLine("Enter owner name: ");
        String ownerPhone = ScannerInput.readNextLine("Enter owner phone: ");
        Owner owner = new Owner(ownerName, ownerPhone);

        Pet pet = null;

        if (type == 1) {
            char sex = ScannerInput.readNextChar("Enter sex (M/F/U): ");
            boolean neutered = readYesNo("Is neutered? (y/n): ");
            double weight = ScannerInput.readNextDouble("Enter weight (kg): ");
            boolean vaccinated = readYesNo("Is vaccinated? (y/n): ");
            String breed = ScannerInput.readNextLine("Enter breed: ");
            int size = readDogSize();
            boolean dangerous = readYesNo("Is dangerous breed? (y/n): ");
            Dog dog = new Dog(name, age, owner, id, sex, neutered, weight, vaccinated, breed, size);
            dog.setDangerousBreed(dangerous);
            pet = dog;
        } else if (type == 2) {
            char sex = ScannerInput.readNextChar("Enter sex (M/F/U): ");
            boolean neutered = readYesNo("Is neutered? (y/n): ");
            double weight = ScannerInput.readNextDouble("Enter weight (kg): ");
            boolean vaccinated = readYesNo("Is vaccinated? (y/n): ");
            boolean indoor = readYesNo("Is indoor cat? (y/n): ");
            String toy = ScannerInput.readNextLine("Enter favourite toy: ");
            pet = new Cat(name, age, owner, id, sex, neutered, weight, vaccinated, indoor, toy);
        } else if (type == 3) {
            double wingSpan = ScannerInput.readNextDouble("Enter wingspan (cm): ");
            boolean canFly = readYesNo("Can fly? (y/n): ");
            int social = readNeeds("Enter socialisation needs (1-5): ");
            int enrich = readNeeds("Enter enrichment needs (1-5): ");
            pet = new Parrot(name, age, owner, id, wingSpan, canFly, social, enrich);
        } else {
            System.out.println("Invalid pet type.");
            return;
        }

        pet.setTemperament(temperament);

        if (dayCare.addPet(pet)) {
            System.out.println("Pet added successfully.");
        } else {
            System.out.println("Could not add pet - day care is full.");
        }
    }

    private void deletePet() {
        int id = ScannerInput.readNextInt("Enter the id of the pet to delete: ");
        Pet deleted = dayCare.deletePetById(id);
        if (deleted != null) {
            System.out.println("Deleted: " + deleted);
        } else {
            System.out.println("No pet found with that id.");
        }
    }

    private void listAllPets() {
        System.out.println(dayCare.listAllPets());
    }

    private void updatePetInfo() {
        int id = ScannerInput.readNextInt("Enter the id of the pet to update: ");
        Pet pet = dayCare.getPetById(id);
        if (pet == null) {
            System.out.println("No pet found with that id.");
            return;
        }
        String newName = ScannerInput.readNextLine("Enter new name: ");
        int newAge = ScannerInput.readNextInt("Enter new age: ");
        String newTemperament = ScannerInput.readNextLine("Enter new temperament: ");
        pet.setName(newName);
        pet.setAge(newAge);
        pet.setTemperament(newTemperament);
        System.out.println("Updated: " + pet);
    }

    // ---- Reports Menu ----

    private void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            if (option == 1) System.out.println(dayCare.listAllPets());
            else if (option == 2) System.out.println(dayCare.listAllDogs());
            else if (option == 3) System.out.println(dayCare.listAllCats());
            else if (option == 4) System.out.println(dayCare.listAllParrots());
            else if (option == 5) System.out.println(dayCare.listAllIndoorCats());
            else if (option == 6) System.out.println(dayCare.listAllDangerousDogs());
            else if (option == 7) {
                int age = ScannerInput.readNextInt("Enter age: ");
                System.out.println(dayCare.listAllDogsOlderThan(age));
            } else if (option == 8) {
                String toy = ScannerInput.readNextLine("Enter favourite toy: ");
                System.out.println(dayCare.listAllCatsByFavouriteToy(toy));
            } else if (option == 9) System.out.println(dayCare.listAllMammalsThatAreNeutered());
            else if (option == 10) System.out.println("Total weekly income: €" + dayCare.calculateWeeklyFees());
            else if (option == 11) {
                int day = readDayIndex();
                System.out.println(dayCare.listPetsAttendingOnDay(day));
            } else System.out.println("Invalid option entered: " + option);
            option = reportsMenu();
        }
    }

    private int reportsMenu() {
        System.out.println("\n---- Pet Reports Menu ----");
        System.out.println("1) List all Pets");
        System.out.println("2) List all Dogs");
        System.out.println("3) List all Cats");
        System.out.println("4) List all Parrots");
        System.out.println("5) List all Indoor Cats");
        System.out.println("6) List all Dangerous Dogs");
        System.out.println("7) List all Dogs older than an age");
        System.out.println("8) List all Cats by favourite toy");
        System.out.println("9) List all Neutered Mammals");
        System.out.println("10) Weekly Income Report");
        System.out.println("11) List pets attending on a given day");
        System.out.println("0) Return to main menu");
        return ScannerInput.readNextInt("==>> ");
    }

    // ---- Search and Sort ----

    private void searchPets() {
        int id = ScannerInput.readNextInt("Enter id to search: ");
        Pet pet = dayCare.getPetById(id);
        if (pet == null) {
            System.out.println("No pet found with that id.");
        } else {
            System.out.println(pet);
        }
    }

    private void sortPets() {
        System.out.println("\n1) Sort by name (A-Z)");
        System.out.println("2) Sort by id (descending)");
        int option = ScannerInput.readNextInt("==>> ");
        if (option == 1) {
            dayCare.sortPetsByName();
            System.out.println("Sorted by name.");
        } else if (option == 2) {
            dayCare.sortPetsById();
            System.out.println("Sorted by id (descending).");
        } else {
            System.out.println("Invalid option.");
        }
    }

    // ---- Staff Menu ----

    private void runStaffMenu() {
        int option = staffMenu();
        while (option != 0) {
            if (option == 1) {
                int id = ScannerInput.readNextInt("Enter pet id: ");
                int day = readDayIndex();
                if (dayCare.checkInPetById(id, day)) {
                    System.out.println("Checked in successfully.");
                } else {
                    System.out.println("No pet found with that id.");
                }
            } else if (option == 2) {
                int id = ScannerInput.readNextInt("Enter pet id: ");
                int day = readDayIndex();
                if (dayCare.checkOutPetById(id, day)) {
                    System.out.println("Checked out successfully.");
                } else {
                    System.out.println("No pet found with that id.");
                }
            } else if (option == 3) {
                int day = readDayIndex();
                System.out.println(dayCare.listPetsAttendingOnDay(day));
            } else {
                System.out.println("Invalid option entered: " + option);
            }
            option = staffMenu();
        }
    }

    private int staffMenu() {
        System.out.println("\n---- Staff Menu ----");
        System.out.println("1) Check in a pet for a day");
        System.out.println("2) Check out a pet for a day");
        System.out.println("3) List pets attending on a day");
        System.out.println("0) Return to main menu");
        return ScannerInput.readNextInt("==>> ");
    }

    // ---- Save / Load ----

    private void saveAll() {
        try {
            dayCare.save();
            System.out.println("Saved to " + dayCare.getFile());
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private void loadAll() {
        try {
            dayCare.load();
            System.out.println("Loaded from " + dayCare.getFile());
        } catch (Exception e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    // ---- Input helpers ----

    private boolean readYesNo(String prompt) {
        String value = ScannerInput.readNextLine(prompt).trim().toLowerCase();
        return value.equals("y") || value.equals("yes");
    }

    /**
     * Reads a day of the week from the user (1=Monday to 7=Sunday)
     * and converts it to a 0-based index for the daysAttending array.
     */
    private int readDayIndex() {
        System.out.println("Day: 1=Mon  2=Tue  3=Wed  4=Thu  5=Fri  6=Sat  7=Sun");
        int day = ScannerInput.readNextInt("Enter day (1-7): ");
        while (day < 1 || day > 7) {
            System.out.println("Please enter a number between 1 and 7.");
            day = ScannerInput.readNextInt("Enter day (1-7): ");
        }
        return day - 1;  // convert to 0-based index
    }

    private int readNeeds(String prompt) {
        int value = ScannerInput.readNextInt(prompt);
        while (value < 1 || value > 5) {
            System.out.println("Please enter a value between 1 and 5.");
            value = ScannerInput.readNextInt(prompt);
        }
        return value;
    }

    private int readDogSize() {
        System.out.println("Dog size: 1=SMALL  2=MEDIUM  3=LARGE  4=XL");
        int size = ScannerInput.readNextInt("==>> ");
        while (size < 1 || size > 4) {
            System.out.println("Please enter 1, 2, 3, or 4.");
            size = ScannerInput.readNextInt("==>> ");
        }
        return size;
    }
}
