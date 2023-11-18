package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.HandleResponse;
import com.yutsuki.telegram.exception.HandleException;
import com.yutsuki.telegram.model.response.UploadImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static com.yutsuki.telegram.exception.HandleException.exception;

@Service
@Slf4j
public class ImageService {
//    private final String imgPath = "/npm/api/img/" ;
    private final String imgPath = "D:/image/" ;
    public ResponseEntity<?> uploadImage(MultipartFile file) throws IOException {
        // validate file
        if (file == null) {
            log.debug("[book] file is null!::{}", file);
            return exception("file is null");
        }
        if (file.isEmpty()) {
            return exception("file is empty");
        }
        if (file.getSize() > 1048576 * 2) {
            log.debug("[book] file size is max size::{}", file.getSize());
            return exception("file size is max size");
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            log.debug("[book] contentType is null!::{}", contentType);
            return exception("invalid contentType ");
        }

        List<String> supportType = Collections.singletonList("image/png");
        log.info("supportType {}", supportType);
        if (!supportType.contains(contentType)) {
            log.info("[book] contentType is not support!::{}", contentType);
            return exception("contentType is not support");
        }

        String fileName = UUID.randomUUID().toString();
        String fullFileName = fileName.concat(".png");
        this.initDirectory();

        Path path = Paths.get(this.imgPath,fullFileName);
        // Copy the uploaded file to the target path
        Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

        // response image name
        UploadImageResponse response = UploadImageResponse.builder()
                .urlPath(this.imgPath.concat(fileName))
                .imageName(fullFileName)
                .build();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> findImag(String imgName) {
        try {
            String imagePath = imgPath + imgName;
            Resource image = new UrlResource("file:" + imagePath);

            if (image.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG) // or the appropriate content type for your images
                        .body(image);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }
    private void initDirectory() {
        File dir = new File(this.imgPath);
        if (!dir.exists()) {
            dir.mkdirs();
            dir.canWrite();
            dir.canRead();
        }
    }
}
