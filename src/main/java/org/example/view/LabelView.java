package org.example.view;

import org.example.controller.LabelController;
import org.example.model.Label;

import java.util.Scanner;

public class LabelView {
    private final LabelController controller = new LabelController();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== Label Menu ===");
            System.out.println("1. Create Label");
            System.out.println("2. List All Labels");
            System.out.println("3. Update Label");
            System.out.println("4. Delete Label");
            System.out.println("5. Find Label by ID");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option (1-6): ");

            String input = scanner.nextLine().trim();

            try {
                switch (input) {
                    case "1":
                        createLabel();
                        break;
                    case "2":
                        listAllLabels();
                        break;
                    case "3":
                        updateLabel();
                        break;
                    case "4":
                        deleteLabel();
                        break;
                    case "5":
                        findLabelById();
                        break;
                    case "6":
                        System.out.println("Returning to main menu...");
                        return;
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ An error occurred: " + e.getMessage());
            }
            System.out.println(); // Для визуального разделения
        }
    }

    private void createLabel() {
        System.out.print("Enter label name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("❌ Label name cannot be empty.");
            return;
        }

        Label label = new Label(null, name);
        controller.create(label);
        System.out.println("✅ Label created successfully with ID: " + label.getId());
    }

    private void listAllLabels() {
        System.out.println("📋 All Active Labels:");
        var labels = controller.getAll();
        if (labels.isEmpty()) {
            System.out.println("No active labels found.");
        } else {
            labels.forEach(System.out::println);
        }
    }

    private void findLabelById() {
        System.out.print("Enter Label ID: ");
        String idStr = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(idStr);
            Label label = controller.getById(id);
            if (label != null) {
                System.out.println("🔍 Found Label: " + label);
            } else {
                System.out.println("❌ Label with ID " + id + " not found or has been deleted.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        }
    }

    private void updateLabel() {
        System.out.print("Enter Label ID to update: ");
        String idStr = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(idStr);
            Label existing = controller.getById(id);
            if (existing == null) {
                System.out.println("❌ Label not found or already deleted.");
                return;
            }

            System.out.println("Current name: " + existing.getName());
            System.out.print("New name (press Enter to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                if (newName.isEmpty()) {
                    System.out.println("❌ Name cannot be empty.");
                    return;
                }
                existing.setName(newName);
            }

            controller.update(existing);
            System.out.println("✅ Label updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format.");
        }
    }

    private void deleteLabel() {
        System.out.print("Enter Label ID to delete: ");
        String idStr = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(idStr);
            controller.delete(id);
            System.out.println("✅ Label with ID " + id + " marked as DELETED.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        }
    }
}