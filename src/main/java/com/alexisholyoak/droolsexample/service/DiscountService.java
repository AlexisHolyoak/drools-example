package com.alexisholyoak.droolsexample.service;

import com.alexisholyoak.droolsexample.model.Sale;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final KieContainer kieContainer;


    public DiscountService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
    public void applyDiscount(Sale sale){
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(sale);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
