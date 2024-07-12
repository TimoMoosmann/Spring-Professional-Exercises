package com.timo.moosmann.tbr.mybank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timo.moosmann.tbr.mybank.service.TransactionService;

public class Application {
    public static TransactionService transactionService = new TransactionService();
    public static ObjectMapper objectMapper = new ObjectMapper();
}
