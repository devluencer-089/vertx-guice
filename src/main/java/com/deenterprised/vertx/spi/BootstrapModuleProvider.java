package com.deenterprised.vertx.spi;

import com.google.inject.Module;

public interface BootstrapModuleProvider {


    Module get();

    int priority();
}
