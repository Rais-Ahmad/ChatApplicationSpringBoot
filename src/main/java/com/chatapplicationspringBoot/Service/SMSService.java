package com.chatapplicationspringBoot.Service;


import com.chatapplicationspringBoot.Model.PojoInterface.SMS;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class SMSService {

    private final String ACCOUNT_SID = "AC31b2c9f66d33e1256230d66f8eb72516";

    private final String AUTH_TOKEN = "90effa9d3b6ae9dd1bd7a4244777c79c";

    private final String FROM_NUMBER = "+14135531059";

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}
