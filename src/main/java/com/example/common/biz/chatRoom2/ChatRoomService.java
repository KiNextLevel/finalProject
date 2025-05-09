package com.example.common.biz.chatRoom2;

import java.util.List;

public interface ChatRoomService {

    boolean insert(ChatRoomVO vo);

    boolean update(ChatRoomVO vo);

    boolean delete(ChatRoomVO vo);

    ChatRoomVO getChatRoom(ChatRoomVO vo);

    List<ChatRoomVO> getChatRoomList(ChatRoomVO vo);

}