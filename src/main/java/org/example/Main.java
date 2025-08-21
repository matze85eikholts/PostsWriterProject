package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.example.view.LabelView;
import org.example.view.PostView;
import org.example.view.WriterView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WriterView writerView = new WriterView();
        PostView postView = new PostView();
        LabelView labelView = new LabelView();

        while (true) {
            System.out.println("\n=== Main menu ===");
            System.out.println("1. Writers");
            System.out.println("2. Posts");
            System.out.println("3. Labels");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> writerView.start();
                case "2" -> postView1.start();
                case "3" -> labelView.start();
                case "4" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}