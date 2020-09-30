package project.study.board.dto;

import lombok.Data;
import project.study.board.domain.BoardType;

@Data
public class CreateBoardRequestDto {
    private String title;
    private String contents;
    private BoardType boardType;
}
