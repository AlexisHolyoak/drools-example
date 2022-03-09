package com.alexisholyoak.droolsexample.controller;

import com.alexisholyoak.droolsexample.model.Sale;
import com.alexisholyoak.droolsexample.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @PostMapping("/discount")
    private Sale getDiscountPercent(@RequestBody Sale sale) {
        this.discountService.applyDiscount(sale);
        return sale;
    }
}
