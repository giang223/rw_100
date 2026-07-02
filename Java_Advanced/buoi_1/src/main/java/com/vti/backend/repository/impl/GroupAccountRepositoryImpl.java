package com.vti.backend.repository.impl;

import com.vti.backend.repository.IGroupAccountRepository;
import com.vti.entity.Group;
import com.vti.entity.GroupAccount;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.vti.utils.HibernateUtils.sessionFactory;

public class GroupAccountRepositoryImpl implements IGroupAccountRepository {
    @Override
    public List<GroupAccount> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "FROM GroupAccount";
        Query<GroupAccount> query = session.createQuery(hql, GroupAccount.class);
        List<GroupAccount> groupAccounts = query.list();
        return groupAccounts;
    }

    @Override
    public GroupAccount findById(Integer id) {
        Session session = sessionFactory.openSession();

        GroupAccount groupAccount = session.find(GroupAccount.class, id);
        session.close();
        return groupAccount;
    }

    @Override
    public void create(GroupAccount groupAccount) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            session.persist(groupAccount);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            GroupAccount groupAccount = session.find(GroupAccount.class, id);
            session.remove(groupAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
