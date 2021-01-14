package springcourse.homework9nosql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springcourse.homework9nosql.aspect.sql.AfterTimeAspectSqlRead;
import springcourse.homework9nosql.aspect.sql.AfterTimeAspectSqlSave;
import springcourse.homework9nosql.aspect.sql.BeforeTimeAspectSqlRead;
import springcourse.homework9nosql.aspect.sql.BeforeTimeAspectSqlSave;
import springcourse.homework9nosql.model.DataSql;
import springcourse.homework9nosql.repository.DataSqlRepo;

import java.util.List;

@Service
public class DataSqlServiceImpl implements DataSqlService{

    private DataSqlRepo dataSqlRepo;

    @Autowired
    public DataSqlServiceImpl(DataSqlRepo dataSqlRepo) {
        this.dataSqlRepo = dataSqlRepo;
    }

    @Override
    @BeforeTimeAspectSqlSave
    @AfterTimeAspectSqlSave
    public void saveAllData(List<DataSql> data) {
        dataSqlRepo.saveAll(data);
    }

    @Override
    @BeforeTimeAspectSqlRead
    @AfterTimeAspectSqlRead
    public List<DataSql> findAllData() {
        return dataSqlRepo.findAll();
    }
}
