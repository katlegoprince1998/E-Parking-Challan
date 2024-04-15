package com.groupg.eparkingchallan.service.twilio;

import com.groupg.eparkingchallan.config.twilio.SmsPojo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class SmsService {
    private final String ACCOUNT_SID ="ACc09d2acee4ddeee07e00f79f1f55b826";

    private final String AUTH_TOKEN = "d1d3cadded4db3a4313fdddb741b8dd2";

    private final String FROM_NUMBER = "+12058462863";

    public void send(SmsPojo sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getSendTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());

    }
}
