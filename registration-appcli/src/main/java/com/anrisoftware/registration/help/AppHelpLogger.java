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

import static com.anrisoftware.registration.help.AppHelpLogger._.error_load_resource;
import static com.anrisoftware.registration.help.AppHelpLogger._.error_print_help;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.registration.appexceptions.AppException;
import com.anrisoftware.resources.api.ResourcesException;

/**
 * Logging for {@link AppHelp}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
final class AppHelpLogger extends AbstractLogger {

    private static final String THE_OUTPUT = "output";

    private static final String THE_RESOURCE = "resource";

    enum _ {

        error_load_resource("Error load text resource"),

        error_print_help("Error print help text");

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
     * Sets the context of the logger to {@link AppHelp}.
     */
    public AppHelpLogger() {
        super(AppHelp.class);
    }

    AppException errorLoadResource(ResourcesException e, String name) {
        return new AppException(error_load_resource, e).add(THE_RESOURCE, name);
    }

    AppException errorPrintHelp(IOException e, Appendable output) {
        return new AppException(error_print_help, e).add(THE_OUTPUT, output);
    }
}
