package com.example.common.biz.visitor.impl;

import com.example.common.biz.visitor.VisitorService;
import com.example.common.biz.visitor.VisitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorDAO visitorDAO;

    @Override
    public boolean insert(VisitorVO vo) {
        return visitorDAO.insert(vo);
    }

    @Override
    public boolean update(VisitorVO vo) {
        return false;
    }

    @Override
    public boolean delete(VisitorVO vo) {
        return false;
    }

    @Override
    public VisitorVO getVisitor(VisitorVO vo) {
        return visitorDAO.getVisitor(vo);
    }

    @Override
    public List<VisitorVO> getVisitorList(VisitorVO vo) {
        return visitorDAO.getVisitorList(vo);
    }
}
