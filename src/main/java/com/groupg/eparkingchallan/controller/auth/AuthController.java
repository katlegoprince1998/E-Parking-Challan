package com.groupg.eparkingchallan.controller.auth;


import com.groupg.eparkingchallan.dto.UserDto;
import com.groupg.eparkingchallan.request.UserRequest;
import com.groupg.eparkingchallan.response.AuthResponse;
import com.groupg.eparkingchallan.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth/")
@RestController
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) throws Exception {

        try{
            AuthResponse authResponse =
                    authenticationService.registerUser(userDto);
            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> logUserIn(@RequestBody UserRequest request){

            AuthResponse authResponse =
                    authenticationService.logUserIn(request);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
}
