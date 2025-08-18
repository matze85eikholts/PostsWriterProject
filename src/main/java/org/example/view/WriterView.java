package org.example.view;
import java.util.Scanner;

public class WriterView {
    private final WriterController controller = new WriterController();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== Writer Menu ===");
            System.out.println("1. Create");
            System.out.println("2. List all");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> controller.create();
                case "2" -> controller.readAll();
                case "3" -> controller.update();
                case "4" -> controller.delete();
                case "5" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}