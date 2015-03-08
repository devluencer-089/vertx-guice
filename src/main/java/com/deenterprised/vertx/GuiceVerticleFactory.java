package com.deenterprised.vertx;

import com.deenterprised.vertx.spi.BootstrapModuleProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.impl.verticle.CompilingClassLoader;
import io.vertx.core.spi.VerticleFactory;

import java.util.ServiceLoader;

public class GuiceVerticleFactory implements VerticleFactory {

    private Injector injector;

    private final ServiceLoader<BootstrapModuleProvider> moduleServiceLoader = ServiceLoader.load(BootstrapModuleProvider.class);

    @Override
    public int order() {
        return -1; //supersede default java factory
    }

    @Override
    public void init(Vertx vertx) {
        Module bootstrapModule = new ModuleLoader(moduleServiceLoader).load();
        VertxModule vertxModule = new VertxModule(vertx);
        injector = Guice.createInjector(vertxModule, bootstrapModule);
    }

    @Override
    public String prefix() {
        return "java";
    }

    @Override
    public Verticle createVerticle(String verticleName, ClassLoader classLoader) throws Exception {
        Class<?> clazz = loadClass(verticleName, classLoader);
        Verticle verticle = (Verticle) injector.getInstance(clazz);
        return verticle;
    }

    private Class<?> loadClass(String verticleName, ClassLoader classLoader) throws ClassNotFoundException {
        verticleName = VerticleFactory.removePrefix(verticleName);
        Class<?> clazz;
        if (verticleName.endsWith(".java")) {
            CompilingClassLoader compilingLoader = new CompilingClassLoader(classLoader, verticleName);
            String className = compilingLoader.resolveMainClassName();
            clazz = compilingLoader.loadClass(className);
        } else {
            clazz = classLoader.loadClass(verticleName);
        }
        return clazz;
    }

}
