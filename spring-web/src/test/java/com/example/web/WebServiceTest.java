package com.example.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
class WebServiceTest {

    @Autowired
    private WebService webService;

    @Test
    void addUser() {
        assertThatThrownBy(() -> webService.addUser(new User()))
                .isInstanceOf(ConstraintViolationException.class);

    }


    @TestConfiguration
    public static class TestConfig {
        @Bean
        public WebService webService() {
            return new WebService();
        }

        @Bean
        public MethodValidationPostProcessor bean() {
            return new MethodValidationPostProcessor();
        }
    }
}