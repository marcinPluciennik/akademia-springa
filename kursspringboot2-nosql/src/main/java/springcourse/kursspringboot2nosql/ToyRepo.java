package springcourse.kursspringboot2nosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepo extends MongoRepository<Toy, String> {
}
