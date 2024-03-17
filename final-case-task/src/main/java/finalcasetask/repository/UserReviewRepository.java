package finalcasetask.repository;

import finalcasetask.payload.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview,Long> {
}
