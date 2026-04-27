import controllers.PetController;
import models.DogClass;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        PetController controller = new PetController();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (choice != 3) {
            System.out.println("\n1. Add Dog");
            System.out.println("2. List Dogs");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            if (choice == 1) {
                System.out.print("Enter dog name: ");
                String name = scanner.nextLine();

                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter owner name: ");
                String owner = scanner.nextLine();

                System.out.print("Is neutered (true/false): ");
                boolean neutered = scanner.nextBoolean();
                scanner.nextLine();

                System.out.print("Enter size (small/medium/large): ");
                String size = scanner.nextLine();

                DogClass dog = new DogClass(name, age, owner, neutered, size);
                controller.addPet(dog);

                System.out.println("Dog added!");

            } else if (choice == 2) {
                controller.listPets();
            }
        }

        System.out.println("Goodbye!");
    }
}