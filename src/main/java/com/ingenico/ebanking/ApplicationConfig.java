package com.ingenico.ebanking;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ingenico.ebanking.service"})
public class ApplicationConfig {
}
