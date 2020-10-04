package project.study.user.dto.findUserOne;

import lombok.Data;
import project.study.common.domain.Address;
import project.study.user.domain.User;
import project.study.user.domain.UserType;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FindUserResponseDto {
    private Long id;
    private String name;
    private String userLoginId;
    private UserType userType;
    private Address address;
    private String deleteYn;
    private List<UserOrderResponseDto> orders;
    private List<UserBoardResponseDto> boards;

    public FindUserResponseDto(User user) {
        id = user.getId();
        name = user.getName();
        userLoginId = user.getUserLoginId();
        userType = user.getUserType();
        address = user.getAddress();
        deleteYn = user.getDeleteYn();
        boards = user.getBoards().stream()
                .map(board -> new UserBoardResponseDto(board))
                .collect(Collectors.toList());

        orders = user.getOrders().stream()
                .map(order -> new UserOrderResponseDto(order))
                .collect(Collectors.toList());
    }
}
