package com.deenterprised.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.service.ServiceVerticleFactory;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

public class GuiceServiceVerticleFactoryTest {

    private final GuiceServiceVerticleFactory sut = new GuiceServiceVerticleFactory();


    @Test
    public void thatOrderIsLessThanOnServiceVerticleFactory() {
        ServiceVerticleFactory factory = new ServiceVerticleFactory();
        assertThat(sut.order(), is(lessThan(factory.order())));
    }


    @Test
    public void thatJavaPrefixIsPrepended() throws Exception {
        String identifier = sut.resolve("service:com.deenterprised:vertx-test-verticle:1.0.0", new DeploymentOptions(), Thread.currentThread().getContextClassLoader());
        assertThat(identifier, startsWith("java:"));
    }
}