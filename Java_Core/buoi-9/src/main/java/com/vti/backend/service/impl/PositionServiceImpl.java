package com.vti.backend.service.impl;

import com.vti.backend.repository.IPositionRepository;
import com.vti.backend.repository.impl.PositionRepositoryImpl;
import com.vti.backend.service.IPositionService;
import com.vti.entity.Position;

import java.util.List;

public class PositionServiceImpl implements IPositionService {
    private IPositionRepository repository = new PositionRepositoryImpl();
    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean create(String name) {
        return repository.create(name);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(int id, String name) {
        return repository.update(id, name);
    }
}
