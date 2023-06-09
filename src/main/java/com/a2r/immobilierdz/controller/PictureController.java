package com.a2r.immobilierdz.controller;


import com.a2r.immobilierdz.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("pictures")
@RestController
public class PictureController {

    private final PictureService pictureService;

    @GetMapping("/{fileName}")
    public ResponseEntity<?> findHousePicture(@PathVariable("fileName") String fileName) throws IOException {
        byte[] picture = pictureService.downloadPicture(fileName);
        if (picture != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(picture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{houseId}")
    public List<String> findHousePicture(@PathVariable("houseId") Long id) throws IOException {
        return  pictureService.downloadAllHousePictures(id);
    }

    @PostMapping("{id}/upload")
    public ResponseEntity<String> uploadHousePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            pictureService.savePicture(file, id);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }
    }

}
