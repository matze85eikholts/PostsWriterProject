package org.example.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Status;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class GsonGenericRepository<T> implements GenericRepository<T, Long> {

    protected final Gson gson = new Gson();
    protected final String filePath;
    protected final Type listType;
    protected final AtomicLong idGenerator = new AtomicLong(1);
    protected List<T> entities = new ArrayList<>();

    public GsonGenericRepository(String filePath, Type listType) {
        this.filePath = filePath;
        this.listType = listType;
        this.load();
        this.generateId();
    }

    private void load() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                writeAll();
            } catch (IOException e) {
                throw new RuntimeException("Cannot create file: " + filePath, e);
            }
        }

        try (Reader reader = new FileReader(file)) {
            List<T> list = gson.fromJson(reader, listType);
            if (list != null) {
                entities = list;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + filePath, e);
        }
    }

    private void writeAll() {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(entities, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + filePath, e);
        }
    }

    private void generateId() {
        long maxId = entities.stream()
                .map(this::extractId)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);
        idGenerator.set(maxId + 1);
    }

    protected abstract Long extractId(T entity);

    @Override
    public T getById(Long id) {
        return entities.stream()
                .filter(e -> extractId(e).equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> getAll() {
        return entities.stream()
                .filter(e -> extractId(e) != null && getEntityStatus(e) != Status.DELETED)
                .toList();
    }

    @Override
    public T save(T entity) {
        Long id = extractId(entity);
        if (id == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
            Long newId = idGenerator.getAndIncrement();
            setEntityId(entity, newId);
        }
        entities.add(entity);
        writeAll();
        return entity;
    }

    @Override
    public T update(T entity) {
        Long id = extractId(entity);
        if (id == null) return null;

        Optional<T> existing = entities.stream()
                .filter(e -> extractId(e).equals(id))
                .findFirst();

        if (existing.isPresent()) {
            entities.remove(existing.get());
            entities.add(entity);
            writeAll();
        }
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        entities.stream()
                .filter(e -> extractId(e).equals(id))
                .findFirst()
                .ifPresent(e -> setEntityStatus(e, Status.DELETED));
        writeAll();
    }

    protected abstract Status getEntityStatus(T entity);
    protected abstract void setEntityStatus(T entity, Status status);
    protected abstract void setEntityId(T entity, Long id);
}
