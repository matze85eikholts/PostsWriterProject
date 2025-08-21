package org.example.view;

import org.example.controller.PostController;
import org.example.model.Post;

import java.util.Scanner;

public class PostView {
    private final PostController controller = new PostController();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== Post Menu ===");
            System.out.println("1. Create Post");
            System.out.println("2. List All Posts");
            System.out.println("3. Update Post");
            System.out.println("4. Delete Post");
            System.out.println("5. Find Post by ID");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option (1-6): ");

            String input = scanner.nextLine().trim();

            try {
                switch (input) {
                    case "1":
                        createPost();
                        break;
                    case "2":
                        listAllPosts();
                        break;
                    case "3":
                        updatePost();
                        break;
                    case "4":
                        deletePost();
                        break;
                    case "5":
                        findPostById();
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
            System.out.println(); // Пустая строка для читаемости
        }
    }

    private void createPost() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Enter content: ");
        String content = scanner.nextLine().trim();
        if (content.isEmpty()) {
            System.out.println("Content cannot be empty.");
            return;
        }

        Post post = new Post(null, title, content);
        controller.create(post);
        System.out.println("✅ Post created successfully with ID: " + post.getId());
    }

    private void listAllPosts() {
        System.out.println("📋 All Active Posts:");
        var posts = controller.getAll();
        if (posts.isEmpty()) {
            System.out.println("No active posts found.");
        } else {
            posts.forEach(System.out::println);
        }
    }

    private void findPostById() {
        System.out.print("Enter Post ID: ");
        String idStr = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(idStr);
            Post post = controller.getById(id);
            if (post != null) {
                System.out.println("🔍 Found Post: " + post);
            } else {
                System.out.println("❌ Post with ID " + id + " not found or has been deleted.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        }
    }

    private void updatePost() {
        System.out.print("Enter Post ID to update: ");
        String idStr = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(idStr);
            Post existing = controller.getById(id);
            if (existing == null) {
                System.out.println("❌ Post not found or already deleted.");
                return;
            }

            System.out.println("Current title: " + existing.getTitle());
            System.out.print("New title (press Enter to keep current): ");
            String newTitle = scanner.nextLine().trim();
            if (!newTitle.isEmpty()) {
                existing.setTitle(newTitle);
            }

            System.out.println("Current content: " + existing.getContent());
            System.out.print("New content (press Enter to keep current): ");
            String newContent = scanner.nextLine().trim();
            if (!newContent.isEmpty()) {
                existing.setContent(newContent);
            }

            // В будущем можно добавить редактирование меток
            System.out.print("Keep existing labels? (y/n, default: y): ");
            String keepLabels = scanner.nextLine().trim().toLowerCase();
            if (keepLabels.equals("n")) {
                System.out.println("⚠️ Label management is not implemented in this version.");
            }

            controller.update(existing);
            System.out.println("✅ Post updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format.");
        }
    }

    private void deletePost() {
        System.out.print("Enter Post ID to delete: ");
        String idStr = scanner.nextLine().trim();
        try {
            Long id = Long.parseLong(idStr);
            controller.delete(id);
            System.out.println("✅ Post with ID " + id + " marked as DELETED.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID format. Please enter a valid number.");
        }
    }
}