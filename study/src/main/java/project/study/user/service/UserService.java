package project.study.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.user.domain.User;
import project.study.user.dto.CreateUserRequestDto;
import project.study.user.dto.CreateUserResponseDto;
import project.study.user.dto.UserLoginResponseDto;
import project.study.user.dto.updateUser.UpdateUserRequestDto;
import project.study.user.repository.UserRepository;
import project.study.user.repository.UserRepositoryCustom;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;

    @Resource(name = "loginUserBean")
    private User loginUserBean;

    @Transactional
    public Long deleteUser(Long id) {
        //추가 체크로직 사항
        //loginUserBean.getId(); 아이디 본인 꺼만 삭제가능
        //loginUserBean.getUserType(); admin은 전부 삭제가능
        return userRepositoryCustom.deleteUser(id);
    }
    @Transactional
    public Long updateUser(Long id,UpdateUserRequestDto updateUserRequestDto){
        if(!updateUserRequestDto.getPass1().equals(updateUserRequestDto.getPass2())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        //추가 체크로직 사항
        //loginUserBean.getId(); 아이디 본인 꺼만 변경가능
        //loginUserBean.getUserType(); admin은 전부 변경가능
        return userRepositoryCustom.updateUser(id,updateUserRequestDto);
    }

    @Transactional
    public CreateUserResponseDto join(CreateUserRequestDto createUserRequestDto){
        User user = User.createUser(createUserRequestDto);
        CreateUserResponseDto userResponseDto = new CreateUserResponseDto(user);
        userRepository.save(user);
        return userResponseDto;
    }

    public boolean getLoginUserInfo(UserLoginResponseDto userLoginResponseDto){
        Optional<User> userLoginInfo = userRepository.findByUserLoginIdAndPass1(userLoginResponseDto.getUserLoginId(), userLoginResponseDto.getPass1());
        System.out.println(userLoginInfo.isEmpty());
        if(!userLoginInfo.isEmpty()){
            loginUserBean.setUserLoginId(userLoginResponseDto.getUserLoginId());
            loginUserBean.setName(userLoginInfo.get().getName());
            loginUserBean.setUserLogin(true);
            loginUserBean.setUserType(userLoginInfo.get().getUserType()); //유저타입 admin, normal
            return true;
        }else{
            return false;
        }
    }


}
