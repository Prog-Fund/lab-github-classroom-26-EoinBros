package models;

import utils.Utilities;

public class Dog extends Mammal {

    public static final float SMALL_DAILY_RATE = 12;
    public static final float MEDIUM_DAILY_RATE = 15;
    public static final float LARGE_DAILY_RATE = 18;
    public static final float XL_DAILY_RATE = 22;

    private boolean dangerousBreed = false;
    private String breed = "";
    private DogSize size = DogSize.MEDIUM;

    public Dog(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, String breed, DogSize size) {
        super(name, age, owner, id, sex, neutered, weight, vaccinated);
        setBreed(breed);
        setSize(size);
    }

    public Dog(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, String breed, boolean dangerousBreed) {
        this(name, age, owner, id, sex, neutered, weight, vaccinated, breed, dangerousBreed ? DogSize.XL : DogSize.MEDIUM);
        // Keep legacy flag for backwards compatibility with existing code/reports.
        setDangerousBreed(dangerousBreed);
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public DogSize getSize() {
        return size;
    }

    public void setSize(DogSize size) {
        if (size != null) {
            this.size = size;
        }
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = MEDIUM_DAILY_RATE;
        if (size == DogSize.SMALL) dailyRate = SMALL_DAILY_RATE;
        if (size == DogSize.LARGE) dailyRate = LARGE_DAILY_RATE;
        if (size == DogSize.XL) dailyRate = XL_DAILY_RATE;
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
                + ", size=" + size
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
        if (size != dog.size) return false;
        if (breed != null ? !breed.equals(dog.breed) : dog.breed != null) return false;
        if (getName() != null ? !getName().equals(dog.getName()) : dog.getName() != null) return false;
        return getOwner() != null ? getOwner().equals(dog.getOwner()) : dog.getOwner() == null;
    }
}
