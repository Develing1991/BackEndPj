package project.study.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.study.user.domain.User;
import project.study.user.domain.UserType;

@Getter
@AllArgsConstructor
public class CreateUserResponseDto {
    private String name;
    private String userLoginId;
    private String pass1;
    private String pass2;

    private String city;
    private String street;
    private String zipcode;

    private UserType userType;

    public CreateUserResponseDto(User user) {
        name = user.getName();
        userLoginId = user.getUserLoginId();
        pass1 = user.getPass1();
        pass2 = user.getPass2();
        userType = user.getUserType();
        city = user.getAddress().getCity();
        street = user.getAddress().getStreet();
        zipcode = user.getAddress().getZipcode();
    }
}
