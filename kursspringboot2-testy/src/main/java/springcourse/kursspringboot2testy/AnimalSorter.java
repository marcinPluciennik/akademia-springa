package springcourse.kursspringboot2testy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AnimalSorter {

    public String[] getDataFromApi(){
        RestTemplate restTemplate = new RestTemplate();
        //Trzeba odpalić aplikację animalTestDataForMockitoTests
        ResponseEntity<String[]> exchange = restTemplate.exchange("http://localhost:9090/animals",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String[].class);
        return exchange.getBody();
    }

    public String[] getSortDataFromApi(){
        String[] dataFromApi = getDataFromApi();
        Arrays.sort(dataFromApi);
        return dataFromApi;
    }
}
