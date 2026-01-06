package com.example.simplewebapp.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class BlogStore {

    private final Map<String, Blog> blogs = new LinkedHashMap<>();

    public List<Blog> findAll() {
        return new ArrayList<>(blogs.values());
    }

    public Blog findById(String id) {
        return blogs.get(id);
    }

    public void save(Blog blog) {
        blogs.put(blog.getId(), blog);
    }

    public void delete(String id) {
        blogs.remove(id);
    }
}
