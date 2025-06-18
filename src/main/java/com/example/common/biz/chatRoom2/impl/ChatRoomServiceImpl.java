package com.example.common.biz.chatRoom2.impl;

import com.example.common.biz.chatRoom2.ChatRoomService;
import com.example.common.biz.chatRoom2.ChatRoomVO;
import com.example.common.biz.chatRoom2.impl.ChatRoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    private ChatRoomDAO chatRoomDAO;

    @Override
    public boolean insert(ChatRoomVO vo) {
        return chatRoomDAO.insert(vo);
    }

    @Override
    public boolean update(ChatRoomVO vo) {
        return chatRoomDAO.update(vo);
    }

    @Override
    public boolean delete(ChatRoomVO vo) {
        return false;
    }

    @Override
    public ChatRoomVO getChatRoom(ChatRoomVO vo) {
        return chatRoomDAO.getChatRoom(vo);
    }

    @Override
    public List<ChatRoomVO> getChatRoomList(ChatRoomVO vo) {
        return chatRoomDAO.getChatRoomList(vo);
    }
}