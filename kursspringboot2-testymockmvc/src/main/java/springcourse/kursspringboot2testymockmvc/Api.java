package springcourse.kursspringboot2testymockmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Api {

    private List<Person> people;

    public Api() {
        people = new ArrayList<>();
        people.add(new Person("Jan", "Nowak", Sex.MALE));
        people.add(new Person("bbb", "dddd", Sex.MALE));
        people.add(new Person("yyyy", "oooo", Sex.FEMALE));
        people.add(new Person("yyyy", "mmmmm", Sex.MALE));
        people.add(new Person("iiiii", "pppp", Sex.FEMALE));

    }

    @GetMapping("/people/{limit}")
    public List<Person> getPeople(@PathVariable("limit") int limit){
        return people.subList(0,limit);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
