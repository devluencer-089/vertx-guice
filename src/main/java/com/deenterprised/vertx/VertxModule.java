package com.deenterprised.vertx;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

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

    @Provides
    EventBus provideEventBus(Vertx vertx) {
        return vertx.eventBus();
    }

    @Provides
    io.vertx.rxjava.core.eventbus.EventBus provideRxEventBus(io.vertx.rxjava.core.Vertx vertx) {
        return vertx.eventBus();
    }
}
