package com.a2r.immobilierdz.picture;

import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.RealEstate;
import com.a2r.immobilierdz.repository.RealEstateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final RealEstateRepository realEstateRepository;
    @Value("${picture.upload.directory}")
    private String uploadDirectory;

    public byte[] downloadPicture(String fileName) throws IOException {
        Optional<Picture> fileData = pictureRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }



    public String savePicture(MultipartFile file, Long id) throws IOException {
        String filePath=uploadDirectory+file.getOriginalFilename();
       RealEstate realEstate = realEstateRepository.save(House.builder().id(id).build());
        Picture picture = pictureRepository.save(Picture.builder()
                .name(file.getOriginalFilename())
                .realEstate(realEstate)
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (picture != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

}
