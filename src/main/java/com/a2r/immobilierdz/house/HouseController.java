package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.exceptions.AddressAlreadyExistsException;
import com.a2r.immobilierdz.exceptions.RealEstateNotFoundException;
import com.a2r.immobilierdz.exceptions.UnauthorizedAccessException;
import com.a2r.immobilierdz.realestate.enums.Type;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/houses")
@RequiredArgsConstructor
@Log4j2
//@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders={"Accept"})

public class HouseController {

    private final HouseService houseService;


    @GetMapping("{id}")
    public ResponseEntity<?> findHouseById(@PathVariable Long id) {
        try {
            HouseLocationDTO houseLocationDTO = houseService.findHouseById(id);
            return ResponseEntity.ok(houseLocationDTO);
        } catch(RealEstateNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<HouseLocationDTO>> findAll() {
        return ResponseEntity.ok(houseService.findAll());
    }

    @PostMapping
    public ResponseEntity<HouseLocationDTO> insertHouse(@Valid @RequestBody HouseLocationDTO houseLocationDto , @AuthenticationPrincipal String principal) {
        try {
        HouseLocationDTO houseLocationDtoResponse = houseService.insertHouse(houseLocationDto , principal ) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(houseLocationDtoResponse);
        }catch (AddressAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
           // throw new AddressAlreadyExistsException("Address Already exits");

    }
    }

    @PutMapping("/{houseId}")
    public ResponseEntity<?> updateHouse( @Valid @RequestBody HouseLocationDTO houseLocationDTO  ,@PathVariable Long houseId, @AuthenticationPrincipal String principal ) {
       try {
           return ResponseEntity.ok(houseService.updateHouse(houseLocationDTO , principal , houseId));
       }catch (UnauthorizedAccessException unauthorizedAccessException){
           return ResponseEntity.badRequest().body(unauthorizedAccessException.getMessage());
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable Long id, @AuthenticationPrincipal String principal) {
        try {
            houseService.deleteHouse(id, principal);
            return ResponseEntity.noContent().build();
        } catch (RealEstateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("city/{city}")
    public ResponseEntity<List<HouseLocationDTO>> findHouseByCity(@PathVariable String city) {
        return ResponseEntity.ok(houseService.findHousesByCity(city));
    }

    @GetMapping("/filter")
    @ResponseBody
    public List<House> filterHouses(@RequestParam(required = false) String city, @RequestParam(value = "minPrice", required = false) Integer minPrice, @RequestParam(value = "maxPrice", required = false) Integer maxPrice, @RequestParam(required = false) Type type) {
        return houseService.filterHouses(city, minPrice, maxPrice, type);
    }

   @GetMapping("owners/{ownerId}")
    public ResponseEntity<List<HouseLocationDTO>> findHousesByOwnerId( @PathVariable Long ownerId, @AuthenticationPrincipal String principal ){
        return ResponseEntity.ok(houseService.findHousesByOwnerId(ownerId, principal));

    }

}
