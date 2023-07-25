package com.a2r.immobilierdz.realestate;

import com.a2r.immobilierdz.appuser.AppUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;

public interface RealEstateService<T extends RealEstateLocationDTO>  {

    T insertHouse(T t  , String principal );


    void deleteHouse(Long id, String principal);

    T updateHouse(T t  , String principal , Long houseId  );

}
