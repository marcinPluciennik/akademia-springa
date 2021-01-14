package springcourse.homework9nosql;

import org.springframework.stereotype.Component;
import springcourse.homework9nosql.controller.CsvController;
import springcourse.homework9nosql.csv.CsvHelperImpl;
import springcourse.homework9nosql.service.DataMongoServiceImpl;
import springcourse.homework9nosql.service.DataSqlServiceImpl;

@Component
public class Start {

    public Start(CsvController csvController, CsvHelperImpl csvHelper, DataSqlServiceImpl dataSqlServiceImpl,
                 DataMongoServiceImpl dataMongoServiceImpl) {

        csvController.downloadCsvFile();

        dataSqlServiceImpl.saveAllData(csvHelper.csvToDataSql());
        dataSqlServiceImpl.findAllData();

        dataMongoServiceImpl.saveAllData(csvHelper.csvToDataMongo());
        dataMongoServiceImpl.findAllData();
    }
}
