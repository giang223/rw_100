package com.vti;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.sessionFactory;
        Session session = sessionFactory.openSession();

        IPositionRepository repository = new PositionRepositoryImpl();
        List<Position> positions = repository.findAll();
        for (Position position : positions) {
            System.out.println(position);
        }
    }
}
