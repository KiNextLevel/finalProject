package com.example.biz.preference.impl;

import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("preferenceService")
public class PreferenceServiceImpl implements PreferenceService {
    @Autowired
    private PreferenceDAO preferenceDAO;

    @Override
    public boolean insert(PreferenceVO vo) {
        return preferenceDAO.insert(vo);
    }

    @Override
    public boolean update(PreferenceVO vo) {
        return preferenceDAO.update(vo);
    }

    @Override
    public boolean delete(PreferenceVO vo) {
        return false;
    }

    @Override
    public PreferenceVO getPreference(PreferenceVO vo) {
        return preferenceDAO.getPreference(vo);
    }

    @Override
    public List<PreferenceVO> getPreferenceList(PreferenceVO vo) {
        return preferenceDAO.getPreferenceList(vo);
    }
}
