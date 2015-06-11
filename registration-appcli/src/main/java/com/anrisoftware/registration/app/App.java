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

import javax.inject.Inject;

import org.kohsuke.args4j.CmdLineException;

/**
 * Application to generate the registration key and code.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
final class App {

    @Inject
    private AppLogger log;

    @Inject
    private AppCommandLineParser commandLine;

    private Appendable output;

    App() {
        this.output = System.out;
    }

    /**
     * Starts the application.
     *
     * @param args
     *            the command line arguments.
     *
     * @throws AppException
     *             if there was an error in the application.
     */
    public void start(String[] args) throws AppException {
        parseCommandLine(args);
        AppWorkerFactory workerFactory = commandLine.getWorkerFactory();
        workerFactory.create(commandLine, output);
    }

    public void setOutput(Appendable output) {
        this.output = output;
    }

    private void parseCommandLine(String[] args)
            throws ParseCommandLineException {
        try {
            commandLine.parse(args);
        } catch (CmdLineException e) {
            throw log.errorParseCommandLine(e, args);
        }
    }
}
