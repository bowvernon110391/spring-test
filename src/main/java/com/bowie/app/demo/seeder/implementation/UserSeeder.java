package com.bowie.app.demo.seeder.implementation;

import com.bowie.app.demo.model.User;
import com.bowie.app.demo.repo.UserRepository;
import com.bowie.app.demo.seeder.SeederAbstract;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UserSeeder extends SeederAbstract {
    @Autowired
    private UserRepository userRepository;

    public UserSeeder(Faker faker) {
        super(faker);
        log.info("UserSeeder instantiated.");
    }

    @Override
    public void seed() {
        int nUser = new Random().ints(10,30).findFirst().getAsInt();
        log.info("Generating {} users...", nUser);

        for (int i=0; i<nUser; ++i) {
            User user = new User(
                    null,
                    faker.name().username(),
                    faker.bothify("##??#?#?##???#?##"),
                    faker.name().fullName(),
                    null,
                    null
            );

            userRepository.save(user);
        }

        log.info("Users seeded", nUser);
    }
}
