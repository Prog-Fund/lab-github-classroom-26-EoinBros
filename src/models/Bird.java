package models;

public abstract class Bird extends Pet {

    private double wingSpan = 0;
    private boolean canFly = true;

    public Bird(String name, int age, Owner owner, int id, double wingSpan, boolean canFly) {
        super(name, age, owner, id);
        setWingSpan(wingSpan);
        setCanFly(canFly);
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setWingSpan(double wingSpan) {
        if (wingSpan >= 0 && wingSpan <= 500) {
            this.wingSpan = wingSpan;
        }
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = canFly ? 8 : 10;
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", wingSpan=" + wingSpan
                + ", canFly=" + canFly;
    }
}
