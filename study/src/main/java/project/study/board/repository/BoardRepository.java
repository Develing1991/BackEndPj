package project.study.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.study.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {

    Page<Board> findAllByTitle(String title,Pageable pageable);
}
