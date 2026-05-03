# Reflection

Name:Eoin Brosnan

Student Number:20117944

---

## Chronology of Implementation

I started by building the model class hierarchy, beginning with the base Pet class and working my way down. I created Pet first, then the two abstract middle classes Mammal and Bird, and finally the three concrete classes Dog, Cat, and Parrot. I made sure each subclass called super() in its constructor to pass the common fields up the chain.

Once the models were in place, I moved on to the DayCare controller. I started with the basic CRUD methods (add, delete, get) and then added the listing and reporting methods. After that I added the check-in/check-out system and the weekly fee calculations.

With the controller finished, I built the Driver class with a menu system. I started with the main menu and then added sub-menus for CRUD, reports, staff check-in/out, and sorting.

Finally, I wrote the JUnit tests. I wrote tests for the DayCare controller first, then added separate test classes for Bird and Parrot to cover their specific fields and fee calculations.

---

## Main Difficulties

**1. Abstract classes**
Understanding when to use abstract classes took some time. I initially made Pet, Mammal, and Bird as regular classes, which compiled fine but was not correct for the design. Once I understood that these classes should never be instantiated directly, making them abstract made sense.

**2. Day index off-by-one bug**
The check-in/check-out menu showed the user days 1-7, but the daysAttending array inside Pet uses 0-6 indexing. Early on I was passing the user's input directly to checkIn(), which meant Sunday (day 7) silently did nothing because index 7 was out of range. The fix was to subtract 1 from the user's input before passing it to the method.

**3. The fees calculation per animal type**
Each animal type has a different daily rate and some have rates that depend on their specific fields (e.g. a Parrot's rate depends on its socialisation and enrichment needs, a Dog's rate depends on its size). Getting the calculateWeeklyFee() override right in each subclass required careful reading of the spec.

**4. Writing JUnit tests**
Writing tests was new to me, particularly knowing what to test. I learned to focus on valid inputs, invalid inputs (boundary cases), and empty states (e.g. an empty day care returning "No Pets").

---

## Remaining Bugs and Unfinished Elements

- The update option in the Driver only updates name, age, and temperament. It does not allow updating type-specific fields such as a dog's breed or a parrot's socialisation needs.
- There is no search by name in the Driver - only search by ID is supported.
- The save/load functionality has not been fully tested end-to-end with all animal types.

---

## Main Learnings

- Inheritance and abstract classes - I now understand how to design a hierarchy where common behaviour lives in the parent class and specific behaviour is overridden in subclasses. Using instanceof to check the type at runtime was also new to me.
- Polymorphism - storing Dog, Cat, and Parrot objects in a single ArrayList of Pet and calling calculateWeeklyFee() on each one, getting the correct result for each type, made polymorphism click for me.
- Encapsulation and validation - putting validation inside setters (e.g. rejecting a socialisation needs value outside 1-5) means the object can never be in an invalid state, which made writing tests much easier.
- JUnit testing - I learned to write a BeforeEach setup method to avoid repeating code across tests, and to write separate test methods for each case rather than one long test.

## Referncea
- I specifically uses Ai (ChatGPT) to generate and solve the test classes as well as the following as i wasent able to get it to compile no matter what a tried
- Parrot fee formula — Parrot.java line 22
- XStream save/load — DayCare.java around line 355
- BirdTest using Parrot — BirdTest.java
- The day index fix — Driver.java around line 215

- This is my work apart from the specific references noted above (and any code from class notes). I understand the code and can decribe any parts of the solution if needs be;
     