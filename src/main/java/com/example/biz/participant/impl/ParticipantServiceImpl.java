package com.example.biz.participant.impl;

import com.example.biz.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("participantService")
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantDAO participantDAO;

    @Override
    public boolean insert(ParticipantVO vo) {
        return participantDAO.insert();
    }

    @Override
    public boolean update(ParticipantVO vo) {
        return participantDAO.update();
    }

    @Override
    public boolean delete(ParticipantVO vo) {
        return participantDAO.delete();
    }

    @Override
    public ParticipantVO getParticipant(ParticipantVO vo) {
        return participantDAO.getParticipant();
    }

    @Override
    public List<ParticipantVO> getParticipantList(ParticipantVO vo) {
        return participantDAO.getParticipantList();
    }
}
