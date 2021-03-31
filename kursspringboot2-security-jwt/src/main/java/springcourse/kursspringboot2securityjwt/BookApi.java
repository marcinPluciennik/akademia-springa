package springcourse.kursspringboot2securityjwt;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookApi {

    private List<String> bookList;

    public BookApi() {
        this.bookList = new ArrayList<>();
        bookList.add("Spring Boot 2");
        bookList.add("Spring in action 5");
    }

    @GetMapping
    public List<String> getBookList(){
        return bookList;
    }

    @PostMapping
    public void addBook(@RequestBody String book){
        bookList.add(book);
    }
}
