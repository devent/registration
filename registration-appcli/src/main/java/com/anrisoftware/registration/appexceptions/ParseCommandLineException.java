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
package com.anrisoftware.registration.appexceptions;

import java.util.Arrays;

/**
 * Exception for parsing the command line.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ParseCommandLineException extends AppException {

    public ParseCommandLineException(Object message, Throwable cause,
            String[] args) {
        super(message.toString(), cause);
        addContextValue("arguments", Arrays.toString(args));
    }

    public ParseCommandLineException(Object message, String[] args) {
        super(message.toString());
        addContextValue("arguments", Arrays.toString(args));
    }
}
