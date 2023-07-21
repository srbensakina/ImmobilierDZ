package com.a2r.immobilierdz.realestate;

import org.springframework.security.oauth2.jwt.Jwt;

public interface RealEstateService<T extends RealEstateLocationDTO>  {

    public T insertHouse(T t , Jwt principal);

   // public void deleteHouse(T t, Jwt principal);

    public T updateHouse(T t ,Jwt principal);

}
