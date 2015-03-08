package com.deenterprised.vertx.spi;

import com.google.inject.AbstractModule;

public class HighPriorityBoostrapModuleProvider implements BootstrapModuleProvider {
    @Override
    public Module get() {
        return new Module();
    }

    @Override
    public int priority() {
        return 100;
    }

    public static class Module extends AbstractModule {

        @Override
        protected void configure() {

        }
    }


}
