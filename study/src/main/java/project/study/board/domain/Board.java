package project.study.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study.board.dto.CreateBoardRequestDto;
import project.study.user.domain.User;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "BOARD_SEQ"
        , initialValue = 1, allocationSize = 1)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENARATOR")
    @Column(name = "board_id")
    private Long id;

    private String title;
    @Lob
    private String contents;
    private int views;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public Board createBoard(User user, CreateBoardRequestDto createBoardRequestDto){
        Board board = new Board();
        board.title = createBoardRequestDto.getTitle();
        board.contents = createBoardRequestDto.getContents();
        board.views = 0;
        board.user = user;
        board.boardType = createBoardRequestDto.getBoardType();
        return board;
    }

    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }
}
