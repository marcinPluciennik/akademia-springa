package springcourse.homework9nosql.service;

import springcourse.homework9nosql.model.DataSql;

import java.util.List;

public interface DataSqlService {
    void saveAllData(List<DataSql> data);
    List<DataSql> findAllData();
}
