package com.example.common.biz.chatRoom.impl;

import com.example.common.biz.chatRoom.ChatRoomService;
import com.example.common.biz.chatRoom.ChatRoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService {
    @Autowired
    private ChatRoomDAO ChatRoomDAO;

    @Override
    public boolean insert(ChatRoomVO vo) {
        return ChatRoomDAO.insert(vo);
    }

    @Override
    public boolean update(ChatRoomVO vo) {
        return ChatRoomDAO.update(vo);
    }

    @Override
    public boolean delete(ChatRoomVO vo) {
        return false;
    }

    @Override
    public ChatRoomVO getChatRoom(ChatRoomVO vo) {
        return null;
    }

    @Override
    public List<ChatRoomVO> getChatRoomList(ChatRoomVO vo) {
        return ChatRoomDAO.getChatRoomList(vo);
    }
}
