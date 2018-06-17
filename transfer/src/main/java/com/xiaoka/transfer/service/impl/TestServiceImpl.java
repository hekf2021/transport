package com.xiaoka.transfer.service.impl;

import com.xiaoka.transfer.dao.TestDao;
import com.xiaoka.transfer.model.User;
import com.xiaoka.transfer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    public User findUser(Integer id){
        return testDao.findUser(id);
    }
}
