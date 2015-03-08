package com.deenterprised.vertx;

import com.deenterprised.vertx.spi.BootstrapModuleProvider;
import com.google.inject.Module;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparingInt;

public class ModuleLoader {
    private final ServiceLoader<BootstrapModuleProvider> serviceLoader;

    public ModuleLoader(ServiceLoader<BootstrapModuleProvider> serviceLoader) {
        this.serviceLoader = serviceLoader;
    }

    Module load() {
        Iterator<BootstrapModuleProvider> iterator = serviceLoader.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalStateException(String.format("No bootstrap module provided. Add a %s through the java ServiceLoader mechanism", BootstrapModuleProvider.class.getName()));
        }

        Spliterator<BootstrapModuleProvider> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.NONNULL);
        return  StreamSupport.stream(spliterator, false)
                .sorted(comparingInt(BootstrapModuleProvider::priority).reversed())
                .map(provider -> provider.get())
                .findFirst()
                .get();

    }
}
