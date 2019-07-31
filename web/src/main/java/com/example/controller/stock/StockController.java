package com.example.controller.stock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Stock")
public class StockController {

    @GetMapping("/list")
    public  String Stock(){
        return "/Stock/list";
    }
}
