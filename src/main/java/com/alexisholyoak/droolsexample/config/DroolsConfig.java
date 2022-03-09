package com.alexisholyoak.droolsexample.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


import java.io.IOException;

@Configuration
public class DroolsConfig {
    private static final String RULES_PATH = "rules/";
    @Bean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }
    private KieServices kieServices() {
        return KieServices.Factory.get();
    }
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
    @Bean
    public KieContainer kieContainer(KieFileSystem kieFileSystem) throws IOException {
        final KieRepository kieRepository = kieRepository();
        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }
    private KieRepository kieRepository() {
        final KieRepository kieRepository = kieServices().getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        return kieRepository;
    }

}
