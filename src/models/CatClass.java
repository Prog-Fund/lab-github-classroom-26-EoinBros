package models;

public class CatClass extends MammalClass {

    private boolean indoor;

    public CatClass(String name, int age, String ownerName, boolean neutered, boolean indoor) {
        super(name, age, ownerName, neutered);
        this.indoor = indoor;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    @Override
    public double calculateFee() {
        if (indoor) {
            return 15;
        }
        else {
            return 20;
        }
    }

    @Override
    public String toString() {
        return "Cat - " + super.toString()
                + ", Neutered: " + neutered
                + ", Indoor: " + indoor;
    }
}
