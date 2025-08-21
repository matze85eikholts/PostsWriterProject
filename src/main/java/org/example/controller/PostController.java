package org.example.controller;

import org.example.model.Post;
import org.example.model.Status;
import org.example.repository.GsonPostRepositoryImpl;
import org.example.repository.PostRepository;

import java.util.List;

public class PostController {

    private final PostRepository repository = new GsonPostRepositoryImpl();

    /**
     * Создает новый пост.
     * @param post пост без ID (ID будет присвоен автоматически)
     */
    public void create(Post post) {
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be null or empty.");
        }
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be null or empty.");
        }
        repository.save(post);
        System.out.println("✅ Post created with ID: " + post.getId());
    }

    /**
     * Возвращает список всех активных постов (со статусом ACTIVE).
     * @return список постов
     */
    public List<Post> getAll() {
        return repository.getAll();
    }

    /**
     * Находит пост по ID.
     * @param id ID поста
     * @return найденный пост или null, если не найден или удален
     */
    public Post getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        Post post = repository.getById(id);
        return (post != null && post.getStatus() == Status.ACTIVE) ? post : null;
    }

    /**
     * Обновляет существующий пост.
     * @param post пост с ID
     */
    public void update(Post post) {
        if (post.getId() == null) {
            throw new IllegalArgumentException("Post ID cannot be null for update.");
        }
        Post existing = repository.getById(post.getId());
        if (existing == null || existing.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Post with ID " + post.getId() + " does not exist or was deleted.");
        }
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be null or empty.");
        }
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be null or empty.");
        }
        repository.update(post);
        System.out.println("✅ Post with ID " + post.getId() + " updated.");
    }

    /**
     * Помечает пост как удаленный по ID.
     * @param id ID поста
     */
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        Post post = repository.getById(id);
        if (post == null || post.getStatus() == Status.DELETED) {
            throw new IllegalArgumentException("Post with ID " + id + " not found or already deleted.");
        }
        repository.deleteById(id);
        System.out.println("✅ Post with ID " + id + " marked as DELETED.");
    }
}