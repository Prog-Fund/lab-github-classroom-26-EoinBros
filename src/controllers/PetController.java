package controllers;

import models.*;
import java.util.ArrayList;

    public class PetController {

        private ArrayList<PetClass> pets;

        public PetController() {
            pets = new ArrayList<>();
        }

        public void addPet(PetClass pet) {
            pets.add(pet);
        }

        public void listPets() {
            for (PetClass pet : pets) {
                System.out.println(pet.getName() + " - " + pet.getOwnerName());
            }
        }
    }
