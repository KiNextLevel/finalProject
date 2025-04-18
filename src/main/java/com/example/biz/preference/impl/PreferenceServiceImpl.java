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
    public boolean insert(PreferenceVO preferenceVO) {
        return preferenceDAO.insert(preferenceVO);
    }

    @Override
    public boolean update(PreferenceVO preferenceVO) {
        return preferenceDAO.update(preferenceVO);
    }

    @Override
    public boolean delete(PreferenceVO preferenceVO) {
        return preferenceDAO.delete(preferenceVO);
    }

    @Override
    public PreferenceVO getPreference(PreferenceVO preferenceVO) {
        return preferenceDAO.getMember(preferenceVO);
    }

    @Override
    public List<PreferenceVO> getPreferenceList(PreferenceVO preferenceVO) {
        return preferenceDAO.insert(preferenceVO);
    }
}
