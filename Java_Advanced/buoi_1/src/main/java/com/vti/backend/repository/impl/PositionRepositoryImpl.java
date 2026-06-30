package com.vti.backend.repository.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.entity.Position;
import com.vti.enums.PositionName;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;
    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Position";
            Query<Position> query = session.createQuery(hql, Position.class);
            positions = query.list();
        }
        finally {
            session.close();
        }
        return positions;
    }

    @Override
    public Position findById(Integer id) {
        Position position = new Position();
        Session session = sessionFactory.openSession();

        try{
            String hql = "From Position where id = :idParam";
            Query<Position> query = session.createQuery(hql, Position.class);
            query.setParameter("idParam", id);
            position = query.uniqueResult();
        }finally {
            session.close();
        }

        return position;
    }

    @Override
    public void create(PositionName name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Position position = new Position();
            position.setName(name);

            session.persist(position);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(PositionName updateName, Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            Position position = session.find(Position.class, id);

            position.setName(updateName);
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
}
