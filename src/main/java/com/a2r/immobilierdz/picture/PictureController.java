package com.a2r.immobilierdz.picture;


import com.a2r.immobilierdz.exceptions.NoSuchPictureException;
import com.a2r.immobilierdz.rating.RatingMapper;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("api/v1/pictures")
@RestController
public class PictureController {

    private final PictureService pictureService;
    private final PictureMapper pictureMapper;

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadPicture(@PathVariable("fileName") String fileName) throws IOException {
        byte[] picture = pictureService.downloadPicture(fileName);
        if (picture != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(picture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all/{houseId}")
    public List<PictureDTO> downloadAllHousePictures(@PathVariable("houseId") Long id) {
      //  return pictureMapper.map(pictureService.downloadAllHousePictures(id));
          return pictureMapper.map(pictureService.downloadAllHousePictures(id));
    }

  /*  @DeleteMapping("/{picture_id}")
    public ResponseEntity<?> deletePicture( @PathVariable Long picture_id) {
        try {
            pictureService.deletePicture(picture_id);
            return ResponseEntity.ok().body(null);
        } catch (NoSuchPictureException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This picture doesn't exist");
        }
    }*/

    @DeleteMapping("/{file_path}")
    public ResponseEntity<?> deletePicture( @PathVariable String file_path) {
        try {
            pictureService.deletePicture(file_path);
            return ResponseEntity.ok().body(null);
        } catch (NoSuchPictureException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
        }
    }

    @PostMapping("{id}/upload")
    public ResponseEntity<String> uploadHousePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file , @AuthenticationPrincipal String ownerId) {
        try {
            pictureService.savePicture(file, id , ownerId);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
