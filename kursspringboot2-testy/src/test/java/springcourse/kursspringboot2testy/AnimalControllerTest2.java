package springcourse.kursspringboot2testy;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AnimalControllerTest2 {

    @Mock
    private AnimalRepo animalRepo;

    @InjectMocks
    private AnimalController animalController;

    @Before
    public void init(){
        given(animalRepo.findAll()).willReturn(prepareMockData());

    }

    @Test
    public void getAnimals() {
        //When
        List<Animal> animals = animalController.getAnimals();
        //Then
        Assert.assertEquals(3, animals.size());
    }

    @Test
    public void addAnimal() {
    }

    public List<Animal> prepareMockData(){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("cat"));
        animals.add(new Animal("dog"));
        animals.add(new Animal("dog"));
        return animals;
    }
}