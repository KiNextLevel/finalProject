package com.example.biz.preference;

import java.util.List;

public interface PreferenceService {
    boolean insert(PreferenceVO preferenceVO);
    boolean update(PreferenceVO preferenceVO);
    boolean delete(PreferenceVO preferenceVO);

    PreferenceVO getMember(PreferenceVO preferenceVO);
    List<PreferenceVO> getMemberList(PreferenceVO preferenceVO);
}
