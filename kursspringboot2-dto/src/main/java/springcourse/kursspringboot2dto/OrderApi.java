package springcourse.kursspringboot2dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    private List<OrderDTO> orderDtoList;

    @Autowired
    public OrderApi(ModelMapper modelMapper) {
        this.orderDtoList = new ArrayList<>();

        Order order1 = new Order( new Customer("Anna Nowak", "AW22222"),
                new Address("Przemysłowa", "Warszawa"));
        Order order2 = new Order( new Customer("Karol Przypalski", "KR33333"),
                new Address("Lotników", "Kraków"));

        orderDtoList.add(modelMapper.map(order1, OrderDTO.class));
        orderDtoList.add(modelMapper.map(order2, OrderDTO.class));
    }

    @GetMapping
    public List<OrderDTO> getOrders(){
        return orderDtoList;
    }
}
