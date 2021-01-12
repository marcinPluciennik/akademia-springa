package springcourse.kursspringboot2nosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class Kursspringboot2NosqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Kursspringboot2NosqlApplication.class, args);
    }

}
