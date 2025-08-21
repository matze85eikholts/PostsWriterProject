package org.example.repository;

import com.google.gson.reflect.TypeToken;
import org.example.model.Post;
import org.example.model.Status;

import java.lang.reflect.Type;
import java.util.List;

public class GsonPostRepositoryImpl extends GsonGenericRepository<Post> implements PostRepository {

    public GsonPostRepositoryImpl() {
        super("src/main/resources/posts.json", new TypeToken<List<Post>>(){}.getType());
    }

    @Override
    protected Long extractId(Post post) {
        return post.getId();
    }

    @Override
    protected void setEntityId(Post post, Long id) {
        post.setId(id);
    }

    @Override
    protected Status getEntityStatus(Post post) {
        return post.getStatus();
    }

    @Override
    protected void setEntityStatus(Post post, Status status) {
        post.setStatus(status);
    }
}
