package com.xiaoka.transfer.dao.impl;

import com.xiaoka.transfer.dao.TestDao;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.xiaoka.transfer.model.User;

import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User findUser(Integer id) {
        StringBuffer sb = new StringBuffer();
        sb.append("select j from User j WHERE j.id = :id ");
        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("id",id);
        query.setMaxResults(1);
        List<User> userList = query.getResultList();
        if(userList!=null&&userList.size()>0){
            return userList.get(0);
        }else{
            return null;
        }
    }
}
