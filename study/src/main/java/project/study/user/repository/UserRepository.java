package project.study.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.study.user.domain.User;
import project.study.user.dto.findUserList.FindUserListResponseDto;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserLoginIdAndPass1(String userLoginId, String pass1);

    Page<User> findByName(String name, Pageable pageable);
    Page<User> findByUserLoginId(String userLoginId, Pageable pageable);

//    @Query("SELECT u FROM User u WHERE u.userLoginId LIKE CONCAT('%',:userLoginId,'%')")
//    Optional<User> findUserLoginId(@Param("userLoginId")String userLoginId);
}
