package com.app.libraryproject.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PrintingColor {
    WHITE_AND_BLACK (new BigDecimal("0.50")),
    COLOR (new BigDecimal("0.80"));

    private BigDecimal price;

    PrintingColor(BigDecimal price) {
        this.price = price;
    }
}
