package com.groupg.eparkingchallan.config.twilio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsPojo {
    //Declaring the message and to variables
    private String sendTo;
    private String message;
}
