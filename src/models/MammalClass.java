package models;

public class MammalClass extends PetClass {
    protected boolean neutered;

    public MammalClass(String name, int age, String ownerName, boolean neutered) {
        super(name, age, ownerName);
        this.neutered = neutered;
    }

    public boolean isNeutered() {
        return neutered;
    }
}