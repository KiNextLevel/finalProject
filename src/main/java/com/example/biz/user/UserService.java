package com.example.biz.user;

import java.util.List;

public interface UserService {
    boolean insert(UserVO vo);
    boolean update(UserVO vo);
    boolean delete(UserVO vo);
    UserVO getUser(UserVO vo);
    List<UserVO> getUserList(UserVO vo);
}
