package com.groupg.eparkingchallan.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
}
