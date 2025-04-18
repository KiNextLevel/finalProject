package com.example.biz.participant.impl;

import com.example.biz.participant.ParticipantService;
import com.example.biz.participant.ParticipantVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("participantService")
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantDAO participantDAO;

    @Override
    public boolean insert(ParticipantVO vo) {
        return participantDAO.insert(vo);
    }

    @Override
    public boolean update(ParticipantVO vo) {
        return false;
    }

    @Override
    public boolean delete(ParticipantVO vo) {
        return participantDAO.delete(vo);
    }

    @Override
    public ParticipantVO getParticipant(ParticipantVO vo) {
        return participantDAO.getParticipant(vo);
    }

    @Override
    public List<ParticipantVO> getParticipantList(ParticipantVO vo) {
        return participantDAO.getParticipantList(vo);
    }
}
