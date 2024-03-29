package springcourse.kursspringboot2securityjwtclient;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@Controller
public class BookApiClient {

    public BookApiClient() {
        addBooks();
        getBooks();
    }

    private void addBooks() {
        String jwt = generateJwt(true);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        String bookToAdd = "Pan Tadeusz";

        HttpEntity httpEntity = new HttpEntity(bookToAdd, headers);


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8080/api/books",
                HttpMethod.POST,
                httpEntity,
                Void.class);
    }

    private void getBooks() {
        String jwt = generateJwt(true);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);


        HttpEntity httpEntity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String[]> exchange = restTemplate.exchange("http://localhost:8080/api/books",
                HttpMethod.GET,
                httpEntity,
                String[].class);

        Stream.of(exchange.getBody()).forEach(System.out::println);
    }

    private String generateJwt(boolean isAdmin) {
        Algorithm algorithm = Algorithm.HMAC512("bPeShVmYq3t6w9z$B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(");
        return JWT.create().withClaim("admin", isAdmin).sign(algorithm);
    }
}
