package com.practicum.catsgram.controller;

import com.practicum.catsgram.exception.ConditionsNotMetException;
import com.practicum.catsgram.exception.NotFondException;
import com.practicum.catsgram.model.Post;
import com.practicum.catsgram.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
       return postService.update(newPost);
    }
}