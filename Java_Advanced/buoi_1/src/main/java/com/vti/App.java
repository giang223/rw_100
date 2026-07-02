package com.vti;

import com.vti.backend.repository.IDepartmentRepository;
import com.vti.backend.repository.IGroupRepository;
import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.DepartmentRepositoryImpl;
import com.vti.backend.repository.impl.GroupRepositoryImpl;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.entity.Department;
import com.vti.entity.Group;
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

        IGroupRepository repository = new GroupRepositoryImpl();
        Group group = new Group();
        group.setGroupName("A");
        repository.create(group);
        System.out.println(repository.findById(1));
    }
}
