package com.joseph.thermometer.thermometer.controller;

import com.joseph.thermometer.thermometer.bean.Thermometer;
import com.joseph.thermometer.thermometer.service.ThermometerService;
import com.joseph.thermometer.thermometer.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThermometerController {



    @GetMapping("/")
    public String thermometer(Model model) {


        return "thermometer";
    }


}
