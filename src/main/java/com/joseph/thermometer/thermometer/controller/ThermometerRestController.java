package com.joseph.thermometer.thermometer.controller;

import com.joseph.thermometer.thermometer.bean.Thermometer;
import com.joseph.thermometer.thermometer.service.ThermometerService;
import com.joseph.thermometer.thermometer.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Stack;

@RestController
public class ThermometerRestController {

    static Stack<Double> thermometerHistory = new Stack<>();
    static String currentTestType = "";
    static Boolean alreadyNotification = false;

    private ThermometerService thermometerService;

    public ThermometerRestController(ThermometerService thermometerService) {
        this.thermometerService = thermometerService;
    }
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/thermometer")
    public Thermometer getThermometer(@RequestParam("threshold") String thresholdStr,
                                      @RequestParam("testType") String testType,
                                      @RequestParam("ignore") String ignore,
                                      @RequestParam("onlyincreasing") String onlyIncreasing,
                                      @RequestParam("onlydecreasing") String onlyDecreasing,
                                      @RequestParam("iscelsius") String iscelsius
                                      ) {

        logger.info("<<<<<<<<<<<<<<<< ThermometerRestController.getThermometer");

        boolean isInit = true;
        if(!"".equals(currentTestType)) {
            if(!testType.equals(currentTestType)) { // The test type has changed. Global variable initialization
                currentTestType = testType;
                thermometerHistory.empty();
                alreadyNotification = false;
            } else {
                isInit = false;
            }
        }

        currentTestType = testType;


        double threshold = Double.parseDouble(thresholdStr);
        if(!"Y".equals(iscelsius)) { // This is fahrenheit
            threshold = CommonUtil.convertCelsiusToFahrenheit(threshold);
        }


        double celsius = thermometerService.getThermometer(testType, isInit);
        double fahrenheit = CommonUtil.convertCelsiusToFahrenheit(celsius);

        boolean notification = false;
        if(thermometerHistory.size() == 0) {
            if(threshold == celsius) {
                notification = true;
            }
        } else {
            double previousCelsius = thermometerHistory.peek();

            if("Y".equals(ignore)) {
                if(threshold == celsius || Math.abs(threshold - celsius) < 0.5) {
                    if(alreadyNotification) { // exsiting
                        if(Math.abs(previousCelsius - celsius) > 0.5) {
                            notification = true;
                        }
                    } else { // not existing
                        notification = true;
                        alreadyNotification = true;
                    }
                }
            } else if("Y".equals(onlyIncreasing)) { // It works only when the temperature is lower than the threshold.
                if(previousCelsius < threshold) {
                    if(threshold == celsius || Math.abs(threshold - celsius) < 0.5) {
                        notification = true;
                    }
                }
            } else if("Y".equals(onlyDecreasing)) { // It works only when the temperature is higher than the threshold.
                if(previousCelsius > threshold) {
                    if(threshold == celsius || Math.abs(threshold - celsius) < 0.5) {
                        notification = true;
                    }
                }
            } else {
                if(threshold == celsius || Math.abs(threshold - celsius) < 0.5) {
                    notification = true;

                }
            }
        }

        Thermometer thermometer = new Thermometer(celsius, fahrenheit);
        thermometerHistory.push(celsius);
        thermometer.setNotification(notification);

        return thermometer;
    }

}
