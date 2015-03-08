package com.deenterprised.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.service.ServiceVerticleFactory;

public class GuiceServiceVerticleFactory implements VerticleFactory {

    private final VerticleFactory delegate = new ServiceVerticleFactory();


    @Override
    public int order() {
        return delegate.order() - 1;
    }

    @Override
    public boolean requiresResolve() {
        return delegate.requiresResolve();
    }

    @Override
    public String resolve(String identifier, DeploymentOptions deploymentOptions, ClassLoader classLoader) throws Exception {
        return "java:" + delegate.resolve(identifier, deploymentOptions, classLoader);
    }

    @Override
    public void init(Vertx vertx) {
        delegate.init(vertx);
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public String prefix() {
        return delegate.prefix();
    }

    @Override
    public Verticle createVerticle(String verticleName, ClassLoader classLoader) throws Exception {
        return delegate.createVerticle(verticleName, classLoader);
    }
}
