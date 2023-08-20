package com.a2r.immobilierdz.login;

import com.a2r.immobilierdz.appuser.AppUser;
import com.a2r.immobilierdz.appuser.AppUserRepository;
import com.a2r.immobilierdz.security.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/api/v1/auth")
@RequestMapping("/rest/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;


    private final AppUserRepository appUserRepository;

    public static final String TOKEN_PREFIX = "Bearer ";

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginReq , HttpServletResponse response) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            AppUser user = appUserRepository.findByEmail(email).get();

            if (!user.isEnabled()) {
                throw new BadCredentialsException("User profile is not enabled");
            }

            String token = jwtUtil.createToken(user);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("userId", user.getId());
            responseData.put("role", user.getAppUserRole().getRoleName());

            response.getWriter().write(objectMapper.writeValueAsString(responseData)
            );


           response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
           response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.AUTHORIZATION + ","+ HttpHeaders.ORIGIN +","+ HttpHeaders.ACCEPT);
           response.addHeader(HttpHeaders.AUTHORIZATION , TOKEN_PREFIX +token);

            //return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(objectMapper.writeValueAsString(responseData));
                return ResponseEntity.ok().body(null);


               // return ResponseEntity.ok("token " + token);

        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
