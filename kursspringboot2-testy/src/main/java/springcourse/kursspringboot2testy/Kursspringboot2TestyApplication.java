package springcourse.kursspringboot2testy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Kursspringboot2TestyApplication {

    public static void main(String[] args) { SpringApplication.run(Kursspringboot2TestyApplication.class, args);
    }

    @Autowired
    private AnimalRepo animalRepo;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        animalRepo.save(new Animal("cat"));
        animalRepo.save(new Animal("dog"));
    }
}
