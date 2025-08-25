package com.app.libraryproject.service;

import com.app.libraryproject.entity.ComputerStationLoan;
import com.app.libraryproject.entity.ComputerStationQueue;
import com.app.libraryproject.repository.ComputerStationLoanRepository;
import com.app.libraryproject.repository.ComputerStationQueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ComputerStationLoanServiceImpl implements ComputerStationLoanService{
    private ComputerStationLoanRepository computerStationLoanRepository;
    private ComputerStationQueueRepository computerStationQueueRepository;
    private ComputerStationQueueServiceImpl computerStationQueueServiceImpl;

    @Override
    public List<ComputerStationLoan> findAll() {
        return computerStationLoanRepository.findAll();
    }

    @Override
    public ComputerStationLoan startComputerStationLoan(Long computerStationQueueId) {
        if (computerStationQueueId == null) throw new NullPointerException("computerStationQueueId is null");
        ComputerStationQueue computerStationQueue = computerStationQueueRepository.getReferenceById(computerStationQueueId);

        return computerStationLoanRepository.save(ComputerStationLoan.builder()
                .computerStation(computerStationQueue.getComputerStation())
                .member(computerStationQueue.getMember())
                .start(LocalDateTime.now())
                .build());
    }

    @Override
    public ComputerStationLoan endComputerStationLoan(Long computerStationLoanId) {
        if (computerStationLoanId == null) throw new NullPointerException("computerStationLoanId is null");

        ComputerStationLoan cs = computerStationLoanRepository.getReferenceById(computerStationLoanId);
        cs.setEnd(LocalDateTime.now());
        computerStationQueueServiceImpl.removeMemberFromQueue(cs.getMember().getId());
        return computerStationLoanRepository.save(cs);
    }
}
