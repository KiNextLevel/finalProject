package com.example.common.biz.report.mybatis;

import com.example.common.biz.report.ReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    boolean insert(ReportVO vo);
    boolean update(ReportVO vo);
    boolean delete(ReportVO vo);
    ReportVO getReport(ReportVO vo);
    List<ReportVO> getReportList(ReportVO vo);
}
