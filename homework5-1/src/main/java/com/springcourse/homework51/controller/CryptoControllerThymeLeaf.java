package com.springcourse.homework51.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CryptoControllerThymeLeaf {

    private CryptoControllerVaadin cryptoControllerVaadin;

    @Autowired
    public CryptoControllerThymeLeaf(CryptoControllerVaadin cryptoControllerVaadin) {
        this.cryptoControllerVaadin = cryptoControllerVaadin;
    }

    @GetMapping("/crypto")
    public String getCryptoCurrency(Model model){
        model.addAttribute("cryptoList" , cryptoControllerVaadin.getCryptoCurrencies());
        return "crypto";
    }
}
