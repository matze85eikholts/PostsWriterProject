package org.example.controller;

import org.example.model.Label;
import org.example.model.Status;
import org.example.repository.GsonLabelRepositoryImpl;
import org.example.repository.LabelRepository;

import java.util.List;

public class LabelController {

    private final LabelRepository repository = new GsonLabelRepositoryImpl();

    /**
     * Создает новую метку.
     * @param label метка без ID (ID будет присвоен автоматически)
     */
    public void create(Label label) {
        if (label.getName() == null || label.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Label name cannot be null or empty.");
        }
        repository.save(label);
        System.out.println("✅ Label created with ID: " + label.getId());
    }

    /**
     * Возвращает список всех активных меток (со статусом ACTIVE).
     * @return список меток
     */
    public List<Label> getAll() {
        return repository.getAll();
    }

    /**
     * Находит метку по ID.
     * @param id ID метки
     * @return найденная метка или null, если не найдена или удалена
     */
    public Label getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        Label label = repository.getById(id);
        return (label != null && label.getStatus() == Status.ACTIVE) ? label : null;
    }

    /**
     * Обновляет существующую метку.
     * @param label метка с ID
     */
    public void update(Label label) {
        if (label.getId() == null) {
            throw new IllegalArgumentException("Label ID cannot be null for update.");
        }
        Label existing = repository.getById(label.getId());
        if (existing == null || existing.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Label with ID " + label.getId() + " does not exist or was deleted.");
        }
        if (label.getName() == null || label.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Label name cannot be null or empty.");
        }
        repository.update(label);
        System.out.println("✅ Label with ID " + label.getId() + " updated.");
    }

    /**
     * Помечает метку как удаленную по ID.
     * @param id ID метки
     */
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        Label label = repository.getById(id);
        if (label == null || label.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Label with ID " + id + " not found or already deleted.");
        }
        repository.deleteById(id);
        System.out.println("✅ Label with ID " + id + " marked as DELETED.");
    }
}
