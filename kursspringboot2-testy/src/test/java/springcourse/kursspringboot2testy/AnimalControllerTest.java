package springcourse.kursspringboot2testy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalControllerTest {

    @Test
    public void shouldGetAnimals() {
        //Given
        AnimalController animalController = mock(AnimalController.class);
        when(animalController.getAnimals()).thenReturn(prepareMockData());
        //When
        List<Animal> animals = animalController.getAnimals();
        //Then
        Assert.assertEquals(3, animals.size());
    }

    public List<Animal> prepareMockData(){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("cat"));
        animals.add(new Animal("dog"));
        animals.add(new Animal("dog"));
        return animals;
    }

    @Test
    public void addAnimal() {
        //Given
        AnimalController animalController = mock(AnimalController.class);
        when(animalController.addAnimal(Mockito.any(Animal.class))).thenReturn(new Animal("dog"));
        //When
        Animal animal = animalController.addAnimal(new Animal());
        //Then
        Assert.assertEquals("dog", animal.getName());
    }
}