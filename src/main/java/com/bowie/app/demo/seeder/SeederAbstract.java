package com.bowie.app.demo.seeder;

import com.github.javafaker.Faker;

public abstract class SeederAbstract {
    protected final Faker faker;

    public SeederAbstract(Faker faker) {
        this.faker = faker;
    }

    public abstract void seed();
}
