package project.study.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.study.user.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
