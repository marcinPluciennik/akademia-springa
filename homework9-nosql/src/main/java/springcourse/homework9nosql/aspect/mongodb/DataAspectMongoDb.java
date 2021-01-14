package springcourse.homework9nosql.aspect.mongodb;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataAspectMongoDb {
    Long startSave;
    Long startRead;

    @Before("@annotation(springcourse.homework9nosql.aspect.mongodb.BeforeTimeAspectMongoDbSave)")
    public void startTimeAspectSave(){
        startSave = System.currentTimeMillis();
    }

    @After("@annotation(springcourse.homework9nosql.aspect.mongodb.AfterTimeAspectMongoDbSave)")
    public void stopTimeAspectSave(){
        Long stop = System.currentTimeMillis();
        System.out.println("Save 1000 records to MONGODB : " + (stop - startSave) + " [ms]");
    }

    @Before("@annotation(springcourse.homework9nosql.aspect.mongodb.BeforeTimeAspectMongoDbRead)")
    public void startTimeAspectRead(){
        startRead = System.currentTimeMillis();
    }

    @After("@annotation(springcourse.homework9nosql.aspect.mongodb.AfterTimeAspectMongoDbRead)")
    public void stopTimeAspectRead(){
        Long stop = System.currentTimeMillis();
        System.out.println("Read 1000 records from MONGODB : " + (stop - startRead) + " [ms]");
    }
}
