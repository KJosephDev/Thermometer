package com.joseph.thermometer.thermometer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Service
public class ThermometerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static int i = 0;

    public double getThermometer(String testType, Boolean isInit) {
        ClassPathResource resource;

        if("WINTER".equals(testType)) {
            resource = new ClassPathResource("data/mockDataWinter.txt");
        } else if("SUMMER".equals(testType)) {
            resource = new ClassPathResource("data/mockDataSummer.txt");
        } else {
            resource = new ClassPathResource("data/mockDataIgnore.txt");
        }

        if(isInit) {
            i = 0;
        }



        try {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);
            int size = content.size();
            if(i > size-1) {
                i = 0;
            }
            String str = content.get(i);
            i++;
            return Double.parseDouble(str);
        } catch (IOException e) {
            logger.error("{}", e.getMessage(), e);
        }

        return 0;
    }


}
