package com.deenterprised.vertx;

import com.google.inject.AbstractModule;
import io.vertx.core.Vertx;

class VertxModule extends AbstractModule {

    private final Vertx vertx;
    private final io.vertx.rxjava.core.Vertx rxVertx;

    VertxModule(Vertx vertx) {
        this.vertx = vertx;
        this.rxVertx = new io.vertx.rxjava.core.Vertx(vertx);
    }

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(vertx);
        bind(io.vertx.rxjava.core.Vertx.class).toInstance(rxVertx);
    }
}
