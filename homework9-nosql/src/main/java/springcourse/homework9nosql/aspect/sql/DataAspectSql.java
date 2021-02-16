package springcourse.homework9nosql.aspect.sql;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataAspectSql {
    private Long startSave;
    private Long startRead;

    @Before("@annotation(springcourse.homework9nosql.aspect.sql.BeforeTimeAspectSqlSave)")
    public void startTimeAspectSave(){
        startSave = System.currentTimeMillis();
    }

    @After("@annotation(springcourse.homework9nosql.aspect.sql.AfterTimeAspectSqlSave)")
    public void stopTimeAspectSave(){
        Long stop = System.currentTimeMillis();
        System.out.println("Save 1000 records to SQLDB : " + (stop - startSave) + " [ms]");
    }

    @Before("@annotation(springcourse.homework9nosql.aspect.sql.BeforeTimeAspectSqlRead)")
    public void startTimeAspectRead(){
        startRead = System.currentTimeMillis();
    }

    @After("@annotation(springcourse.homework9nosql.aspect.sql.AfterTimeAspectSqlRead)")
    public void stopTimeAspectRead(){
        Long stop = System.currentTimeMillis();
        System.out.println("Read 1000 records from SQLDB : " + (stop - startRead) + " [ms]");
    }
}
