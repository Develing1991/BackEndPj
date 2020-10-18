package project.study.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.board.domain.Board;
import project.study.board.domain.BoardType;
import project.study.board.dto.CreateBoardRequestDto;
import project.study.board.dto.FindBoardResponseDto;
import project.study.board.dto.UpdateBoardRequestDto;
import project.study.board.repository.BoardRepository;
import project.study.board.repository.BoardRepositoryCustom;
import project.study.user.domain.User;
import project.study.user.dto.findUserList.FindUserListResponseDto;
import project.study.user.repository.UserRepository;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepositoryCustom boardRepositoryCustom;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Resource(name = "loginUserBean")
    private User loginUserBean;

    @Transactional
    public Long createBoard(CreateBoardRequestDto createBoardRequestDto) {
//        if(loginUserBean.getId() == null){
//            throw new IllegalStateException("로그인이 필요한 서비스 입니다.");
//        }
        User findUser = userRepository.findById(createBoardRequestDto.getUserId()).get();
        return boardRepositoryCustom.createBoard(findUser, createBoardRequestDto);
    }

    @Transactional
    public Long updateBoard(Long id, UpdateBoardRequestDto updateBoardRequestDto) {
        return boardRepositoryCustom.update(id, updateBoardRequestDto);
    }

    @Transactional
    public Long deleteBoard(Long id) {
        return boardRepositoryCustom.deleteBoard(id);
    }

    public FindBoardResponseDto findBoardOne(Long id){
        return boardRepositoryCustom.findBoardOne(id);
    }

    public Page<FindBoardResponseDto> findBoardList(BoardType type,String title,  Pageable pageable){
        title = "%" + title +"%";
        Page<FindBoardResponseDto> map;
//        if(type.equals("null")){
//            Page<Board> findAll = boardRepository.findAll(pageable);
//            map = findAll.map(board -> new FindBoardResponseDto(board));
//        }else{
//            Page<Board> findBoard = boardRepository.findAllByBoardType(type, pageable);
//            map = findBoard.map(board -> new FindBoardResponseDto(board));
//        }
        Page<Board> findBoard = boardRepository.findAllByBoardTypeAndTitleLike(type, title,pageable);
        map = findBoard.map(board -> new FindBoardResponseDto(board));
        return map;
    }


}
