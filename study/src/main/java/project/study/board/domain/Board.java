package project.study.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study.board.dto.CreateBoardRequestDto;
import project.study.board.dto.UpdateBoardRequestDto;
import project.study.common.domain.BaseAuditingUser;
import project.study.user.domain.User;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "BOARD_SEQ"
        , initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseAuditingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "board_id")
    private Long id;

    private String title;
    @Lob
    private String contents;
    private int views;

    private String deleteYn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public static Board createBoard(User user, CreateBoardRequestDto createBoardRequestDto){
        Board board = new Board();
        board.title = createBoardRequestDto.getTitle();
        board.contents = createBoardRequestDto.getContents();
        board.views = 0;
        board.deleteYn = "N";
        board.boardType = createBoardRequestDto.getBoardType();
        board.setUser(user);
        return board;
    }

    public static Board updateBoard(Board board,UpdateBoardRequestDto updateBoardRequestDto) {
        board.title = updateBoardRequestDto.getTitle();
        board.contents = updateBoardRequestDto.getContents();
        board.deleteYn = updateBoardRequestDto.getDeleteYn();
        board.boardType = updateBoardRequestDto.getBoardType();
        return board;
    }

    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }
}
