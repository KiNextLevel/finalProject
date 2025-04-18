package com.example.biz.board.impl;

import com.example.biz.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardSerivceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;

    @Override
    public boolean insert(BoardVO vo) {
        return boardDAO.insert();
    }

    @Override
    public boolean update(BoardVO vo) {
        return boardDAO.update();
    }

    @Override
    public boolean delete(BoardVO vo) {
        return boardDAO.delete();
    }

    @Override
    public BoardVO getBoard(BoardVO vo) {
        return boardDAO.getBoard();
    }

    @Override
    public List<BoardVO> getBoardList(BoardVO vo) {
        return boardDAO.getBoardList();
    }
}
