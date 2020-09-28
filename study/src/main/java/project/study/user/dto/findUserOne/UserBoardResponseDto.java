package project.study.user.dto.findUserOne;

import lombok.Data;
import project.study.board.domain.Board;
import project.study.board.domain.BoardType;
import project.study.user.domain.User;

@Data
public class UserBoardResponseDto {
    private Long id;
    private String title;
    private int views;
    private BoardType boardType;

    public UserBoardResponseDto(Board board) {
        id = board.getId();
        title = board.getTitle();
        views = board.getViews();
        boardType = board.getBoardType();
    }
}
