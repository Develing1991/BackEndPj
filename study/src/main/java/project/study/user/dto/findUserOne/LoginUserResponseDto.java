package project.study.user.dto.findUserOne;

import lombok.Data;
import project.study.common.domain.Address;
import project.study.user.domain.User;
import project.study.user.domain.UserType;

@Data
public class LoginUserResponseDto {
    private Long id;
    private String name;
    private String userLoginId;
    private UserType userType;
    private String deleteYn;

    public LoginUserResponseDto(Long id) {
        this.id = id;
    }

    public LoginUserResponseDto(User user) {
        id = user.getId();
        name = user.getName();
        userLoginId = user.getUserLoginId();
        userType = user.getUserType();
        deleteYn = user.getDeleteYn();
    }
}
