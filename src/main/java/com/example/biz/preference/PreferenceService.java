package com.example.biz.preference;

import java.util.List;

public interface PreferenceService {

    boolean insert(PreferenceVO vo);
    boolean update(PreferenceVO vo);
    boolean delete(PreferenceVO vo);
    PreferenceVO getPreference(PreferenceVO vo);
    List<PreferenceVO> getPreferenceList(PreferenceVO vo);
}
