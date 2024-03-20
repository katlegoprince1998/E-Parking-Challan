package com.groupg.eparkingchallan.service.authentication;


import com.groupg.eparkingchallan.dto.UserDto;
import com.groupg.eparkingchallan.request.UserRequest;
import com.groupg.eparkingchallan.response.AuthResponse;

public interface AuthenticationService {
    AuthResponse registerUser(UserDto userDto) throws Exception;
    AuthResponse logUserIn(UserRequest request);
}
