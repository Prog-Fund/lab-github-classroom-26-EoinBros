package models;

public class Dog extends Mammal {

    public static final double SMALL_DAILY_RATE = 12;
    public static final double MEDIUM_DAILY_RATE = 15;
    public static final double LARGE_DAILY_RATE = 18;
    public static final double XL_DAILY_RATE = 22;

    private String breed = "";
    private int size = 2;  // 1=SMALL, 2=MEDIUM, 3=LARGE, 4=XL
    private boolean dangerousBreed = false;

    public Dog(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, String breed, int size) {
        super(name, age, owner, id, sex, neutered, weight, vaccinated);
        setBreed(breed);
        setSize(size);
    }

    public Dog(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, String breed, boolean dangerousBreed) {
        this(name, age, owner, id, sex, neutered, weight, vaccinated, breed, 2);
        setDangerousBreed(dangerousBreed);
    }

    public String getBreed() {
        return breed;
    }

    public int getSize() {
        return size;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public void setBreed(String breed) {
        if (breed != null) {
            this.breed = breed;
        }
    }

    public void setSize(int size) {
        if (size >= 1 && size <= 4) {
            this.size = size;
        }
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = MEDIUM_DAILY_RATE;
        if (size == 1) dailyRate = SMALL_DAILY_RATE;
        if (size == 3) dailyRate = LARGE_DAILY_RATE;
        if (size == 4) dailyRate = XL_DAILY_RATE;
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public String toString() {
        return "Dog{" + super.toString()
                + ", breed='" + breed + "'"
                + ", size=" + size
                + ", dangerousBreed=" + dangerousBreed
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return getId() == dog.getId()
                && getAge() == dog.getAge()
                && getSex() == dog.getSex()
                && isNeutered() == dog.isNeutered()
                && Double.compare(getWeight(), dog.getWeight()) == 0
                && isVaccinated() == dog.isVaccinated()
                && size == dog.size
                && dangerousBreed == dog.dangerousBreed
                && breed.equals(dog.breed)
                && getName().equals(dog.getName());
    }
}
