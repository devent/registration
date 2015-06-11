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

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.registration.appexceptions.AppException;
import com.anrisoftware.registration.appexceptions.ParseCommandLineException;

/**
 * Logging for {@link Starter}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
final class StarterLogger extends AbstractLogger {

    /**
     * Sets the context of the logger to {@link Starter}.
     */
    public StarterLogger() {
        super(Starter.class);
    }

    void errorParseCommandLine(ParseCommandLineException e) {
        log.error(e.getLocalizedMessage(), e);
    }

    void errorApp(AppException e) {
        log.error(e.getLocalizedMessage(), e);
    }
}
