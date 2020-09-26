package project.study.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.study.user.domain.User;

@Data
public class CreateUserRequestDto {
    private String name;
    private String userLoginId;
    private String pass1;
    private String pass2;

    private String city;
    private String street;
    private String zipcode;


}
