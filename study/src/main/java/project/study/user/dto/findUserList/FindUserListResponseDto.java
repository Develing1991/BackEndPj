package project.study.user.dto.findUserList;

import lombok.Data;
import project.study.common.domain.Address;
import project.study.user.domain.User;

import java.time.LocalDateTime;

@Data
public class FindUserListResponseDto {
    private Long id;
    private String name;
    private String userLoginId;
    private Address address;
    private String deletedYn;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public FindUserListResponseDto(User user) {
        id = user.getId();
        name = user.getName();
        userLoginId = user.getUserLoginId();
        address = user.getAddress();
        deletedYn = user.getDeleteYn();
        createdDate = user.getCreatedDate();
        lastModifiedDate = user.getLastModifiedDate();
    }
}
