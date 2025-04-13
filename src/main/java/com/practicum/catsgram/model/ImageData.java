package com.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageData {
    private final byte[] date;
    private final String name;
}
