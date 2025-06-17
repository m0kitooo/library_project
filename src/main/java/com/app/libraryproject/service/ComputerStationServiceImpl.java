package com.app.libraryproject.service;

import com.app.libraryproject.entity.ComputerStation;
import com.app.libraryproject.repository.ComputerStationRepositiry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComputerStationServiceImpl implements ComputerStationService{
    private final ComputerStationRepositiry computerStationRepositiry;

    @Override
    public List<ComputerStation> getComputerStations() {
        return computerStationRepositiry.findAll();
    }
}
