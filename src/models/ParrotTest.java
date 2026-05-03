package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParrotTest {

    private Parrot parrot;
    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner("Bob", "0861234567");
        parrot = new Parrot("Polly", 3, owner, 1, 0.4, true, 3, 3);
    }

    // ---- Constructor / Getters ----

    @Test
    void constructor_setsNameCorrectly() {
        assertEquals("Polly", parrot.getName());
    }

    @Test
    void constructor_setsSocialisationNeedsCorrectly() {
        assertEquals(3, parrot.getSocialisationNeeds());
    }

    @Test
    void constructor_setsEnrichmentNeedsCorrectly() {
        assertEquals(3, parrot.getEnrichmentNeeds());
    }

    @Test
    void constructor_setsWingSpanCorrectly() {
        assertEquals(0.4, parrot.getWingSpan(), 0.001);
    }

    // ---- setSocialisationNeeds ----

    @Test
    void setSocialisationNeeds_validValue_updates() {
        parrot.setSocialisationNeeds(5);
        assertEquals(5, parrot.getSocialisationNeeds());
    }

    @Test
    void setSocialisationNeeds_belowMin_noChange() {
        parrot.setSocialisationNeeds(0);
        assertEquals(3, parrot.getSocialisationNeeds());
    }

    @Test
    void setSocialisationNeeds_aboveMax_noChange() {
        parrot.setSocialisationNeeds(6);
        assertEquals(3, parrot.getSocialisationNeeds());
    }

    @Test
    void setSocialisationNeeds_boundary1_isAllowed() {
        parrot.setSocialisationNeeds(1);
        assertEquals(1, parrot.getSocialisationNeeds());
    }

    // ---- setEnrichmentNeeds ----

    @Test
    void setEnrichmentNeeds_validValue_updates() {
        parrot.setEnrichmentNeeds(4);
        assertEquals(4, parrot.getEnrichmentNeeds());
    }

    @Test
    void setEnrichmentNeeds_belowMin_noChange() {
        parrot.setEnrichmentNeeds(0);
        assertEquals(3, parrot.getEnrichmentNeeds());
    }

    @Test
    void setEnrichmentNeeds_aboveMax_noChange() {
        parrot.setEnrichmentNeeds(6);
        assertEquals(3, parrot.getEnrichmentNeeds());
    }

    // ---- calculateWeeklyFee ----

    @Test
    void calculateWeeklyFee_zeroDays_returnsZero() {
        assertEquals(0, parrot.calculateWeeklyFee(), 0.001);
    }

    @Test
    void calculateWeeklyFee_oneDayMinNeeds_correctRate() {
        // socialisationNeeds=1, enrichmentNeeds=1 → dailyRate = 10 + 0 + 0 = 10
        parrot.setSocialisationNeeds(1);
        parrot.setEnrichmentNeeds(1);
        parrot.checkIn(0);
        assertEquals(10.0, parrot.calculateWeeklyFee(), 0.001);
    }

    @Test
    void calculateWeeklyFee_twoDaysMaxNeeds_correctRate() {
        // socialisationNeeds=5, enrichmentNeeds=5 → dailyRate = 10 + 4 + 4 = 18
        parrot.setSocialisationNeeds(5);
        parrot.setEnrichmentNeeds(5);
        parrot.checkIn(0);
        parrot.checkIn(1);
        assertEquals(36.0, parrot.calculateWeeklyFee(), 0.001);
    }

    @Test
    void calculateWeeklyFee_threeDaysDefaultNeeds_correctRate() {
        // socialisationNeeds=3, enrichmentNeeds=3 → dailyRate = 10 + 2 + 2 = 14
        parrot.checkIn(0);
        parrot.checkIn(1);
        parrot.checkIn(2);
        assertEquals(42.0, parrot.calculateWeeklyFee(), 0.001);
    }

    // ---- toString ----

    @Test
    void toString_containsSocialisationNeeds() {
        assertTrue(parrot.toString().contains("socialisationNeeds"));
    }

    @Test
    void toString_containsEnrichmentNeeds() {
        assertTrue(parrot.toString().contains("enrichmentNeeds"));
    }

    @Test
    void toString_containsParrot() {
        assertTrue(parrot.toString().startsWith("Parrot{"));
    }
}
