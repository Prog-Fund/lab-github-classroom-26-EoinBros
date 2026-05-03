package models;

import utils.Utilities;

public class Parrot extends Bird {

    private int socialisationNeeds = 3; // 1 (low) .. 5 (high)
    private int enrichmentNeeds = 3;    // 1 (low) .. 5 (high)
    // Kept for backwards compatibility with older code/reports/data; no longer used for care/fees.
    private int vocabularySize = 0;

    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int socialisationNeeds, int enrichmentNeeds) {
        super(name, age, owner, id, wingSpan, canFly);
        setSocialisationNeeds(socialisationNeeds);
        setEnrichmentNeeds(enrichmentNeeds);
    }

    // Backwards-compatible constructor signature (old "vocabulary size" argument).
    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int vocabularySize) {
        this(name, age, owner, id, wingSpan, canFly, 3, 3);
        setVocabularySize(vocabularySize);
    }

    public int getVocabularySize() {
        return vocabularySize;
    }

    public void setVocabularySize(int vocabularySize) {
        if (Utilities.validIntRange(vocabularySize, 0, 10000)) {
            this.vocabularySize = vocabularySize;
        }
    }

    public int getSocialisationNeeds() {
        return socialisationNeeds;
    }

    public void setSocialisationNeeds(int socialisationNeeds) {
        if (Utilities.validIntRange(socialisationNeeds, 1, 5)) {
            this.socialisationNeeds = socialisationNeeds;
        }
    }

    public int getEnrichmentNeeds() {
        return enrichmentNeeds;
    }

    public void setEnrichmentNeeds(int enrichmentNeeds) {
        if (Utilities.validIntRange(enrichmentNeeds, 1, 5)) {
            this.enrichmentNeeds = enrichmentNeeds;
        }
    }

    @Override
    public double calculateWeeklyFee() {
        double dailyRate = 10 + (socialisationNeeds - 1) + (enrichmentNeeds - 1);
        return numOfDaysAttending() * dailyRate;
    }

    @Override
    public String toString() {
        return "Parrot{" + super.toString()
                + ", socialisationNeeds=" + socialisationNeeds
                + ", enrichmentNeeds=" + enrichmentNeeds
                + '}';
    }
}
