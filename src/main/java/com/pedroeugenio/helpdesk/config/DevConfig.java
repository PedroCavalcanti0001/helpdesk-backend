package com.pedroeugenio.helpdesk.config;

import com.pedroeugenio.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;


    @Bean
    public boolean instanciaDB() {
        if (value.equals("create")) {
            this.dbService.instanciaDB();
            return true;
        }
        return false;
    }
}
