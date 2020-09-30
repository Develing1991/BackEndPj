package project.study.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.study.board.domain.Board;
import project.study.board.dto.CreateBoardRequestDto;
import project.study.board.dto.FindBoardResponseDto;
import project.study.board.dto.UpdateBoardRequestDto;
import project.study.user.domain.User;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardRepositoryCustom {
    private final EntityManager em;

    @Transactional
    public Long createBoard(User user, CreateBoardRequestDto createBoardRequestDto) {
        Board board = Board.createBoard(user, createBoardRequestDto);
        em.persist(board);
        return board.getId();
    }

    @Transactional
    public Long update(Long id, UpdateBoardRequestDto updateBoardRequestDto) {
        Board findBoard = em.find(Board.class, id);
        Board updateBoard = Board.updateBoard(findBoard, updateBoardRequestDto);
        em.persist(updateBoard);
        return updateBoard.getId();
    }

    @Transactional
    public Long deleteBoard(Long id) {
        Board findBoard = em.find(Board.class, id);
        findBoard.setDeleteYn("Y");
        return findBoard.getId();
    }

    public FindBoardResponseDto findBoardOne(Long id) {
        Board findBoard = em.find(Board.class, id);
        FindBoardResponseDto findBoardResponseDto = new FindBoardResponseDto(findBoard);
        return findBoardResponseDto;
    }


}
