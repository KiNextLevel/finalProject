package com.example.biz.participant;

import java.util.List;

public interface ParticipantService {
    boolean insert(ParticipantVO vo);
    boolean update(ParticipantVO vo);
    boolean delete(ParticipantVO vo);
    ParticipantVO getParticipant(ParticipantVO vo);
    List<ParticipantVO> getParticipantList(ParticipantVO vo);
}
