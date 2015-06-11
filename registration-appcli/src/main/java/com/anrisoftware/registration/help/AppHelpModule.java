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
package com.anrisoftware.registration.help;

import com.anrisoftware.registration.workers.AppWorker;
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule;
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule;
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule;
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule;
import com.anrisoftware.resources.texts.texts.TextsResourcesModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Install the application help.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public final class AppHelpModule extends AbstractModule {

    /**
     * Application resources module.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 1.0
     */
    public final class ResourcesModule extends AbstractModule {

        @Override
        protected void configure() {
            install(new TextsResourcesModule());
            install(new TextsDefaultMapsModule());
            install(new TextsResourcesCharsetModule());
            install(new BinariesResourcesModule());
            install(new BinariesDefaultMapsModule());
        }

    }

    @Override
    protected void configure() {
        install(new ResourcesModule());
        install(new FactoryModuleBuilder().implement(AppWorker.class,
                AppHelp.class).build(AppHelpFactory.class));
    }

}
