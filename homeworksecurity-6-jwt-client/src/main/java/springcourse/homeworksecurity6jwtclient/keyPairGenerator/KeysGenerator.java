package springcourse.homeworksecurity6jwtclient.keyPairGenerator;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class KeysGenerator {

    public KeyPair generateKeyPair(){
        try{
            SecureRandom secureRandom = new SecureRandom();
            java.security.KeyPairGenerator keyPairGenerator = java.security.KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048, secureRandom);
            return keyPairGenerator.generateKeyPair();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void saveKeyPair(){
        KeyPair keyPair = generateKeyPair();
        try{
            Files.write(Paths.get("src/main/resources/private.key"), keyPair.getPrivate().getEncoded());
            Files.write(Paths.get("src/main/resources/public.key"), keyPair.getPublic().getEncoded());
        }catch (IOException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public PrivateKey getPrivateKey(){
        try{
            byte[] keyBytes = Files.readAllBytes(Paths.get("src/main/resources/private.key"));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return keyFactory.generatePrivate(keySpec);
        }catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public PublicKey getPublicKey(){
        try{
            byte[] keyBytes = Files.readAllBytes(Paths.get("src/main/resources/public.key"));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            System.out.println("Public key: " + Base64.getEncoder().encodeToString(keyBytes));
            return keyFactory.generatePublic(keySpec);
        }catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
