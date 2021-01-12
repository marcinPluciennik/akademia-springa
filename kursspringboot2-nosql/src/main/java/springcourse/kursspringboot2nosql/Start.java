package springcourse.kursspringboot2nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private ToyRepo toyRepo;

    @Autowired
    public Start(ToyRepo toyRepo) {
        this.toyRepo = toyRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        Toy toyTeddy = new Toy("Miś Uszatek", ToyType.TEDDY_BEAR);
        Toy toyDoll = new Toy("Lala", ToyType.DOLL);

        //SAVE
//        toyRepo.save(toyTeddy);
//        toyRepo.save(toyDoll);


        //UPDATE
        Toy toy = toyRepo.findById("5ffddbf02e6f9d354a591dc6").get();
        toy.setToyType(ToyType.DOLL);
        toyRepo.save(toy);

        //DELETE
        toyRepo.deleteById("5ffddbf02e6f9d354a591dc6");


        //READ
        //System.out.println(toyRepo.findById("5ffddbf02e6f9d354a591dc6").get());
        toyRepo.findAll().forEach(System.out::println);

    }
}
