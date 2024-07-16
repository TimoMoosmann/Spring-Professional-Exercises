package com.timo.moosmann.tbr.mybank.context;

import com.timo.moosmann.tbr.mybank.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    TransactionService transactionService() {
        return new TransactionService();
    }
}
