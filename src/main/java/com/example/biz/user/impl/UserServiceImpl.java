package com.example.biz.user.impl;

import com.example.biz.user.UserService;
import com.example.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean insert(UserVO vo) {
        return userDAO.insert(vo);
    }

    @Override
    public boolean update(UserVO vo) {
        return userDAO.update(vo);
    }

    @Override
    public boolean delete(UserVO vo) {
        return userDAO.delete(vo);
    }

    @Override
    public UserVO getUser(UserVO vo) {
        return userDAO.getUser(vo);
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return userDAO.getUserList(vo);
    }
}
