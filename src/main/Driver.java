package main;

import controllers.DayCare;

import models.*;

import utils.ScannerInput;

public class Driver {

    private final DayCare dayCare = new DayCare();

    public static void main(String[] args) {

        Driver driver = new Driver();

        driver.runMenu();

    }

}

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {
            if (option == 1) {
                runPetsCrudMenu();
            }
            else if (option == 2) {
                runReportsMenu();
            }
            else if (option == 3) {
                searchPets();
            }
            else if (option == 4) {
                sortPets();
            }
            else if (option == 5) {
                saveAll();
            }
            else if (option == 6) {
                loadAll();
            }
            else {
                System.out.println("Invalid option: " + option);
            }

            option = mainMenu();
        }

        System.out.println("Exiting...bye");
    }

    private int mainMenu() {
        System.out.println("------ Pet Day Care ------");
        System.out.println("1) Pets CRUD Menu");
        System.out.println("2) Reports Menu");
        System.out.println("3) Search Pets");
        System.out.println("4) Sort Pets");
        System.out.println("5) Save all");
        System.out.println("6) Load all");
        System.out.println("0) Exit");
        return ScannerInput.readNextInt("==>> ");
    }

    private void runPetsCrudMenu() {
        int option = petsCrudMenu();
        while (option != 0) {
            if (option == 1) {
                addPet();
            }
            else if (option == 2) {
                deletePet();
            }
            else if (option == 3) {
                listAllPets();
            }
            else if (option == 4) {
                updatePetInfo();
            }
            else {
                System.out.println("Invalid option: " + option);
            }
            option = petsCrudMenu();
        }
    }

    private int petsCrudMenu() {
        System.out.println("---- Pets CRUD Menu ----");
        System.out.println("1) Add a new Pet");
        System.out.println("2) Delete a Pet");
        System.out.println("3) List all Pets");
        System.out.println("4) Update Pet Information");
        System.out.println("0) Return to main menu");
        return ScannerInput.readNextInt("==>> ");
    }

    private void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            if (option == 1) {
                listAllPets();
            }
            else if (option == 2) {
                System.out.println(dayCare.listAllDogs());
            }
            else if (option == 3) {
                System.out.println(dayCare.listAllCats());
            }
            else if (option == 4) {
                System.out.println(dayCare.listAllDangerousDogs());
            }
            else if (option == 5) {
                System.out.println(dayCare.listAllIndoorCats());
            }
            else if (option == 6) {
                int age = ScannerInput.readNextInt("Enter age: ");
                System.out.println(dayCare.listAllDogsOlderThan(age));
            }
            else if (option == 7) {
                String toy = ScannerInput.readNextLine("Enter favourite toy: ");
                System.out.println(dayCare.listAllCatsByFavouriteToy(toy));
            }
            else if (option == 8) {
                System.out.println(dayCare.listAllMammalsThatAreNeutered());
            }
            else if (option == 9) {
                System.out.println("Weekly income: " + dayCare.getWeeklyIncome());
            }
            else {
                System.out.println("Invalid option: " + option);
            }
            option = reportsMenu();
        }
    }

    private int reportsMenu() {
        System.out.println("---- Pet Reports Menu ----");
        System.out.println("1) List all Pets");
        System.out.println("2) List all Dogs");
        System.out.println("3) List all Cats");
        System.out.println("4) List all Dangerous Dogs");
        System.out.println("5) List all Indoor Cats");
        System.out.println("6) List all dogs older than an age");
        System.out.println("7) List all cats by favourite toy");
        System.out.println("8) List all animals that are neutered");
        System.out.println("9) Produce Weekly Income Report");
        System.out.println("0) Return to main menu");
        return ScannerInput.readNextInt("==>> ");
    }

    private void addPet() {
        System.out.println("1) Dog");
        System.out.println("2) Cat");
        System.out.println("3) Bird");
        System.out.println("4) Parrot");
        int type = ScannerInput.readNextInt("Choose pet type ==>> ");

        String name = ScannerInput.readNextLine("Enter pet name: ");
        int age = ScannerInput.readNextInt("Enter age: ");
        int id = ScannerInput.readNextInt("Enter id: ");

        String ownerName = ScannerInput.readNextLine("Enter owner name: ");
        String ownerPhone = ScannerInput.readNextLine("Enter owner phone: ");
        Owner owner = new Owner(ownerName, ownerPhone);

        Pet pet = null;

        if (type == 1) {
            char sex = ScannerInput.readNextChar("Enter sex (M/F/U): ");
            boolean neutered = readBoolean("Is neutered (true/false): ");
            double weight = ScannerInput.readNextDouble("Enter weight: ");
            boolean vaccinated = readBoolean("Is vaccinated (true/false): ");
            String breed = ScannerInput.readNextLine("Enter breed: ");
            boolean dangerous = readBoolean("Is dangerous breed (true/false): ");
            pet = new Dog(name, age, owner, id, sex, neutered, weight, vaccinated, breed, dangerous);
        }
        else if (type == 2) {
            char sex = ScannerInput.readNextChar("Enter sex (M/F/U): ");
            boolean neutered = readBoolean("Is neutered (true/false): ");
            double weight = ScannerInput.readNextDouble("Enter weight: ");
            boolean vaccinated = readBoolean("Is vaccinated (true/false): ");
            boolean indoor = readBoolean("Is indoor cat (true/false): ");
            String toy = ScannerInput.readNextLine("Enter favourite toy: ");
            pet = new Cat(name, age, owner, id, sex, neutered, weight, vaccinated, indoor, toy);
        }
        else if (type == 3) {
            double wingSpan = ScannerInput.readNextDouble("Enter wingspan: ");
            boolean canFly = readBoolean("Can fly (true/false): ");
            pet = new Bird(name, age, owner, id, wingSpan, canFly);
        }
        else if (type == 4) {
            double wingSpan = ScannerInput.readNextDouble("Enter wingspan: ");
            boolean canFly = readBoolean("Can fly (true/false): ");
            int vocab = ScannerInput.readNextInt("Enter vocabulary size: ");
            pet = new Parrot(name, age, owner, id, wingSpan, canFly, vocab);
        }

        boolean added = dayCare.addPet(pet);
        if (added) {
            System.out.println("Pet added.");
        }
        else {
            System.out.println("Pet not added.");
        }
    }

    private void deletePet() {
        int id = ScannerInput.readNextInt("Enter the id of the pet to delete: ");
        Pet deleted = dayCare.deletePetById(id);
        if (deleted != null) {
            System.out.println("Deleted: " + deleted);
        }
        else {
            System.out.println("No pet deleted.");
        }
    }

    private void listAllPets() {
        System.out.println(dayCare.listAllPets());
    }

    private void updatePetInfo() {
        int id = ScannerInput.readNextInt("Enter the id of the pet to update: ");
        Pet pet = dayCare.getPetById(id);
        if (pet == null) {
            System.out.println("No pet found.");
            return;
        }

        String newName = ScannerInput.readNextLine("Enter new name: ");
        int newAge = ScannerInput.readNextInt("Enter new age: ");
        pet.setName(newName);
        pet.setAge(newAge);

        System.out.println("Updated: " + pet);
    }

    private void searchPets() {
        int id = ScannerInput.readNextInt("Enter id to search: ");
        Pet pet = dayCare.getPetById(id);
        if (pet == null) {
            System.out.println("No pet found.");
        }
        else {
            System.out.println(pet);
        }
    }

    private void sortPets() {
        System.out.println("1) Sort by name");
        System.out.println("2) Sort by id (descending)");
        int option = ScannerInput.readNextInt("==>> ");
        if (option == 1) {
            dayCare.sortPetsByName();
            System.out.println("Sorted by name.");
        }
        else if (option == 2) {
            dayCare.sortPetsById();
            System.out.println("Sorted by id.");
        }
        else {
            System.out.println("Invalid option.");
        }
    }

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

    private boolean readBoolean(String prompt) {
        String value = ScannerInput.readNextLine(prompt).trim().toLowerCase();
        return value.equals("true") || value.equals("t") || value.equals("yes") || value.equals("y");
    }
}
