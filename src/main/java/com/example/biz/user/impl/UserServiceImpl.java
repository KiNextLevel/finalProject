package com.example.biz.user.impl;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public boolean insert(UserVO vo) {
        return false;
    }

    @Override
    public boolean update(UserVO vo) {
        return false;
    }

    @Override
    public boolean delete(UserVO vo) {
        return false;
    }

    @Override
    public UserVO getUser(UserVO vo) {
        return null;
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return List.of();
    }
}
