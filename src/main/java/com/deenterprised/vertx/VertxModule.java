package com.deenterprised.vertx;

import com.google.inject.AbstractModule;
import io.vertx.core.Vertx;

class VertxModule extends AbstractModule {

    private final Vertx vertx;

    VertxModule(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(vertx);
    }
}
