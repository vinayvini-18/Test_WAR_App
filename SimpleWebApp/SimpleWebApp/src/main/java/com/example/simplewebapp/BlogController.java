package com.example.simplewebapp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.simplewebapp.model.Blog;
import com.example.simplewebapp.model.BlogStore;

@Controller
public class BlogController {

    @Autowired
    private BlogStore store;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("posts", store.findAll());
        return "dashboard";
    }

    @GetMapping("/post/{id}")
    public String view(@PathVariable String id, Model model) {
        model.addAttribute("post", store.findById(id));
        return "view_post";
    }

    @GetMapping("/post/new")
    public String createForm(Model model) {
        model.addAttribute("post", new Blog());
        return "post_form";
    }

    @PostMapping("/post")
    public String create(Blog blog) {
        blog.setId(UUID.randomUUID().toString());
        store.save(blog);
        return "redirect:/";
    }

    @GetMapping("/post/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("post", store.findById(id));
        return "post_form";
    }

    @PostMapping("/post/update")
    public String update(Blog blog) {
        store.save(blog);
        return "redirect:/post/" + blog.getId();
    }

    @GetMapping("/post/delete/{id}")
    public String delete(@PathVariable String id) {
        store.delete(id);
        return "redirect:/";
    }
}
