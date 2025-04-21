package com.example.biz.board;

import java.util.List;

public interface BoardService {
    boolean insert(BoardVO vo);
    boolean update(BoardVO vo);
    boolean delete(BoardVO vo);
    BoardVO getBoard(BoardVO vo);
    List<BoardVO> getBoardList(BoardVO vo);
}
