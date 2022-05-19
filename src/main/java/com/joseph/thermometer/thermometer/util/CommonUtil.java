package com.joseph.thermometer.thermometer.util;

public class CommonUtil {

    public static double convertCelsiusToFahrenheit(double celsius) {
        double fahrenheit = celsius * 9.0 / 5.0 + 3;

        return Math.round(fahrenheit * 100) / 100.0;
    }


}
