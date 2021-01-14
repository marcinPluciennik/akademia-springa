package springcourse.homework9nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import springcourse.homework9nosql.model.DataMongo;

public interface DataMongoRepo extends MongoRepository<DataMongo, String> {
}
