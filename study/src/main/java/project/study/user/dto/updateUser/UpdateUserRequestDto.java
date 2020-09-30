package project.study.user.dto.updateUser;

import lombok.Data;
import project.study.common.domain.Address;
import project.study.user.domain.User;

@Data
public class UpdateUserRequestDto {
    private String name;
    private String pass1;
    private String pass2;

    private String deleteYn;

    private String city;
    private String street;
    private String zipcode;

}
