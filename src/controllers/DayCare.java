package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DayCare {

    private ArrayList<Pet> pets = new ArrayList<Pet>();
    private int maxNumberOfPets = 0;
    private String name = "";
    private String file = "Pets.xml";

    public DayCare(String name, int maxNumberOfPets) {
        setName(name);
        setMaxNumberOfPets(maxNumberOfPets);
    }

    public DayCare() {
    }

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

    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    public Pet deletePetByIndex(int index) {
        if (isValidPetIndex(index)) {
            return pets.remove(index);
        }
        return null;
    }

    public Pet deletePetById(int id) {
        int index = getPetIndexById(id);
        return deletePetByIndex(index);
    }

    public Pet getPet(int index) {
        if (isValidPetIndex(index)) {
            return pets.get(index);
        }
        return null;
    }

    public Pet getPet(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    public Pet getPetById(int id) {
        int index = getPetIndexById(id);
        return getPet(index);
    }

    private int getPetIndexById(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

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

    public String listAllDogs() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Dog) {
                output += i + ": " + pet + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Dogs";
        }
        return output;
    }

    public String listAllCats() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Cat) {
                output += i + ": " + pet + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No cats";
        }
        return output;
    }

    public String listAllParrots() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Parrot) {
                output += i + ": " + pet + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Parrots";
        }
        return output;
    }

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

    public int numberOfPets() {
        return pets.size();
    }

    public String listAllDangerousDogs() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
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

    public String listAllIndoorCats() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Cat) {
                Cat cat = (Cat) pet;
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

    public String listAllMammalsThatAreNeutered() {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Mammal) {
                Mammal mammal = (Mammal) pet;
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

    public String listAllDogsOlderThan(int age) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
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

    public String listAllCatsByFavouriteToy(String favouriteToy) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet instanceof Cat) {
                Cat cat = (Cat) pet;
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

    public int numberOfCats() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                count++;
            }
        }
        return count;
    }

    public int numberOfDogs() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                count++;
            }
        }
        return count;
    }

    public int numberOfParrots() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Parrot) {
                count++;
            }
        }
        return count;
    }

    public int numberOfDangerousDogs() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
                if (dog.isDangerousBreed()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int numberOfIndoorCats() {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Cat) {
                Cat cat = (Cat) pet;
                if (cat.isIndoorCat()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int numberOfParrotsByVocabularySize(int vocabSize) {
        int count = 0;
        for (Pet pet : pets) {
            if (pet instanceof Parrot) {
                Parrot parrot = (Parrot) pet;
                int vocab = 0;
                try {
                    vocab = Integer.parseInt(parrot.getVocabularySize());
                } catch (NumberFormatException e) {
                    vocab = 0;
                }

                if (vocab == vocabSize) {
                    count++;
                }
            }
        }
        return count;
    }

    public Pet updatePet(int index, Pet updatedPet) {
        if (isValidPetIndex(index) && updatedPet != null) {
            pets.set(index, updatedPet);
            return updatedPet;
        }
        return null;
    }

    public boolean isValidPetIndex(int index) {
        return index >= 0 && index < pets.size();
    }

    public double getWeeklyIncome() {
        double total = 0;
        for (Pet pet : pets) {
            total += pet.calculateWeeklyFee();
        }
        return total;
    }

    public double getAverageNumDaysPerWeek() {
        if (pets.isEmpty()) {
            return 0;
        }
        int totalDays = 0;
        for (Pet pet : pets) {
            totalDays += pet.numOfDaysAttending();
        }
        return (double) totalDays / pets.size();
    }

    public Pet findDogByOwnerAndBreedAndAge(String name, String breed, int age) {
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
                if (dog.getOwner() != null
                        && dog.getOwner().getName().equalsIgnoreCase(name)
                        && dog.getBreed().equalsIgnoreCase(breed)
                        && dog.getAge() == age) {
                    return dog;
                }
            }
        }
        return null;
    }

    public String getPetsByOwnersName(String name) {
        String output = "";
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            if (pet.getOwner() != null && pet.getOwner().getName().equalsIgnoreCase(name)) {
                output += i + ": " + pet + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No Pets for " + name;
        }
        return output;
    }

    public void sortPetsById() {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = 0; j < pets.size() - 1 - i; j++) {
                if (pets.get(j).getId() < pets.get(j + 1).getId()) {
                    swapPets(j, j + 1);
                }
            }
        }
    }

    public void sortPetsByName() {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = 0; j < pets.size() - 1 - i; j++) {
                if (pets.get(j).getName().compareToIgnoreCase(pets.get(j + 1).getName()) > 0) {
                    swapPets(j, j + 1);
                }
            }
        }
    }

    private void swapPets(int i, int j) {
        Pet temp = pets.get(i);
        pets.set(i, pets.get(j));
        pets.set(j, temp);
    }

    private void swapPets(Pet i, Pet j) {
        int indexI = pets.indexOf(i);
        int indexJ = pets.indexOf(j);
        if (indexI != -1 && indexJ != -1) {
            swapPets(indexI, indexJ);
        }
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(pets);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        Class<?>[] classes = new Class<?>[] { Pet.class, Mammal.class, Bird.class, Dog.class, Cat.class, Parrot.class, Owner.class };

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        pets = (ArrayList<Pet>) in.readObject();
        in.close();
    }
}
