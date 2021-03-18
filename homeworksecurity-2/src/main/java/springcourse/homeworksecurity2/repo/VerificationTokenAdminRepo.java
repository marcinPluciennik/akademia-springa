package springcourse.homeworksecurity2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcourse.homeworksecurity2.entity.VerificationTokenAdmin;

@Repository
public interface VerificationTokenAdminRepo extends JpaRepository<VerificationTokenAdmin, Long> {
    VerificationTokenAdmin findByValue(String value);
}
