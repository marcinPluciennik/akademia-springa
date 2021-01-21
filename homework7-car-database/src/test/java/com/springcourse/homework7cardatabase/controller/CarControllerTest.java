package com.springcourse.homework7cardatabase.controller;

import com.google.gson.Gson;
import com.springcourse.homework7cardatabase.dao.CarDao;
import com.springcourse.homework7cardatabase.model.Car;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CarControllerTest {
    private final Long CAR_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarDao carDao;

    @Before
    public void init(){
        Car car1 = new Car(1L, "Fiat", "126p", "Blue", 1990);
        Car car2 = new Car(2L, "Skoda", "500", "White", 2000);

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        when(carDao.findAll()).thenReturn(carList);
        when(carDao.findById(CAR_ID)).thenReturn(carList.get((int)(CAR_ID - 1)));
    }

    @Test
    public void shouldGetCars() throws Exception{
        //When & Then
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetCarsByYearRange() throws Exception{
        //Given
        Car car1 = new Car(1L, "Fiat", "126p", "Blue", 1990);
        Car car2 = new Car(2L, "Skoda", "500", "White", 2000);
        Car car3 = new Car(3L, "Polonez", "1500", "Black", 1999);
        Car car4 = new Car(4L, "Fiat", "Panda", "Blue", 2010);
        Car car5 = new Car(5L, "VW", "Polo", "White", 1998);

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);
        carList.add(car5);

        List<Car> carsByYearRange = new ArrayList<>();
        carsByYearRange.add(car1);
        carsByYearRange.add(car3);
        carsByYearRange.add(car5);

        when(carDao.findByYearsRange(1990, 1999)).thenReturn(carsByYearRange);

        //When & Then
        mockMvc.perform(get("/cars/carsByYearRange/{start}+{end}", 1990, 1999))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldGetCarsById() throws Exception{
        //When & Then
        mockMvc.perform(get("/cars/carById/{id}", CAR_ID))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark", Is.is("Fiat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Is.is("126p")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Is.is("Blue")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", Is.is(1990)));
    }

    @Test
    public void shouldAddCar() throws Exception{
        //Given
        Car newCar = new Car(3L, "Trabant", "100L", "Lila", 1980);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newCar);

        //When & Then
        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.carId", Is.is(3)))
                .andExpect(jsonPath("$.mark", Is.is("Trabant")))
                .andExpect(jsonPath("$.model", Is.is("100L")))
                .andExpect(jsonPath("$.color", Is.is("Lila")))
                .andExpect(jsonPath("$.year", Is.is(1980)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldEditCar() throws Exception{
        //Given
        Car newCar = new Car(1L, "Trabant", "100L", "Lila", 1980);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(newCar);

        //When & Then
        mockMvc.perform(put("/cars", CAR_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.carId", Is.is(1)))
                .andExpect(jsonPath("$.mark", Is.is("Trabant")))
                .andExpect(jsonPath("$.model", Is.is("100L")))
                .andExpect(jsonPath("$.color", Is.is("Lila")))
                .andExpect(jsonPath("$.year", Is.is(1980)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRemoveCar() throws Exception{
        //When & Then
        mockMvc.perform(delete("/cars/removeCarById/{id}", CAR_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}