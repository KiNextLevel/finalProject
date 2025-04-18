package com.example.biz.preference.impl;

import com.example.biz.preference.PreferenceService;
import com.example.biz.preference.PreferenceVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("preferenceService")
public class PreferenceServiceImpl implements PreferenceService {
    @Override
    public boolean insert(PreferenceVO vo) {
        return false;
    }

    @Override
    public boolean update(PreferenceVO vo) {
        return false;
    }

    @Override
    public boolean delete(PreferenceVO vo) {
        return false;
    }

    @Override
    public PreferenceVO getPreference(PreferenceVO vo) {
        return null;
    }

    @Override
    public List<PreferenceVO> getPreferenceList(PreferenceVO vo) {
        return List.of();
    }
}
