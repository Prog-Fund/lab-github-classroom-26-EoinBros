package models;

public class Parrot extends Bird {

    private int socialisationNeeds = 3;  // 1 (low) to 5 (high)
    private int enrichmentNeeds = 3;     // 1 (low) to 5 (high)

    public Parrot(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int socialisationNeeds, int enrichmentNeeds) {
        super(name, age, owner, id, wingSpan, canFly);
        setSocialisationNeeds(socialisationNeeds);
        setEnrichmentNeeds(enrichmentNeeds);
    }

    public int getSocialisationNeeds() {
        return socialisationNeeds;
    }

    public int getEnrichmentNeeds() {
        return enrichmentNeeds;
    }

    public void setSocialisationNeeds(int socialisationNeeds) {
        if (socialisationNeeds >= 1 && socialisationNeeds <= 5) {
            this.socialisationNeeds = socialisationNeeds;
        }
    }

    public void setEnrichmentNeeds(int enrichmentNeeds) {
        if (enrichmentNeeds >= 1 && enrichmentNeeds <= 5) {
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
                + "}";
    }
}
