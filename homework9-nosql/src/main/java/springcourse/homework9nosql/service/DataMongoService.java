package springcourse.homework9nosql.service;


import springcourse.homework9nosql.model.DataMongo;
import java.util.List;

public interface DataMongoService{
    void saveAllData(List<DataMongo> data);
    List<DataMongo> findAllData();
}
