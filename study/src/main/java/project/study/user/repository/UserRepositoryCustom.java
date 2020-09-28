package project.study.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.study.common.domain.Address;
import project.study.user.domain.User;
import project.study.user.dto.findUserOne.FindUserResponseDto;
import project.study.user.dto.updateUser.UpdateUserRequestDto;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryCustom {
    private final EntityManager em;

    public Long deleteUser(Long id) {
        User deleteUser = em.find(User.class, id);
        deleteUser.setDeleteYn("Y");
        return deleteUser.getId();
    }
    public Long updateUser(Long id,UpdateUserRequestDto updateUserRequestDto) {
        User updateUser = em.find(User.class, id);
        //수정시 애내 화면에서 조회셋팅 후..해야 nullpoint 안뜸.
        updateUser.setName(updateUserRequestDto.getName());
        updateUser.setPass1(updateUserRequestDto.getPass1());
        updateUser.setDeleteYn("N");
        updateUser.setAddress(new Address(updateUserRequestDto.getCity()
                                ,updateUserRequestDto.getStreet()
                                ,updateUserRequestDto.getZipcode()));

        return updateUser.getId();
    }

    public FindUserResponseDto findUser(Long userid){

//        return em.createQuery("select u from User u " +
//                "   left join u.orders o where u.id = :userid", User.class)
//                .setParameter("userid",userid).getSingleResult();
//


        User finduser = em.createQuery("select u from User u " +
                "   left join u.orders o" +
                "   left join u.boards b" +
                "   left join o.orderItems oi " +
                "   left join oi.product p where u.id = :userid", User.class)
                .setParameter("userid", userid).getSingleResult();

        FindUserResponseDto findUserResponseDto = new FindUserResponseDto(finduser);
        return findUserResponseDto;


//        return em.createQuery("select u from User u where u.id = :userId", User.class)
//                .setParameter("userId",userid)
//                .getSingleResult();
    }
}
