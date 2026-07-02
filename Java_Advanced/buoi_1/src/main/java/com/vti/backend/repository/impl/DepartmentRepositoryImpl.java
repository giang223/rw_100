package com.vti.backend.repository.impl;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.entity.Department;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements IDepartmentRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Department";
            Query<Department> query = session.createQuery(hql, Department.class);
            departments = query.list();
        }
        finally {
            session.close();
        }

        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = new Department();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Department where id = :idParam";
            Query<Department> query = session.createQuery(hql, Department.class);
            query.setParameter("idParam", id);
            department = query.uniqueResult();
        }
        finally {
            session.close();
        }
        return department;
    }

    @Override
    public void create(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            Department department = new Department();
            department.setName(name);

            session.persist(department);

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
    public void update(String updateName, Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            Department department = session.find(Department.class, id);

            department.setName(updateName);
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
        try
        {
            Department department = session.find(Department.class, id);
            session.remove(department);
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
