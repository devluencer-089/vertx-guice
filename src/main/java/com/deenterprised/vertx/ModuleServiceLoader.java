package com.deenterprised.vertx;

import com.deenterprised.vertx.spi.BootstrapModuleProvider;
import com.google.inject.Module;

import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

public class ModuleServiceLoader {

    static List<Module> load() {
        ServiceLoader<BootstrapModuleProvider> serviceLoader = ServiceLoader.load(BootstrapModuleProvider.class);
        List<Module> modules = new LinkedList<>();
        serviceLoader.iterator().forEachRemaining(provider -> modules.add(provider.get()));
        return modules;
    }
}
