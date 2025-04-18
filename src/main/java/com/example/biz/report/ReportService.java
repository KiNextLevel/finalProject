package com.example.biz.report;

import java.util.List;

public interface ReportService {
    boolean insert(ReportVO vo);
    boolean update(ReportVO vo);
    boolean delete(ReportVO vo);
    ReportVO getReport(ReportVO vo);
    List<ReportVO> getReportList(ReportVO vo);
}
