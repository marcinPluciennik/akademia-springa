package springcourse.kursspringboot2testy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AnimalSorterTest {

    @Spy
    private AnimalSorter animalSorter;

    @Before
    public void getDataFromApi() {
        given(animalSorter.getDataFromApi()).willReturn(prepareMockData());
    }

    private String[] prepareMockData() {
        String[] animals =  {"Pies", "Kot", "Szynszyl"};
        return animals;
    }

    @Test
    public void sortDataFromApi() {
        String[] sorted =  {"Kot", "Pies", "Szynszyl"};
        assertArrayEquals(sorted, animalSorter.getSortDataFromApi());
    }
}