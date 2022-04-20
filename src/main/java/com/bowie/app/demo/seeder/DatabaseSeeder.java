package com.bowie.app.demo.seeder;

import com.bowie.app.demo.seeder.implementation.UserSeeder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * This is a main class that instantiates other seeders
 * this must only run in development mode, and after
 * the application finish its startup
 */
@Component
@Profile("development")
@Slf4j
public class DatabaseSeeder {

    @Autowired
    private UserSeeder userSeeder;

    @EventListener(ApplicationReadyEvent.class)
    public void seed() {
        // just a log would suffice though
        log.info("Database seeding started.");
//        System.out.println("Running database seed....");

        // TODO: put other seeders here in their specified orders
        userSeeder.seed();

        log.info("Database seeding completed.");
    }
}
