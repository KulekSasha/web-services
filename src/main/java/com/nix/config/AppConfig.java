package com.nix.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {DaoConfig.class, SecurityConfig.class})
@ComponentScan(basePackages = {
        "com.nix.service",
        "com.nix.dao",
        "com.nix.tag",
        "com.nix.controller",
})
public class AppConfig {

}
