package models;

import utils.Utilities;

public class Mammal extends Pet {

    private char sex = 'U';
    private boolean neutered = false;
    private double weight = 0;
    private boolean vaccinated = false;

    public Mammal(String name, int age, Owner owner, int id, char sex, boolean neutered, double weight, boolean vaccinated) {
        super(name, age, owner, id);
        setSex(sex);
        setNeutered(neutered);
        setWeight(weight);
        setVaccinated(vaccinated);
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public double getWeight() {
        return weight;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public void setWeight(double weight) {
        if (Utilities.validRange(weight, 0, 500)) {
            this.weight = weight;
        }
    }

    public void setSex(char sex) {
        if (Utilities.validChar(sex, 'M', 'F', 'U')) {
            this.sex = sex;
        }
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public char getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Mammal{" + super.toString()
                + ", sex=" + sex
                + ", neutered=" + neutered
                + ", weight=" + weight
                + ", vaccinated=" + vaccinated
                + '}';
    }
}
