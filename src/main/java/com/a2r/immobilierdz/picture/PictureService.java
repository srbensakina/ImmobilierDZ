package com.a2r.immobilierdz.picture;

import com.a2r.immobilierdz.exceptions.NoSuchPictureException;
import com.a2r.immobilierdz.exceptions.RealEstateNotFoundException;
import com.a2r.immobilierdz.exceptions.UnauthorizedAccessException;
import com.a2r.immobilierdz.house.specs.RealEstateRepository;
import com.a2r.immobilierdz.realestate.RealEstate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<String> downloadAllHousePictures(Long id) {
        List<String> picturePaths = new ArrayList<>();
        List<Picture> fileDataList = pictureRepository.findAllByRealEstateId(id);
        for (Picture picture: fileDataList
             ){
            String filePath = picture.getFilePath();
            picturePaths.add(filePath);
        }
        return picturePaths;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public String savePicture(MultipartFile file, Long id , String ownerId) throws IOException {
        String filePath = uploadDirectory + file.getOriginalFilename();
      RealEstate realEstate =  realEstateRepository.findById(id).orElseThrow(() ->new RealEstateNotFoundException("This real estate doesn't exist"));
        if (realEstate.getOwnerId().equals(Long.valueOf(ownerId))){
            Picture picture = pictureRepository.save(Picture.builder()
                    .name(file.getOriginalFilename())
                    .realEstate(realEstate)
                    .filePath(filePath).build());

            file.transferTo(new File(filePath));

            if (picture != null) {
                return "file uploaded successfully : " + filePath;
            }
        }else
            throw new UnauthorizedAccessException("You are not authorized to add pictures to properties you don't own.");
        return null;
    }


    @PreAuthorize("hasRole('ROLE_OWNER')")
    public void deletePicture(Long picture_id) {
       pictureRepository.delete(pictureRepository.findById(picture_id).orElseThrow(() -> new NoSuchPictureException("This picture doesn't exist")));
    }

}
