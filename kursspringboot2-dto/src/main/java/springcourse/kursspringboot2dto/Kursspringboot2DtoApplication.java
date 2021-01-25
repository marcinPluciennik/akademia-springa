package springcourse.kursspringboot2dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Kursspringboot2DtoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Kursspringboot2DtoApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Order, OrderDTO>() {
            @Override
            protected void configure() {
                map().setCity(source.getAddress().getCity());
                map().setStreet(source.getAddress().getStreet());
                map().setName(source.getCustomer().getName());
                map().setLocalDate(LocalDate.now());
            }
        });
        return modelMapper;
    }

}
