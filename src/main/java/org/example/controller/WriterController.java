package org.example.controller;

import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.GsonWriterRepositoryImpl;
import org.example.repository.WriterRepository;

import java.util.Scanner;

public class WriterController {
    private final WriterRepository repository = new GsonWriterRepositoryImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void create() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        Writer writer = new Writer(null, firstName, lastName);
        repository.save(writer);
        System.out.println("Writer created with ID: " + writer.getId());
    }

    public void readAll() {
        repository.getAll().forEach(System.out::println);
    }

    public void update() {
        System.out.print("Writer ID to update: ");
        Long id = Long.parseLong(scanner.nextLine());
        Writer writer = repository.getById(id);
        if (writer == null || writer.getStatus() == Status.DELETED) {
            System.out.println("Writer not found.");
            return;
        }
        System.out.print("New first name (" + writer.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.trim().isEmpty()) writer.setFirstName(firstName);

        System.out.print("New last name (" + writer.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.trim().isEmpty()) writer.setLastName(lastName);

        repository.update(writer);
        System.out.println("Writer updated.");
    }

    public void delete() {
        System.out.print("Writer ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        repository.deleteById(id);
        System.out.println("Writer marked as deleted.");
    }
}
