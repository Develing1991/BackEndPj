package project.study.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.study.board.domain.Board;
import project.study.board.domain.BoardType;
import project.study.user.domain.User;

public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findAllByBoardTypeAndTitleLike(BoardType type,String title, Pageable pageable);
}
