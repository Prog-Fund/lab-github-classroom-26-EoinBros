package models;

public abstract class PetClass {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public abstract double calculateFee();

    @Override
    public String toString() {
        return "Name: " + name
                + ", Age: " + age
                + ", Owner: " + ownerName
                + ", Fee: " + calculateFee();
    }
}
