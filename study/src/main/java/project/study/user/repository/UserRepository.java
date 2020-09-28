package project.study.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.study.user.domain.User;
import project.study.user.dto.findUserList.FindUserListResponseDto;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserLoginIdAndPass1(String userLoginId, String pass1);

    Page<User> findByName(String name, Pageable pageable);
}
