package models;

import utils.Utilities;

public class Dog extends Mammal {

    public static final float NONDANGEROUS_DAILY_RATE = 15;
    public static final float DANGEROUS_DAILY_RATE = 25;

    private boolean dangerousBreed = false;
    private String breed = "";

    public Dog(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, String breed, boolean dangerousBreed) {
        super(name, age, owner, id, sex, neutered, weight, vaccinated);
        setBreed(breed);
        setDangerousBreed(dangerousBreed);
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = dangerousBreed ? DANGEROUS_DAILY_RATE : NONDANGEROUS_DAILY_RATE;
        return numOfDaysAttending() * dailyRate;
    }

    public void setBreed(String breed) {
        this.breed = Utilities.truncateString(breed, 30);
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Dog{" + super.toString()
                + ", breed='" + breed + '\''
                + ", dangerousBreed=" + dangerousBreed
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        if (dangerousBreed != dog.dangerousBreed) return false;
        if (getId() != dog.getId()) return false;
        if (getAge() != dog.getAge()) return false;
        if (getSex() != dog.getSex()) return false;
        if (isNeutered() != dog.isNeutered()) return false;
        if (Double.compare(dog.getWeight(), getWeight()) != 0) return false;
        if (isVaccinated() != dog.isVaccinated()) return false;
        if (breed != null ? !breed.equals(dog.breed) : dog.breed != null) return false;
        if (getName() != null ? !getName().equals(dog.getName()) : dog.getName() != null) return false;
        return getOwner() != null ? getOwner().equals(dog.getOwner()) : dog.getOwner() == null;
    }
}
