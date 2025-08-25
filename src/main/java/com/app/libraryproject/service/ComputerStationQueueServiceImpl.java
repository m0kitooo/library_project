package com.app.libraryproject.service;

import com.app.libraryproject.entity.ComputerStationQueue;
import com.app.libraryproject.repository.ComputerStationQueueRepository;
import com.app.libraryproject.repository.ComputerStationRepositiry;
import com.app.libraryproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ComputerStationQueueServiceImpl implements ComputerStationQueueService{
    private ComputerStationQueueRepository computerStationQueueRepository;
    private ComputerStationRepositiry computerStationRepositiry;
    private MemberRepository memberRepository;

    @Override
    public ComputerStationQueue addMemberToQueue(Long computerStationId, Long memberId, int exceptedUsingHours){
        if(computerStationId==null || memberId==null) throw new IllegalArgumentException();

        return computerStationQueueRepository.save(ComputerStationQueue.builder()
                        .computerStation(computerStationRepositiry.getReferenceById(computerStationId))
                        .member(memberRepository.getReferenceById(memberId))
                        .exceptedStart(getComputerStationQueue(computerStationId).getLast().getExceptedStart().plusHours(getComputerStationQueue(computerStationId).getLast().getExceptedUsingHours()))
                        .exceptedUsingHours(exceptedUsingHours)
                        .build());
    }

    @Override
    public void removeMemberFromQueue(Long memberId) {
        if (memberId == null) throw new IllegalArgumentException();

        computerStationQueueRepository.delete(computerStationQueueRepository.getComputerStationQueueByMemberId(memberId).orElseThrow());
    }


    @Override
    public List<ComputerStationQueue> getComputerStationQueue(Long computerStationId) {
        return computerStationQueueRepository.getComputerStationQueueByComputerStationIdOrderByExceptedEndDesc(computerStationId);
    }
}
