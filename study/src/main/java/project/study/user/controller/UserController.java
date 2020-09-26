package project.study.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.study.user.dto.CreateUserRequestDto;
import project.study.user.dto.CreateUserResponseDto;
import project.study.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("users")
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto join = userService.join(createUserRequestDto);
        return join;
    }
}
