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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Loads the registration.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class RegistrationLoader {

    /**
     * Loads the registration from the specified file.
     *
     * @param file
     *            the {@link File}.
     *
     * @return the loaded {@link Registration}.
     *
     * @throws IOException
     *             if there was an error reading the file.
     *
     * @throws ClassNotFoundException
     *             if the {@link Registration} class was not found.
     */
    public Registration load(File file) throws IOException,
            ClassNotFoundException {
        FileInputStream fstream = new FileInputStream(file);
        return load(fstream);
    }

    /**
     * Loads the registration from the specified stream.
     *
     * @param input
     *            the {@link InputStream}.
     *
     * @return the loaded {@link Registration}.
     *
     * @throws IOException
     *             if there was an error reading the file.
     *
     * @throws ClassNotFoundException
     *             if the {@link Registration} class was not found.
     *
     * @since 1.1
     */
    public Registration load(InputStream input) throws IOException,
            ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(input);
        Registration registration = (Registration) stream.readObject();
        stream.close();
        return registration;
    }
}
