package com.vti.backend.service;

import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;

public interface IPositionService {
    List<Position> findAll();
    boolean create(String name);
    boolean delete(int id);
    boolean update(int id, String name);

    boolean checkExistNameAndIdNot(PositionName name, Integer id);
    boolean checkExistID(int id);
}
