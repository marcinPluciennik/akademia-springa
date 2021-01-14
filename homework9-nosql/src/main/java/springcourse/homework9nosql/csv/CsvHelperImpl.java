package springcourse.homework9nosql.csv;

import org.springframework.stereotype.Service;
import springcourse.homework9nosql.model.DataMongo;
import springcourse.homework9nosql.model.DataSql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvHelperImpl implements CsvHelper{
    private static final String CSV_PATH = "/Users/marcin/Desktop/spring-course/homework9-nosql/src/main/java/springcourse/homework9nosql/source/source.csv";

    @Override
    public List<DataSql> csvToDataSql() {
        try {
            List<DataSql> myDataSql = new ArrayList<>();
            String line;
            FileReader fileReader = new FileReader(CSV_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                Long id = Long.parseLong(temp[0]);
                String first_name = temp[1];
                String last_name = temp[2];
                String email = temp[3];
                String gender = temp[4];
                String ip_address = temp[5];
                myDataSql.add(new DataSql(id, first_name, last_name, email, gender, ip_address));
            }
            bufferedReader.close();
            return myDataSql;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<DataMongo> csvToDataMongo() {
        try {
            List<DataMongo> myDataMongo = new ArrayList<>();
            String line;
            FileReader fileReader = new FileReader(CSV_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(",");
                String first_name = temp[1];
                String last_name = temp[2];
                String email = temp[3];
                String gender = temp[4];
                String ip_address = temp[5];
                myDataMongo.add(new DataMongo(first_name, last_name, email, gender,ip_address));
            }
            bufferedReader.close();
            return myDataMongo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
