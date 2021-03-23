package springcourse.homeworksecurity3passwordencoder.encoder;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MarcinPasswordEncoder implements PasswordEncoder {

    public static final Logger LOGGER = LoggerFactory.getLogger(MarcinPasswordEncoder.class);

    @Override
    public String encode(CharSequence charSequence) {
        String result = new String();
        for (int i = 0; i < charSequence.length(); i++){
            char c = charSequence.charAt(i);
            int ascii = c;
            result += String.valueOf(ascii);
        }
        String passwordEncoded = getEncoderVersion() + getSalt() + result;
        return passwordEncoded;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String salt = s.substring(9,19);
        String password = encode(charSequence);
        password = password.substring(0,9) + salt + password.substring(19);
        LOGGER.info("USER DB PASSWORD : " + s);
        LOGGER.info("USER RAW PASSWORD ENCODED: " + password);
        LOGGER.info("PASSWORDS MATCH ?: " + s.equals(password));
        return  s.equals(password);
    }

    public String getEncoderVersion(){
        return "@1MARCIN@";
    }

    public String getSalt(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }
}
