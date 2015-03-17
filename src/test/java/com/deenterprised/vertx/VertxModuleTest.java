package com.deenterprised.vertx;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class VertxModuleTest {

    private final Vertx vertx = Vertx.vertx();
    private final Injector injector = Guice.createInjector(new VertxModule(vertx));

    @Inject
    private Vertx injectedVertxInstance;
    @Inject
    private io.vertx.rxjava.core.Vertx injectedRxVertxInstance;
    @Inject
    private EventBus injectedEventBus;
    @Inject
    private io.vertx.rxjava.core.eventbus.EventBus injectedRxEventBus;


    @Before
    public void init() {
        injector.injectMembers(this);
    }

    @Test
    public void thatAllMembersAreInjected() {
        assertThat(injectedVertxInstance, is(notNullValue()));
        assertThat(injectedRxVertxInstance, is(notNullValue()));
        assertThat(injectedEventBus, is(notNullValue()));
        assertThat(injectedRxEventBus, is(notNullValue()));
    }

}