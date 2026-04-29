package models;

import utils.Utilities;

public class Bird extends Pet {

    private double wingSpan = 0;
    private boolean canFly = true;

    public Bird(String name, int age, Owner owner, int id, double wingSpan, boolean canFly) {
        super(name, age, owner, id);
        setWingSpan(wingSpan);
        setCanFly(canFly);
    }

    public void setWingSpan(double wingSpan) {
        if (Utilities.validRange(wingSpan, 0, 500)) {
            this.wingSpan = wingSpan;
        }
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = canFly ? 8 : 10;
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public String toString() {
        return "Bird{" + super.toString()
                + ", wingSpan=" + wingSpan
                + ", canFly=" + canFly
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        if (Double.compare(bird.wingSpan, wingSpan) != 0) return false;
        if (canFly != bird.canFly) return false;
        if (getId() != bird.getId()) return false;
        if (getAge() != bird.getAge()) return false;
        if (getName() != null ? !getName().equals(bird.getName()) : bird.getName() != null) return false;
        return getOwner() != null ? getOwner().equals(bird.getOwner()) : bird.getOwner() == null;
    }
}
