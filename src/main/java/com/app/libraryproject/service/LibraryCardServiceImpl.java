package com.app.libraryproject.service;

import com.app.libraryproject.dto.LibraryCardResponse;
import com.app.libraryproject.dto.CreateMemberRequest;
import com.app.libraryproject.repository.LibraryCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryCardServiceImpl implements LibraryCardService {
    private final LibraryCardRepository libraryCardRepository;

    @Override
    public LibraryCardResponse register(CreateMemberRequest createMemberRequest) {
//        return libraryCardRepository.save(new LibraryCard())
        return null;
    }
}
