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

import org.apache.commons.lang3.exception.ContextedException;
import org.apache.commons.lang3.exception.ExceptionContext;

/**
 * Registration application exception.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AppException extends ContextedException {

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause,
            ExceptionContext context) {
        super(message, cause, context);
    }

    public AppException(Object message, Throwable cause,
            ExceptionContext context) {
        super(message.toString(), cause, context);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Object message, Throwable cause) {
        super(message.toString(), cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Object message) {
        super(message.toString());
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException add(String label, Object value) {
        super.addContextValue(label, value);
        return this;
    }
}
