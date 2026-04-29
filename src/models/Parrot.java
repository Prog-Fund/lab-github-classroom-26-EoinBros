package models;

import utils.Utilities;

public class Parrot extends Bird {

    private String vocabularySize = "0";

    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int vocabularySize) {
        super(name, age, owner, id, wingSpan, canFly);
        setVocabularySize(vocabularySize);
    }

    public void setVocabularySize(int vocabularySize) {
        if (Utilities.validIntRange(vocabularySize, 0, 10000)) {
            this.vocabularySize = String.valueOf(vocabularySize);
        }
    }

    public String getVocabularySize() {
        return vocabularySize;
    }

    @Override
    public double calculateWeeklyFee() {
        int vocab = 0;
        try {
            vocab = Integer.parseInt(vocabularySize);
        } catch (NumberFormatException e) {
            vocab = 0;
        }

        double dailyRate = 10;
        if (vocab > 100) {
            dailyRate = 12;
        }
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public String toString() {
        return "Parrot{" + super.toString()
                + ", vocabularySize=" + vocabularySize
                + '}';
    }
}
