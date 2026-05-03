package models;

public class Cat extends Mammal {

    private String favouriteToy = "";
    private boolean indoorCat = false;

    public Cat(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, boolean indoorCat, String favouriteToy) {
        super(name, age, owner, id, sex, neutered, weight, vaccinated);
        setIndoorCat(indoorCat);
        setFavouriteToy(favouriteToy);
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    public boolean isIndoorCat() {
        return indoorCat;
    }

    public void setFavouriteToy(String favouriteToy) {
        if (favouriteToy != null) {
            this.favouriteToy = favouriteToy;
        }
    }

    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = indoorCat ? 12 : 14;
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return getId() == cat.getId()
                && getAge() == cat.getAge()
                && getSex() == cat.getSex()
                && isNeutered() == cat.isNeutered()
                && Double.compare(getWeight(), cat.getWeight()) == 0
                && isVaccinated() == cat.isVaccinated()
                && indoorCat == cat.indoorCat
                && favouriteToy.equals(cat.favouriteToy)
                && getName().equals(cat.getName());
    }

    @Override
    public String toString() {
        return "Cat{" + super.toString()
                + ", favouriteToy='" + favouriteToy + "'"
                + ", indoorCat=" + indoorCat
                + "}";
    }
}
