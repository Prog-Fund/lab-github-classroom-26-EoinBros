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

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public double calculateFee() {
        if (size == null) {
            return 20;
        }
        String normalizedSize = size.toLowerCase();
        if (normalizedSize.equals("large")) {
            return 30;
        } else if (normalizedSize.equals("medium")) {
            return 25;
        } else {
            return 20;
        }
    }

    @Override
    public String toString() {
        return "Dog - " + super.toString()
                + ", Neutered: " + neutered
                + ", Size: " + size;
    }
}
