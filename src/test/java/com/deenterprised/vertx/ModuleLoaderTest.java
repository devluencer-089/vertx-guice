package com.deenterprised.vertx;

import com.deenterprised.vertx.spi.BootstrapModuleProvider;
import com.deenterprised.vertx.spi.HighPriorityBoostrapModuleProvider;
import com.google.inject.Module;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.ServiceLoader;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ModuleLoaderTest {


    private final ServiceLoader<BootstrapModuleProvider> serviceLoader = ServiceLoader.load(BootstrapModuleProvider.class, ModuleLoaderTest.class.getClassLoader());
    private final ModuleLoader sut = new ModuleLoader(serviceLoader);

    @Test
    public void thatAnExceptionIsThrow_ifNoModulesAreRegistered() throws Exception {
        sut.load();
        overrideProviders(new LinkedHashMap<>());

        catchException(sut).load();
        assertThat(caughtException(), is(instanceOf(IllegalStateException.class)));
    }

    @Test
    public void thatProviderWithHigherPriorityIsPicked() throws Exception {
        Module module = sut.load();
        assertThat(module, is(instanceOf(HighPriorityBoostrapModuleProvider.Module.class)));
    }

    private void overrideProviders(LinkedHashMap<Object, Object> providers) throws NoSuchFieldException, IllegalAccessException {
        Field providersField = serviceLoader.getClass().getDeclaredField("providers");
        providersField.setAccessible(true);
        providersField.set(serviceLoader, providers);
    }

}