package com.app.libraryproject.service;

import com.app.libraryproject.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAll();
    Payment newPayment(char paymentType, double amount);
    Payment newPayment(char paymentType, double amount, String description);
}
