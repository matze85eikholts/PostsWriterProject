package org.example.repository;

import com.google.gson.reflect.TypeToken;
import org.example.model.Label;
import org.example.model.Status;

import java.lang.reflect.Type;
import java.util.List;

public class GsonLabelRepositoryImpl extends GsonGenericRepository<Label> implements LabelRepository {

    public GsonLabelRepositoryImpl() {
        super("src/main/resources/labels.json", new TypeToken<List<Label>>(){}.getType());
    }

    @Override
    protected Long extractId(Label label) {
        return label.getId();
    }

    @Override
    protected void setEntityId(Label label, Long id) {
        label.setId(id);
    }

    @Override
    protected Status getEntityStatus(Label label) {
        return label.getStatus();
    }

    @Override
    protected void setEntityStatus(Label label, Status status) {
        label.setStatus(status);
    }
}
