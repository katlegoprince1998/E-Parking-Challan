package com.groupg.eparkingchallan.service.twilio;

import com.groupg.eparkingchallan.config.twilio.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsService {

    @Value("${twilio.accountSid}")
    private String ACCOUNT_SID;

    @Value("${twilio.authToken}")
    private String AUTH_TOKEN;

    @Value("${twilio.fromNumber}")
    private String FROM_NUMBER;

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(sms.getSendTo()),
                new PhoneNumber(FROM_NUMBER),
                sms.getMessage()
        ).create();

        System.out.println("Message SID: " + message.getSid());
    }
}
