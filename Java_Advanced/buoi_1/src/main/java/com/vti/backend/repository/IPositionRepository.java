package com.vti.backend.repository;

import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.enums.PositionName;

import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
    Position findById(Integer id);
    void create(PositionName name);
    void update(PositionName updateName, Integer id);
}
