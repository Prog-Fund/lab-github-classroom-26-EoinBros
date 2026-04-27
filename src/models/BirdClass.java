package models;

public class BirdClass extends PetClass {

    private boolean canFly;

    public BirdClass(String name, int age, String ownerName, boolean canFly) {
        super(name, age, ownerName);
        this.canFly = canFly;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public double calculateFee() {
        if (canFly) {
            return 10;
        }
        else {
            return 12;
        }
    }

    @Override
    public String toString() {
        return "Bird - " + super.toString()
                + ", Can fly: " + canFly;
    }
}
