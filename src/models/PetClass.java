package models;

public class PetClass {
    protected String name;
    protected int age;
    protected String ownerName;

    public PetClass(String name, int age, String ownerName) {
        this.name = name;
        this.age = age;
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOwnerName() {
        return ownerName;
    }
}