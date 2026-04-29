package models;

import utils.Utilities;

public class Cat extends Mammal {

    private String favouriteToy = "";
    private boolean indoorCat = false;

    public Cat(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated, boolean indoorCat, String favouriteToy) {
        super(name, age, owner, id, sex, neutered, weight, vaccinated);
        setIndoorCat(indoorCat);
        setFavouriteToy(favouriteToy);
    }

    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    public void setFavouriteToy(String favouriteToy) {
        this.favouriteToy = Utilities.truncateString(favouriteToy, 30);
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = indoorCat ? 12 : 14;
        return numOfDaysAttending() * dailyRate;
    }

    public boolean isIndoorCat() {
        return indoorCat;
    }

    @Override
    public String toString() {
        return "Cat{" + super.toString()
                + ", favouriteToy='" + favouriteToy + '\''
                + ", indoorCat=" + indoorCat
                + '}';
    }
}
