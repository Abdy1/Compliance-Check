package com.cbo.riskmanagement;

import com.cbo.riskmanagement.service.OfacService;
import com.cbo.riskmanagement.service.UNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class RiskmanagementApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    UNService unService;

    @Autowired
    OfacService ofacService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RiskmanagementApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RiskmanagementApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .maxAge(86400);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        scheduleDailyTask(() -> {
            try {
                unService.checkUNUpdate();
            } catch (IOException | ParserConfigurationException | SAXException e) {
                // Handle the exceptions here
                e.printStackTrace();
            }
        });

        scheduleDailyTask(() -> {
            try {
                ofacService.checkOfacUpdate();
            } catch (IOException | ParserConfigurationException | SAXException e) {
                // Handle the exceptions here
                e.printStackTrace();
            }
        });
    }

    private void scheduleDailyTask(Runnable task) {
        Timer timer = new Timer();
        Calendar now = Calendar.getInstance();
        Calendar targetTime = (Calendar) now.clone();

        targetTime.set(Calendar.HOUR_OF_DAY, 2);
        targetTime.set(Calendar.MINUTE, 30);
        targetTime.set(Calendar.SECOND, 0);

        if (targetTime.before(now)) {
            targetTime.add(Calendar.DATE, 1);
        }

        long delay = targetTime.getTimeInMillis() - now.getTimeInMillis();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delay, 24 * 60 * 60 * 1000);
    }

}