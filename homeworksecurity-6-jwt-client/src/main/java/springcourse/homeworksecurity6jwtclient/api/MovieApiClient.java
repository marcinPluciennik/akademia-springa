package springcourse.homeworksecurity6jwtclient.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import springcourse.homeworksecurity6jwtclient.keyPairGenerator.KeysGenerator;

import java.util.stream.Stream;

@Controller
public class MovieApiClient {

    private KeysGenerator keysGenerator;

    @Autowired
    public MovieApiClient(KeysGenerator keysGenerator) {
        this.keysGenerator = keysGenerator;

        addMovies();
        getMovies();
        //keysGenerator.saveKeyPair(); - for one-time use (generates and save keys)
        keysGenerator.getPublicKey();
        keysGenerator.getPrivateKey();
    }

    private void addMovies() {
        String jwt = generateJwt(true);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "  + jwt);

        String movieToAdd = "Alien Resurection";

        HttpEntity httpEntity = new HttpEntity(movieToAdd, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8080/api/movies",
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }

    private void getMovies() {
        String jwt = generateJwt(true);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "  + jwt);

        HttpEntity httpEntity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> exchange = restTemplate.exchange("http://localhost:8080/api/movies",
                HttpMethod.GET,
                httpEntity,
                String[].class);

        Stream.of(exchange.getBody()).forEach(System.out::println);
    }

    private String generateJwt(boolean isAdmin) {
        Algorithm algorithm = Algorithm.HMAC512("bPeShVmYq3t6w9z$B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(");
        //Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) keysGenerator.getPrivateKey());
        return JWT.create().withClaim("admin", isAdmin).sign(algorithm);
    }

}
