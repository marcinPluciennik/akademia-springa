package springcourse.homeworksecurity2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.homeworksecurity2.entity.MyAppUser;

@Repository
public interface MyAppUserRepo extends JpaRepository<MyAppUser, Long> {

    MyAppUser findAllByUsername(String username);
}
