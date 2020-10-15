package project.study.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.study.user.domain.User;
import project.study.user.dto.CreateUserRequestDto;
import project.study.user.dto.CreateUserResponseDto;
import project.study.user.dto.findUserList.FindUserListRequestDto;
import project.study.user.dto.findUserList.FindUserListResponseDto;
import project.study.user.dto.findUserOne.FindUserResponseDto;
import project.study.user.dto.UserLoginResponseDto;
import project.study.user.dto.updateUser.UpdateUserRequestDto;
import project.study.user.repository.UserRepository;
import project.study.user.repository.UserRepositoryCustom;
import project.study.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;
    private final UserRepositoryCustom userRepositoryCustom;
    private final UserRepository userRepository;


    @Resource(name = "loginUserBean")
    private User loginUserBean;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public void loginPro(@RequestBody UserLoginResponseDto userLoginResponseDto) {
        boolean loginUserInfo = userService.getLoginUserInfo(userLoginResponseDto);
        if (loginUserInfo){
            System.out.println("로그인 성공");
        }else{
            System.out.println("로그인 실패");
        }

    }

    /**
     * 회원가입
     */
    @PostMapping("/user")
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        if(!createUserRequestDto.getPass1().equals(createUserRequestDto.getPass2())){
            throw new IllegalStateException("비밀번호 불일치");
        }
        CreateUserResponseDto join = userService.join(createUserRequestDto);
        return join;
    }

    /**
     * 개인 회원 조회
     */
    @GetMapping("/user/{id}")
    public FindUserResponseDto findUser(@PathVariable("id")Long id){
        FindUserResponseDto findUserResponseDto = userRepositoryCustom.findUser(id);

        return findUserResponseDto;
    }

    /**
     * 회원 리스트
     */
    @PostMapping("/userlist")
    public Page<FindUserListResponseDto> findUserList(@RequestBody(required = false) FindUserListRequestDto findUserListRequestDto, Pageable pageable){
        Page<User> userList; //리팩토링 해야함.. qeuryDsl
        if(findUserListRequestDto.getUserLoginId() != null){ //로그인 아이디 조회
            userList = userRepository.findByUserLoginId(findUserListRequestDto.getUserLoginId(),pageable);
        }else if(findUserListRequestDto.getName() != null){
            userList = userRepository.findByName(findUserListRequestDto.getName(), pageable);
        }else{
            userList = userRepository.findAll(pageable);
        }

        Page<FindUserListResponseDto> map = userList.map(user -> new FindUserListResponseDto(user));
        return map;
    }

    /**
     * 회원정보 수정
     */
    @PostMapping("/user/{id}")
    public Long updateUser(@PathVariable("id")Long id,@RequestBody UpdateUserRequestDto updateUserRequestDto) {
        Long updateUserId = userService.updateUser(id,updateUserRequestDto);
        return updateUserId;
    }


    /**
     * 회원 탈퇴 삭제
     */
    @DeleteMapping("/user/{id}")
    public Long deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

}
