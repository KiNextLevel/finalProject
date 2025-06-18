package com.example.common.biz.user.impl;

import com.example.common.biz.user.UserService;
import com.example.common.biz.user.UserVO;
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

    //사용자 정보 업데이트
    @Override
    public boolean update(UserVO vo) {
        return userDAO.update(vo);
    }

    @Override
    public boolean delete(UserVO vo) {
        return false;
    }

    @Override
    public UserVO getUser(UserVO vo) {
//        try{
//            int i = 1/0;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return userDAO.getUser(vo);
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return userDAO.getUserList(vo);
    }
}
