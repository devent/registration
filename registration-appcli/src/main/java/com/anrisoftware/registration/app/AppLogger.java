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

import static com.anrisoftware.registration.app.AppLogger._.error_parse_command_line;
import static com.anrisoftware.registration.app.AppLogger._.error_print_help;
import static com.anrisoftware.registration.app.AppLogger._.error_worker;
import static com.anrisoftware.registration.app.AppLogger._.nothing_specified;

import org.kohsuke.args4j.CmdLineException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.registration.appexceptions.AppException;
import com.anrisoftware.registration.appexceptions.ParseCommandLineException;
import com.anrisoftware.registration.workers.AppWorker;

/**
 * Logging for {@link App}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AppLogger extends AbstractLogger {

    private static final String THE_WORKER = "worker";

    enum _ {

        error_parse_command_line("Error parse command line"),

        nothing_specified("Nothing specified"),

        error_worker("Error application"),

        error_print_help("Error print help");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Sets the context of the logger to {@link App}.
     */
    public AppLogger() {
        super(App.class);
    }

    ParseCommandLineException errorParseCommandLine(CmdLineException e,
            String[] args) {
        return new ParseCommandLineException(error_parse_command_line, e, args);
    }

    ParseCommandLineException nothingSpecified(String[] args) {
        return new ParseCommandLineException(nothing_specified, args);
    }

    AppException errorAppWorker(Exception e, AppWorker worker) {
        return new AppException(error_worker, e).add(THE_WORKER, worker);
    }

    AppException errorPrintHelp(Exception e) {
        return new AppException(error_print_help, e);
    }
}
