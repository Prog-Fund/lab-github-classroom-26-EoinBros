package models;

import utils.Utilities;

public abstract class Pet {

    private int age = 0;
    private int id = 0;
    private String name = "";
    private boolean[] daysAttending = new boolean[7];
    private Owner owner = null;
    private String temperament = "";

    public Pet(String name, int age, Owner owner, int id) {
        setName(name);
        setAge(age);
        setOwner(owner);
        setId(id);
    }

    public void checkIn(int dayIndex) {
        if (Utilities.validIntRange(dayIndex, 0, 6)) {
            daysAttending[dayIndex] = true;
        }
    }

    public void checkOut(int dayIndex) {
        if (Utilities.validIntRange(dayIndex, 0, 6)) {
            daysAttending[dayIndex] = false;
        }
    }

    public boolean isAttendingOnDay(int dayIndex) {
        if (Utilities.validIntRange(dayIndex, 0, 6)) {
            return daysAttending[dayIndex];
        }
        return false;
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
        return numOfDaysAttending() * 10;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = Utilities.truncateString(name, 30);
        }
    }

    public void setAge(int age) {
        if (Utilities.validIntRange(age, 0, 99)) {
            this.age = age;
        }
    }

    public void setId(int id) {
        if (id >= 1) {
            this.id = id;
        }
    }

    public void setOwner(Owner owner) {
        if (owner != null) {
            this.owner = owner;
        }
    }

    public void setDaysAttending(boolean[] daysAttending) {
        if (daysAttending != null && daysAttending.length == 7) {
            this.daysAttending = daysAttending;
        }
    }

    public void setTemperament(String temperament) {
        if (temperament != null) {
            this.temperament = Utilities.truncateString(temperament.trim(), 20);
        }
    }

    @Override
    public String toString() {
        return "id=" + id
                + ", name='" + name + "'"
                + ", age=" + age
                + ", owner=" + owner
                + ", temperament='" + temperament + "'"
                + ", daysAttending=" + numOfDaysAttending()
                + ", weeklyFee=" + calculateWeeklyFee();
    }
}
