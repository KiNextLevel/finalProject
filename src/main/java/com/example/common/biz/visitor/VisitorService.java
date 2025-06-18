package com.example.common.biz.visitor;

import java.util.List;

public interface VisitorService {
    boolean insert(VisitorVO vo);
    boolean update(VisitorVO vo);
    boolean delete(VisitorVO vo);
    VisitorVO getVisitor(VisitorVO vo);
    List<VisitorVO> getVisitorList(VisitorVO vo);
}
