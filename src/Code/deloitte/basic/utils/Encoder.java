package deloitte.basic.utils;

import java.util.Base64;

public class Encoder {


    public String encoder(String input){
        return Base64.getEncoder().encodeToString(input.getBytes());//Hello my name is Rudy! SGVsbG8gbXkgbmFtZSBpcyBSdWR5IQ==
    }

    public String decode(String input){
        return new String(Base64.getDecoder().decode(input));
    }
}
