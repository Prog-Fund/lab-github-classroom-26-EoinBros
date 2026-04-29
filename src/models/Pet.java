package models;

import utils.Utilities;

public class Pet {

    private int age = 0;
    private int id = 0;
    private String name = "";
    private boolean[] daysAttending = new boolean[7];
    private Owner owner = null;

    public Pet(String name, int age, Owner owner, int id) {
        initName(name);
        setAge(age);
        setOwner(owner);
        setId(id);
    }

    public void checkOut(int dayIndex) {
        if (Utilities.validIntRange(dayIndex, 1, 7)) {
            daysAttending[dayIndex - 1] = false;
        }
    }

    public void checkIn(int dayIndex) {
        if (Utilities.validIntRange(dayIndex, 1, 7)) {
            daysAttending[dayIndex - 1] = true;
        }
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void initName(String name) {
        this.name = Utilities.truncateString(name, 30);
    }

    public void setId(int id) {
        if (Utilities.validIntRange(id, 1, Integer.MAX_VALUE)) {
            this.id = id;
        }
    }

    public void setDaysAttending(boolean[] daysAttending) {
        if (daysAttending != null && daysAttending.length == 7) {
            this.daysAttending = daysAttending;
        }
    }

    public void setName(String name) {
        if (Utilities.validateStringLength(name, 30)) {
            this.name = name;
        }
    }

    public void setOwner(Owner owner) {
        if (owner != null) {
            this.owner = owner;
        }
    }

    public void setAge(int age) {
        if (Utilities.validIntRange(age, 0, 99)) {
            this.age = age;
        }
    }

    public int numOfDaysAttending() {
        int count = 0;
        for (boolean attending : daysAttending) {
            if (attending) {
                count++;
            }
        }
        return count;
    }

    public double calculateWeeklyFee() {
        double dailyRate = 10;
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public String toString() {
        return "Pet{id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + ", owner=" + owner
                + ", daysAttending=" + numOfDaysAttending()
                + ", weeklyFee=" + calculateWeeklyFee()
                + '}';
    }
}
