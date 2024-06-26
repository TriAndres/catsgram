package com.practicum.catsgram.service;

import com.practicum.catsgram.exeption.ConditionsNotMetException;
import com.practicum.catsgram.exeption.ImageFileException;
import com.practicum.catsgram.exeption.NotFondException;
import com.practicum.catsgram.model.Image;
import com.practicum.catsgram.model.ImageData;
import com.practicum.catsgram.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final PostService postService;
    private final Map<Long, Image> images = new HashMap<>();
    private String imageDirectory = "images";

    public List<Image> saveImages(long postId, List<MultipartFile> files) {
        return files.stream().map(file -> saveImage(postId, file)).collect(Collectors.toList());
    }

    public Image saveImage(long postId, MultipartFile file) {
        Post post = postService.findById(postId)
                .orElseThrow(() -> new ConditionsNotMetException("Указаный пост не найден"));
        Path filePath = saveFile(file, post);
        long imageId = getNextId();
        Image image = new Image();
        image.setId(imageId);
        image.setFilePath(filePath.toString());
        image.setPostId(postId);
        image.setOriginalFileName(file.getOriginalFilename());
        images.put(imageId, image);
        return image;
    }

    public List<Image> getPostImages(long postId) {
        return images.values()
                .stream()
                .filter(image -> image.getPostId() == postId)
                .collect(Collectors.toList());
    }

    public ImageData getImageData(long imageId) {
        if (!images.containsKey(imageId)) {
            throw new NotFondException("Изображение с id =" + imageId + " не найдено");
        }
        Image image = images.get(imageId);
        byte[] data = loadFale(image);
        return new ImageData(data, image.getOriginalFileName());
    }

    private Path saveFile(MultipartFile file, Post post) {
        try {
            String uniqueFileName = String.format("%d,%s", Instant.now().toEpochMilli(),
                    StringUtils.getFilenameExtension(file.getOriginalFilename()));
            Path uploadPath = Paths.get(imageDirectory, String.valueOf(post.getAuthorId()), post.getId().toString());
            Path filePath = uploadPath.resolve(uniqueFileName);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            file.transferTo(filePath);
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadFale(Image image) {
        Path path = Paths.get(image.getFilePath());
        if (Files.exists(path)) {
            try {
                return Files.readAllBytes(path);
            } catch (IOException e) {
                throw new ImageFileException("Ошибка чтения файла. Id: " + image.getId()
                        + ", name: " + image.getOriginalFileName());
            }
        } else {
            throw new ImageFileException("Ффйл не найден. Id: " + image.getId()
                    + ", name: " + image.getOriginalFileName());
        }
    }

    private long getNextId() {
        long currentMaxId = images.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
