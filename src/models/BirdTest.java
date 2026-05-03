package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BirdTest {

    // Bird is abstract so we test its properties through Parrot (Parrot IS-A Bird)
    private Parrot bird;
    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner("Alice", "0851234567");
        bird = new Parrot("Tweety", 2, owner, 1, 0.5, true, 3, 3);
    }

    // ---- Constructor / Getters ----

    @Test
    void constructor_setsWingSpanCorrectly() {
        assertEquals(0.5, bird.getWingSpan(), 0.001);
    }

    @Test
    void constructor_setsCanFlyCorrectly() {
        assertTrue(bird.isCanFly());
    }

    @Test
    void constructor_setsNameCorrectly() {
        assertEquals("Tweety", bird.getName());
    }

    @Test
    void constructor_setsAgeCorrectly() {
        assertEquals(2, bird.getAge());
    }

    // ---- setWingSpan ----

    @Test
    void setWingSpan_validValue_updatesWingSpan() {
        bird.setWingSpan(1.2);
        assertEquals(1.2, bird.getWingSpan(), 0.001);
    }

    @Test
    void setWingSpan_negativeValue_noChange() {
        bird.setWingSpan(-1.0);
        assertEquals(0.5, bird.getWingSpan(), 0.001);
    }

    @Test
    void setWingSpan_tooLarge_noChange() {
        bird.setWingSpan(600);
        assertEquals(0.5, bird.getWingSpan(), 0.001);
    }

    @Test
    void setWingSpan_zero_isAllowed() {
        bird.setWingSpan(0);
        assertEquals(0, bird.getWingSpan(), 0.001);
    }

    // ---- setCanFly ----

    @Test
    void setCanFly_false_updatesCanFly() {
        bird.setCanFly(false);
        assertFalse(bird.isCanFly());
    }

    @Test
    void setCanFly_true_updatesCanFly() {
        bird.setCanFly(false);
        bird.setCanFly(true);
        assertTrue(bird.isCanFly());
    }

    // ---- toString ----

    @Test
    void toString_containsWingSpan() {
        assertTrue(bird.toString().contains("wingSpan"));
    }

    @Test
    void toString_containsCanFly() {
        assertTrue(bird.toString().contains("canFly"));
    }
}
