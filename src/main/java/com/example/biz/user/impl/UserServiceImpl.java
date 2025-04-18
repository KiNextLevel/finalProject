package com.example.biz.user.impl;

import com.example.biz.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean insert(UserVO vo) {
        return userDAO.insert();
    }

    @Override
    public boolean update(UserVO vo) {
        return userDAO.update();
    }

    @Override
    public boolean delete(UserVO vo) {
        return userDAO.delete();
    }

    @Override
    public UserVO getUser(UserVO vo) {
        return userDAO.getUser();
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return userDAO.getUserList();
    }
}
