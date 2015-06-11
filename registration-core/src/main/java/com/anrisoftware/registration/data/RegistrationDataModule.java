/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of registration-core.
 *
 * registration-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * registration-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with registration-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.registration.data;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the registration data.
 *
 * @see RegistrationFactory
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class RegistrationDataModule extends AbstractModule {

    /**
     * Returns the registration factory.
     *
     * @return the {@link RegistrationFactory}.
     */
    public static RegistrationFactory getRegistrationFactory() {
        return SingletonHolder.registrationFactory;
    }

    /**
     * Returns the registration key factory.
     *
     * @return the {@link RegisterKeyFactory}.
     */
    public static RegisterKeyFactory getRegisterKeyFactory() {
        return SingletonHolder.registerKeyFactory;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] { new RegistrationDataModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final RegistrationFactory registrationFactory = injector
                .getInstance(RegistrationFactory.class);

        public static final RegisterKeyFactory registerKeyFactory = injector
                .getInstance(RegisterKeyFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(DefaultRegistration.class,
                DefaultRegistration.class).build(RegistrationFactory.class));
        install(new FactoryModuleBuilder().implement(RegisterKey.class,
                RegisterKey.class).build(RegisterKeyFactory.class));
    }

}
