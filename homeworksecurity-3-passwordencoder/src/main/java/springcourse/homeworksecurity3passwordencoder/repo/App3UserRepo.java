package springcourse.homeworksecurity3passwordencoder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.homeworksecurity3passwordencoder.entity.App3User;

import java.util.Optional;

@Repository
public interface App3UserRepo extends JpaRepository<App3User, Long> {

    Optional<App3User> findAllByUsername(String username);
}
