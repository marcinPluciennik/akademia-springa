package springcourse.homeworksecurity6jwtasymmetricapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieApi {

    List<String> movies;

    public MovieApi() {
        this.movies = new ArrayList<>();
        movies.add("Alien 1");
        movies.add("Alien 2");
        movies.add("Alien 3");
    }

    @GetMapping("/api/movies")
    public List<String> getMovies(){
        return movies;
    }

    @PostMapping("/api/movies") // to use it add http.csrf().disable(); in WebSecurityConfig
    public void addMovie(@RequestBody String movie){
        movies.add(movie);
    }
}
