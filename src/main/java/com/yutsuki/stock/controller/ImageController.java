package com.yutsuki.stock.controller;

import com.yutsuki.stock.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/v1/image")
public class ImageController {
    @Resource
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> findImage(@PathVariable String fileName) {
        return imageService.findImag(fileName);
    }
}
