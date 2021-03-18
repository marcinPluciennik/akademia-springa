package springcourse.homeworksecurity2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springcourse.homeworksecurity2.entity.VerificationToken;

@Repository
@Transactional
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByValue(String value);
    void deleteByValue(String value);
}
