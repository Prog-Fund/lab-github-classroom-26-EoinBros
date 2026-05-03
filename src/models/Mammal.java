package models;

import utils.Utilities;

public abstract class Mammal extends Pet {

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

    public char getSex() {
        return sex;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setSex(char sex) {
        if (sex == 'M' || sex == 'F' || sex == 'U') {
            this.sex = sex;
        }
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public void setWeight(double weight) {
        if (weight >= 0 && weight <= 500) {
            this.weight = weight;
        }
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", sex=" + sex
                + ", neutered=" + neutered
                + ", weight=" + weight
                + ", vaccinated=" + vaccinated;
    }
}
