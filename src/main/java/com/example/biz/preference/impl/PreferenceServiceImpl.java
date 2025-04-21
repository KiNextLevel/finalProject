package com.example.biz.preference.impl;

import com.example.biz.preference.PreferenceService;

import java.util.List;

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
