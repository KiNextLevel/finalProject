package com.example.common.biz.chatMessage.impl;

import com.example.common.biz.chatMessage.ChatMessageService;
import com.example.common.biz.chatMessage.ChatMessageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    private ChatMessageDAO chatMessageDAO;
    @Override
    public boolean insert(ChatMessageVO vo) {
        return chatMessageDAO.insert(vo);
    }

    @Override
    public boolean update(ChatMessageVO vo) {
        return false;
    }

    @Override
    public boolean delete(ChatMessageVO vo) {
        return false;
    }

    @Override
    public ChatMessageVO getChatMessage(ChatMessageVO vo) {
        return null;
    }

    @Override
    public List<ChatMessageVO> getChatMessageList(ChatMessageVO vo) {
        return chatMessageDAO.getChatMessageList(vo);
    }
}
