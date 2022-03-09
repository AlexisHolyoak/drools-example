package com.alexisholyoak.droolsexample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sale {
    private int quantity;
    private String item;
    private int discount;
}
