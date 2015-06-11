/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of registration-appcli.
 *
 * registration-appcli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * registration-appcli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with registration-appcli. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.registration.app;

import com.anrisoftware.registration.codegenerator.CodeGeneratorModule;
import com.anrisoftware.registration.data.RegistrationDataModule;
import com.anrisoftware.registration.keygenerator.KeyGeneratorModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the registration command line application.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class AppModule extends AbstractModule {

    /**
     * Returns the registration command line application,
     *
     * @return the {@link App}.
     */
    public static App getApp() {
        return SingletonHolder.app;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] { new AppModule(),
                new CodeGeneratorModule(), new RegistrationDataModule(),
                new KeyGeneratorModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final App app = injector.getInstance(App.class);

    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(AppWorker.class,
                GenerateKeyWorker.class).build(GenerateKeyWorkerFactory.class));
        install(new FactoryModuleBuilder().implement(AppWorker.class,
                GenerateCodeWorker.class)
                .build(GenerateCodeWorkerFactory.class));
    }

}
