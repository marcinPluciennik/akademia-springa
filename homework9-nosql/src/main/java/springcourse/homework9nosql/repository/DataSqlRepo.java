package springcourse.homework9nosql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcourse.homework9nosql.model.DataSql;

public interface DataSqlRepo extends JpaRepository<DataSql, Long> {
}
