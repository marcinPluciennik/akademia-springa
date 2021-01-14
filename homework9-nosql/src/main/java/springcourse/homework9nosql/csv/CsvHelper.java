package springcourse.homework9nosql.csv;

import springcourse.homework9nosql.model.DataMongo;
import springcourse.homework9nosql.model.DataSql;

import java.util.List;

public interface CsvHelper{

    List<DataSql> csvToDataSql();
    List<DataMongo> csvToDataMongo();
}
