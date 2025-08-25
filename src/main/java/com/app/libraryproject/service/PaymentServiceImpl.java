package com.app.libraryproject.service;

import com.app.libraryproject.entity.Payment;
import com.app.libraryproject.model.PaymentType;
import com.app.libraryproject.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment newPayment(char paymentType, double amount) {
        PaymentType type = paymentType == 'i' ? PaymentType.DEPOSIT : PaymentType.WITHDRAWL;

        return paymentRepository.save(Payment.builder()
                .paymentType(type)
                .PaymentDate(LocalDateTime.now())
                .amount(BigDecimal.valueOf(amount))
                .build());
    }

    @Override
    public Payment newPayment(char paymentType, double amount, String description) {
        PaymentType type = paymentType == 'i' ? PaymentType.DEPOSIT : PaymentType.WITHDRAWL;

        return paymentRepository.save(Payment.builder()
                .paymentType(type)
                .PaymentDate(LocalDateTime.now())
                .amount(BigDecimal.valueOf(amount))
                .description(description)
                .build());
    }
}
