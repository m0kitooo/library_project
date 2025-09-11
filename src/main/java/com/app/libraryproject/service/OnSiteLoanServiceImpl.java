package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookUsage.FinishOnSiteLoanRequest;
import com.app.libraryproject.dto.bookUsage.OnSiteLoanRequest;
import com.app.libraryproject.dto.bookUsage.OnSiteLoanResponse;
import com.app.libraryproject.entity.OnSiteLoan;
import com.app.libraryproject.model.LoanStatus;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.LibraryCardRepository;
import com.app.libraryproject.repository.MemberRepository;
import com.app.libraryproject.repository.OnSiteLoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OnSiteLoanServiceImpl implements OnSiteLoanService {
    private final OnSiteLoanRepository loanRepository;
    private final LibraryCardRepository libraryCardRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public List<OnSiteLoanResponse> addOnSiteLoan(OnSiteLoanRequest request) {
        var member = memberRepository.findByLibraryCards_Id(request.libraryCardId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        var books = bookRepository.findAllById(request.booksId());

        if (books.size() != request.booksId().size()) {
            throw new RuntimeException("Some books not found");
        }

        OnSiteLoan loan = new OnSiteLoan();
        loan.setMember(member);
        loan.setBooks(books);
        loan.setStartTime(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);

        loanRepository.save(loan);

        return List.of(OnSiteLoanResponse.from(loan));
    }

    @Override
    @Transactional
    public OnSiteLoanResponse finishOnSiteLoan(FinishOnSiteLoanRequest request) {
        var loan = loanRepository.findById(request.loanId())
                .orElseThrow(() -> new RuntimeException("On-site loan not found"));

        if (loan.getStatus() == LoanStatus.FINISHED) {
            throw new RuntimeException("Loan already finished");
        }

        loan.setStatus(LoanStatus.FINISHED);
        loan.setEndTime(LocalDateTime.now());

        loanRepository.save(loan);

        return OnSiteLoanResponse.from(loan);
    }

    @Override
    @Transactional
    public List<OnSiteLoanResponse> getLoansForMember(Long memberId) {
        var member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return loanRepository.findByMember(member)
                .stream()
                .map(OnSiteLoanResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public List<OnSiteLoanResponse> getLoans(){
        return loanRepository.findAll()
                .stream()
                .map(OnSiteLoanResponse::from)
                .toList();
    }
}
