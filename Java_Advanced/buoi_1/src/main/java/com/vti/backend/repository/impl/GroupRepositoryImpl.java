package com.vti.backend.repository.impl;

import com.vti.backend.repository.IGroupRepository;
import com.vti.entity.Account;
import com.vti.entity.Group;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.vti.utils.HibernateUtils.sessionFactory;

public class GroupRepositoryImpl implements IGroupRepository {

    @Override
    public List<Group> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "FROM Group";
        Query<Group> query = session.createQuery(hql, Group.class);
        List<Group> groups = query.list();
        return groups;
    }

    @Override
    public Group findById(Integer id) {
        Session session = sessionFactory.openSession();

        Group group = session.find(Group.class, id);
        session.close();
        return group;
    }

    @Override
    public void create(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            session.persist(group);
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
    public void update(Integer id, String newGroupName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Group group = session.find(Group.class, id);
            group.setGroupName(newGroupName);
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
            Group group = session.find(Group.class, id);
            session.remove(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
