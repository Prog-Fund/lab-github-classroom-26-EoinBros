import controllers.PetController;
import models.BirdClass;
import models.CatClass;
import models.DogClass;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        PetController controller = new PetController();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (choice != 5) {
            System.out.println("1. Add Dog");
            System.out.println("2. Add Cat");
            System.out.println("3. Add Bird");
            System.out.println("4. List Pets");
            System.out.println("5. Exit");
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
                System.out.print("Enter cat name: ");
                String name = scanner.nextLine();

                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter owner name: ");
                String owner = scanner.nextLine();

                System.out.print("Is neutered (true/false): ");
                boolean neutered = scanner.nextBoolean();
                scanner.nextLine();

                System.out.print("Is indoor (true/false): ");
                boolean indoor = scanner.nextBoolean();
                scanner.nextLine();

                CatClass cat = new CatClass(name, age, owner, neutered, indoor);
                controller.addPet(cat);

                System.out.println("Cat added!");

            } else if (choice == 3) {
                System.out.print("Enter bird name: ");
                String name = scanner.nextLine();

                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter owner name: ");
                String owner = scanner.nextLine();

                System.out.print("Can fly (true/false): ");
                boolean canFly = scanner.nextBoolean();
                scanner.nextLine();

                BirdClass bird = new BirdClass(name, age, owner, canFly);
                controller.addPet(bird);

                System.out.println("Bird added!");

            } else if (choice == 4) {
                controller.listPets();
            }
        }

        System.out.println("Goodbye!");
    }
}
