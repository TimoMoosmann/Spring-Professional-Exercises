package com.timo.moosmann.tbr.mybank.context;

import com.timo.moosmann.tbr.mybank.ApplicationLauncher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class ApplicationConfiguration {

}
