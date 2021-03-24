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
        final int CHAR_NUMBER_TO_CUT_MAIN_PASSWORD = 19;
        String mainPasswordDB = s.substring(CHAR_NUMBER_TO_CUT_MAIN_PASSWORD);
        String mainPasswordEncoded = encode(charSequence).substring(CHAR_NUMBER_TO_CUT_MAIN_PASSWORD);
        LOGGER.info("USER DB MAIN PASSWORD : " + mainPasswordDB);
        LOGGER.info("USER RAW MAIN PASSWORD ENCODED: " + mainPasswordEncoded);
        LOGGER.info("PASSWORDS MATCH ?: " + mainPasswordDB.equals(mainPasswordEncoded));
        return  mainPasswordDB.equals(mainPasswordEncoded);
    }

    public String getEncoderVersion(){
        return "@1MARCIN@";
    }

    public String getSalt(){
        int length = 10;
        boolean isUsedLetters = true;
        boolean isUseNumbers = true;
        String generatedString = RandomStringUtils.random(length, isUsedLetters, isUseNumbers);
        return generatedString;
    }
}
