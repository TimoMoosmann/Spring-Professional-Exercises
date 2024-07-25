package com.timo.moosmann.tbr.mybank.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebsiteController {

    private final String bankSlogan;

    public WebsiteController(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    @ModelAttribute("bankSlogan")
    public String populateBankSlogan() {
        return bankSlogan;
    }

    @GetMapping("/")
    public String homepage() {
        return "index.html";
    }
}
