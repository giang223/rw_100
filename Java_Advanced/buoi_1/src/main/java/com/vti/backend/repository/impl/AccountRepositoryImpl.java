package com.vti.backend.repository.impl;

import com.vti.backend.repository.IAccountRepository;
import com.vti.entity.Account;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.vti.utils.HibernateUtils.sessionFactory;

public class AccountRepositoryImpl implements IAccountRepository {
    @Override
    public List<Account> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "FROM Account";
        Query<Account> query = session.createQuery(hql, Account.class);
        List<Account> accounts = query.list();
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Session session = sessionFactory.openSession();

        Account account = session.find(Account.class, id);
        session.close();
        return account;
    }

    @Override
    public void create(Account account) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Integer id, String newFullName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Account account = session.find(Account.class, id);
            account.setFullName(newFullName);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Account account = session.find(Account.class, id);
            session.remove(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
