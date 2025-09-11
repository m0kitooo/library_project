package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookUsage.FinishOnSiteLoanRequest;
import com.app.libraryproject.dto.bookUsage.OnSiteLoanRequest;
import com.app.libraryproject.dto.bookUsage.OnSiteLoanResponse;

import java.util.List;

public interface OnSiteLoanService {
    List<OnSiteLoanResponse> addOnSiteLoan(OnSiteLoanRequest request);
    OnSiteLoanResponse finishOnSiteLoan(FinishOnSiteLoanRequest request);
    List<OnSiteLoanResponse> getLoansForMember(Long memberId);
    List<OnSiteLoanResponse> getLoans();
}
