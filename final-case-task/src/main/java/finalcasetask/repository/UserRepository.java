package finalcasetask.repository;

import finalcasetask.payload.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {


}
