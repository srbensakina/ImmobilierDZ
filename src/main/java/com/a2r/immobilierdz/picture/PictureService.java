package com.a2r.immobilierdz.picture;

import com.a2r.immobilierdz.exceptions.NoSuchPictureException;
import com.a2r.immobilierdz.exceptions.RealEstateNotFoundException;
import com.a2r.immobilierdz.exceptions.UnauthorizedAccessException;
import com.a2r.immobilierdz.house.House;
import com.a2r.immobilierdz.house.HouseRepository;
import com.a2r.immobilierdz.house.specs.RealEstateRepository;
import com.a2r.immobilierdz.realestate.RealEstate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final RealEstateRepository realEstateRepository;
    private final HouseRepository houseRepository;
    @Value("${picture.upload.directory}")
    private String uploadDirectory;

    @Value("${picture.api}")
    private String api_key;


    public byte[] downloadPicture(String fileName) throws IOException {
        Optional<Picture> fileData = pictureRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }



    public List<Picture> downloadAllHousePictures(Long id) {
      //  List<String> picturePaths = new ArrayList<>();
       // List<Picture> fileDataList = pictureRepository.findAllByRealEstateId(id);
      /*  for (Picture picture : fileDataList
        ) {
            String filePath = picture.getFilePath();
            picturePaths.add(filePath);
        }
        return picturePaths;*/
      //  return pictureRepository.findAllByRealEstateId(id);
        return pictureRepository.findAllByRealEstateId(id);
    }

 /*   @Transactional
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
    }*/

    @Transactional
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public String savePicture(MultipartFile file, Long id, String ownerId) throws IOException, ParseException {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new RealEstateNotFoundException("This real estate doesn't exist"));

        if (!house.getOwnerId().equals(Long.valueOf(ownerId))) {
            throw new UnauthorizedAccessException("You are not authorized to add pictures to properties you don't own.");
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(uploadDirectory);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key", api_key);
            builder.addBinaryBody("image", file.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, file.getOriginalFilename());

            HttpEntity multipart = builder.build();
            request.setEntity(multipart);

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = ((CloseableHttpResponse) response).getEntity();
            String jsonResponse = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            String imageUrl = "";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode rootNode = objectMapper.readTree(jsonResponse);
                JsonNode urlNode = rootNode.path("data").path("display_url");
                imageUrl = urlNode.asText();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //  JSONParser parser = new JSONParser();
            //  JSONObject json = (JSONObject) parser.parse(jsonResponse);

            if (responseEntity != null) {
                if (!Objects.equals(imageUrl, "")) {
                    Picture picture = pictureRepository.save(Picture.builder()
                            .name(file.getOriginalFilename())
                            .realEstate(house)
                            .filePath(imageUrl).build());

                    if (picture != null) {
                        return "Image uploaded successfully: " + imageUrl;
                    }
                }
            }
        }

        return "Failed to upload image";

}

  /*  @PreAuthorize("hasRole('ROLE_OWNER')")
    public void deletePicture(Long picture_id) {
       pictureRepository.delete(pictureRepository.findById(picture_id).orElseThrow(() -> new NoSuchPictureException("This picture doesn't exist")));
    }*/

 /*   @PreAuthorize("hasRole('ROLE_OWNER')")
    public void deletePicture(String filePath) {
        pictureRepository.delete(pictureRepository.findByFilePath(filePath).orElseThrow(() -> new NoSuchPictureException("This picture doesn't exist")));
    }*/


    @PreAuthorize("hasRole('ROLE_OWNER')")
    public void deletePicture(String name) {
        pictureRepository.delete(pictureRepository.findByName(name).orElseThrow(() -> new NoSuchPictureException("This picture doesn't exist")));
}
}
