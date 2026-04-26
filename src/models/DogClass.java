package models;

public class DogClass extends MammalClass {
    private String size;

    public DogClass(String name, int age, String ownerName, boolean neutered, String size) {
        super(name, age, ownerName, neutered);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public double calculateFee() {
        if (size.equals("large")) {
            return 30;
        } else if (size.equals("medium")) {
            return 25;
        } else {
            return 20;
        }
    }
}