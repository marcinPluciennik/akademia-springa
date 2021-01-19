package springcourse.kursspringboot2testy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    private AnimalController animalController;

    @Autowired
    public Api(AnimalController animalController) {
        this.animalController = animalController;
    }

    @GetMapping("/animals")
    public Iterable<Animal> getAnimals(){
        return animalController.getAnimals();
    }

    @PostMapping("/animals")
    public void addAnimal(@RequestBody Animal animal){
        animalController.addAnimal(animal);
    }
}
