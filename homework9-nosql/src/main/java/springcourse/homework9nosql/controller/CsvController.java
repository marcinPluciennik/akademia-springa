package springcourse.homework9nosql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class CsvController {
    private RestTemplate restTemplate;
    private static final String CSV_PATH = "/Users/marcin/Desktop/spring-course/homework9-nosql/src/main/java/springcourse/homework9nosql/source/source.csv";
    private static final String API_PATH = "https://my.api.mockaroo.com/users.json?key=57205520";

    @Autowired
    public CsvController() {
        this.restTemplate = new RestTemplate();
    }

    public void downloadCsvFile(){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(API_PATH, HttpMethod.GET, entity, byte[].class);
            response.getBody();
            Files.write(Paths.get(CSV_PATH), response.getBody());
            System.out.println("File is downloaded and saved.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
