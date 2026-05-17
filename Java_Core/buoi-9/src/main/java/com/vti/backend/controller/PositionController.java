package com.vti.backend.controller;

import com.vti.backend.service.IPositionService;
import com.vti.backend.service.impl.PositionServiceImpl;
import com.vti.entity.Position;

import java.util.List;

public class PositionController {
    IPositionService service = new PositionServiceImpl();
    public List<Position> findAll()
    {
        return service.findAll();
    }

    public boolean create(String name)
    {
        return service.create(name);
    }

    public boolean delete(int id)
    {
        return service.delete(id);
    }

    public boolean update(int id, String name)
    {
        return service.update(id, name);
    }
}
