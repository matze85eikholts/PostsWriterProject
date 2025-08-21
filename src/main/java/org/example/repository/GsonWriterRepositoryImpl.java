package org.example.repository;

import com.google.gson.reflect.TypeToken;
import org.example.model.Status;
import org.example.model.Writer;

import java.lang.reflect.Type;
import java.util.List;

public class GsonWriterRepositoryImpl extends GsonGenericRepository<Writer> implements WriterRepository {

    public GsonWriterRepositoryImpl() {
        super("src/main/resources/writers.json", new TypeToken<List<Writer>>(){}.getType());
    }

    @Override
    protected Long extractId(Writer writer) {
        return writer.getId();
    }

    @Override
    protected void setEntityId(Writer writer, Long id) {
        writer.setId(id);
    }

    @Override
    protected Status getEntityStatus(Writer writer) {
        return writer.getStatus();
    }

    @Override
    protected void setEntityStatus(Writer writer, Status status) {
        writer.setStatus(status);
    }
}