/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of registration-appcli.
 *
 * registration-appcli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * registration-appcli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * registration-appcli. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.registration.app;

import javax.inject.Inject;

import com.anrisoftware.registration.appexceptions.AppException;
import com.anrisoftware.registration.appexceptions.ParseCommandLineException;
import com.google.inject.Injector;

/**
 * Starts the application to generate the registration key and code.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public final class Starter {

    @Inject
    private StarterLogger log;

    /**
     * Starts the application to generate the registration key and code.
     *
     * @param args
     *            the command line arguments.
     */
    public static void main(String[] args) {
        Injector injector = AppModule.getInjector();
        Starter starter = injector.getInstance(Starter.class);
        starter.doStart(args);
    }

    private void doStart(String[] args) {
        App app = AppModule.getApp();
        try {
            app.start(args);
        } catch (ParseCommandLineException e) {
            log.errorParseCommandLine(e);
            printHelp(app);
        } catch (AppException e) {
            log.errorApp(e);
        }
    }

    private void printHelp(App app) {
        try {
            app.printHelp();
        } catch (AppException e1) {
            log.errorApp(e1);
        }
    }
}
