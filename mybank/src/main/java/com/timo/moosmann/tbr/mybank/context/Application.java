package com.timo.moosmann.tbr.mybank.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.timo.moosmann.tbr.mybank.service.TransactionService;

public class Application {
    public static TransactionService transactionService = new TransactionService();
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
