package springcourse.homework9nosql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springcourse.homework9nosql.aspect.mongodb.AfterTimeAspectMongoDbRead;
import springcourse.homework9nosql.aspect.mongodb.AfterTimeAspectMongoDbSave;
import springcourse.homework9nosql.aspect.mongodb.BeforeTimeAspectMongoDbRead;
import springcourse.homework9nosql.aspect.mongodb.BeforeTimeAspectMongoDbSave;
import springcourse.homework9nosql.model.DataMongo;
import springcourse.homework9nosql.repository.DataMongoRepo;

import java.util.List;

@Service
public class DataMongoServiceImpl implements DataMongoService{

    private DataMongoRepo dataMongoRepo;

    @Autowired
    public DataMongoServiceImpl(DataMongoRepo dataMongoRepo) {
        this.dataMongoRepo = dataMongoRepo;
    }

    @Override
    @BeforeTimeAspectMongoDbSave
    @AfterTimeAspectMongoDbSave
    public void saveAllData(List<DataMongo> data) {
        dataMongoRepo.saveAll(data);
    }

    @Override
    @BeforeTimeAspectMongoDbRead
    @AfterTimeAspectMongoDbRead
    public List<DataMongo> findAllData() {
        return dataMongoRepo.findAll();
    }
}
