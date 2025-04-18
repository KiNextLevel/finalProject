package com.example.biz.board.impl;

import com.example.biz.board.BoardService;
import com.example.biz.board.BoardVO;

import java.util.List;

public class BoardSerivceImpl implements BoardService {
    @Override
    public boolean insert(BoardVO vo) {
        return false;
    }

    @Override
    public boolean update(BoardVO vo) {
        return false;
    }

    @Override
    public boolean delete(BoardVO vo) {
        return false;
    }

    @Override
    public BoardVO getBoard(BoardVO vo) {
        return null;
    }

    @Override
    public List<BoardVO> getBoardList(BoardVO vo) {
        return List.of();
    }
}
