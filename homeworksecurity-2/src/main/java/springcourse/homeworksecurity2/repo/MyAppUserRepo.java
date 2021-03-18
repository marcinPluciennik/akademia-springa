package springcourse.homeworksecurity2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.homeworksecurity2.entity.MyAppUser;

import java.util.Optional;

@Repository
public interface MyAppUserRepo extends JpaRepository<MyAppUser, Long> {
    Optional<MyAppUser> findMyAppUserByUsername(String username);
}
