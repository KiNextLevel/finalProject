package com.example.biz.report.impl;

import com.example.biz.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDAO reportDAO;

    @Override
    public boolean insert(ReportVO vo) {
        return reportDAO.insert();
    }

    @Override
    public boolean update(ReportVO vo) {
        return reportDAO.update();
    }

    @Override
    public boolean delete(ReportVO vo) {
        return reportDAO.delete();
    }

    @Override
    public ReportVO getReport(ReportVO vo) {
        return reportDAO.getReport();
    }

    @Override
    public List<ReportVO> getReportList(ReportVO vo) {
        return reportDAO.getReportList();
    }
}
