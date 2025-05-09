package com.example.common.biz.report.impl;

import com.example.common.biz.report.ReportService;
import com.example.common.biz.report.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportService")
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDAO3 reportDAO;

    @Override
    public boolean insert(ReportVO vo) {
        return reportDAO.insert(vo);
    }

    @Override
    public boolean update(ReportVO vo) {
        return false;
    }

    @Override
    public boolean delete(ReportVO vo) {
        return reportDAO.delete(vo);
    }

    @Override
    public ReportVO getReport(ReportVO vo) {
        return reportDAO.getReport(vo);
    }

    @Override
    public List<ReportVO> getReportList(ReportVO vo) {
        return reportDAO.getReportList(vo);
    }
}
