package project.study.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.study.board.domain.Board;
import project.study.board.domain.BoardType;
import project.study.board.dto.CreateBoardRequestDto;
import project.study.board.dto.FindBoardResponseDto;
import project.study.board.dto.UpdateBoardRequestDto;
import project.study.board.repository.BoardRepository;
import project.study.board.service.BoardService;
import project.study.user.domain.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    /**
     * 게시판 생성
     */
    @PostMapping("/board")
    public Long createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
        return boardService.createBoard(createBoardRequestDto);
    }

    /**
     * 게시판 조회 One
     */
    @GetMapping("/board/{id}")
    public FindBoardResponseDto findBoard(@PathVariable("id") Long id) {
        return boardService.findBoardOne(id);
    }

    /**
     * 게시판 조회 list
     */
    @GetMapping("/board/list/{type}")
    public Page<FindBoardResponseDto> findBoardList(@PathVariable(value = "type",required = false) BoardType type,
                                                    @RequestParam(value = "title", defaultValue = "%%") String title,
                                                    Pageable pageable){
        return boardService.findBoardList(type, title, pageable);
    }

    /**
     * 게시물 수정
     */
    @PostMapping("/board/{id}")
    public Long updateBoard(@PathVariable("id")Long id,@RequestBody UpdateBoardRequestDto updateBoardRequestDto) {
        return boardService.updateBoard(id, updateBoardRequestDto);
    }

    /**
     * 게시물 삭제
     */
    @DeleteMapping("/board/{id}")
    private Long deleteBoard(@PathVariable("id") Long id) {
        return boardService.deleteBoard(id);
    }


}
