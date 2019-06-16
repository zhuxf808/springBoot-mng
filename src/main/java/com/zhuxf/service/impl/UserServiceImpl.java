package com.zhuxf.service.impl;

import com.zhuxf.bean.pojo.User;
import com.zhuxf.dao.user.UserDao;
import com.zhuxf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getList() {
        Map<String,Object> paramsMap = new HashMap<>();
        return userDao.getList(paramsMap);
    }
}
