package com.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "id")
public class Post {
    private long id;
    private long authorId;
    private String description;
    private Instant postData;
}
