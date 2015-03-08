package com.deenterprised.vertx.spi;

import com.google.inject.AbstractModule;

public class SomeBoostrapModuleProvider implements BootstrapModuleProvider {
    @Override
    public Module get() {
        return new Module();
    }

    @Override
    public int priority() {
        return 0;
    }

    public final class Module extends AbstractModule {

        @Override
        protected void configure() {

        }
    }
}
