package project.study.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.user.domain.User;
import project.study.user.dto.CreateUserRequestDto;
import project.study.user.dto.CreateUserResponseDto;
import project.study.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public CreateUserResponseDto join(CreateUserRequestDto createUserRequestDto){
        User user = User.createUser(createUserRequestDto);
        CreateUserResponseDto userResponseDto = new CreateUserResponseDto(user);
        userRepository.save(user);
        return userResponseDto;
    }

}
