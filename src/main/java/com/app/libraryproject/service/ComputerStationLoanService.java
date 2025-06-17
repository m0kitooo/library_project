package com.app.libraryproject.service;

import com.app.libraryproject.entity.ComputerStationLoan;

import java.util.List;

public interface ComputerStationLoanService {
    List<ComputerStationLoan> findAll();
    ComputerStationLoan startComputerStationLoan(Long computerStationQueueId);
    ComputerStationLoan endComputerStationLoan(Long computerStationLoanId);
}
