package project.study.board.dto;

import lombok.Data;
import project.study.board.domain.Board;
import project.study.board.domain.BoardType;
import project.study.user.domain.User;

import java.time.LocalDateTime;

@Data
public class FindBoardResponseDto {
    private Long boardId;
    private String title;
    private String contents;
    private String deleteYn;
    private int views;
    private BoardType boardType;

    private Long userId;
    private String userLoginId;

    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;


    public FindBoardResponseDto(Board board) {
        boardId = board.getId();
        title = board.getTitle();
        contents = board.getContents();
        deleteYn = board.getDeleteYn();
        views = board.getViews();
        boardType = board.getBoardType();

        userId = board.getUser().getId();
        userLoginId = board.getUser().getUserLoginId();

        createdBy = board.getCreatedBy();
        createdDate = board.getCreatedDate();
        modifiedBy = board.getLastModifiedBy();
        modifiedDate = board.getLastModifiedDate();
    }
}
