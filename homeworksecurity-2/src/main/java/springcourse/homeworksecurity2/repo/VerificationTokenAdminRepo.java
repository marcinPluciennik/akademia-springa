package springcourse.homeworksecurity2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springcourse.homeworksecurity2.entity.VerificationTokenAdmin;

@Repository
@Transactional
public interface VerificationTokenAdminRepo extends JpaRepository<VerificationTokenAdmin, Long> {
    VerificationTokenAdmin findByValue(String value);
    void deleteByValue(String value);
}
