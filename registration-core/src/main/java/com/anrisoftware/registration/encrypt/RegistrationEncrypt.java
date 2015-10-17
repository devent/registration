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
package com.anrisoftware.registration.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import org.jasypt.util.binary.BasicBinaryEncryptor;

import com.anrisoftware.registration.data.RegisterKey;
import com.anrisoftware.registration.data.Registration;
import com.anrisoftware.registration.data.RegistrationLoader;

/**
 * Stores and loads the registration from an encrypted source.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.1
 */
public class RegistrationEncrypt {

    @Inject
    private RegistrationLoader registrationLoader;

    /**
     * Stores the registration in the specified file.
     *
     * @param registerKey
     *            the {@link RegisterKey} to store.
     *
     * @param password
     *            the {@link String} password.
     *
     * @param file
     *            the destination {@link File}.
     *
     * @throws IOException
     *             if there was an error saving the registration.
     */
    public void save(RegisterKey registerKey, String password, File file)
            throws IOException {
        FileOutputStream fstream = new FileOutputStream(file);
        save(registerKey, password, fstream);
    }

    /**
     * Stores the registration in the specified stream.
     *
     * @param registerKey
     *            the {@link RegisterKey} to store.
     *
     * @param password
     *            the {@link String} password.
     *
     * @param output
     *            the destination {@link OutputStream}.
     *
     * @throws IOException
     *             if there was an error saving the registration.
     */
    public void save(RegisterKey registerKey, String password,
            OutputStream output) throws IOException {
        byte[] binary = save(registerKey, password);
        output.write(binary);
        output.flush();
    }

    /**
     * Stores the registration in the specified byte array.
     *
     * @param registerKey
     *            the {@link RegisterKey} to store.
     *
     * @param password
     *            the {@link String} password.
     *
     * @return the byte array.
     *
     * @throws IOException
     *             if there was an error saving the registration.
     */
    public byte[] save(RegisterKey registerKey, String password)
            throws IOException {
        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(password);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        registerKey.save(bytes);
        return binaryEncryptor.encrypt(bytes.toByteArray());
    }

    /**
     * Loads the registration from the specified file.
     *
     * @param password
     *            the {@link String} password.
     *
     * @param file
     *            the source {@link File}.
     *
     * @return the loaded {@link Registration}.
     *
     * @throws IOException
     *             if there was an error reading the file.
     *
     * @throws ClassNotFoundException
     *             if the {@link Registration} class was not found.
     */
    public Registration load(String password, File file) throws IOException,
            ClassNotFoundException {
        FileInputStream fstream = new FileInputStream(file);
        return load(password, fstream);
    }

    /**
     * Loads the registration from the specified stream.
     *
     * @param password
     *            the {@link String} password.
     *
     * @param input
     *            the source {@link InputStream}.
     *
     * @return the loaded {@link Registration}.
     *
     * @throws IOException
     *             if there was an error reading the file.
     *
     * @throws ClassNotFoundException
     *             if the {@link Registration} class was not found.
     */
    public Registration load(String password, InputStream input)
            throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        copyData(input, bytes);
        return load(password, bytes.toByteArray());
    }

    /**
     * Loads the registration from the specified byte array.
     *
     * @param password
     *            the {@link String} password.
     *
     * @param input
     *            the source byte array.
     *
     * @return the loaded {@link Registration}.
     *
     * @throws IOException
     *             if there was an error reading the file.
     *
     * @throws ClassNotFoundException
     *             if the {@link Registration} class was not found.
     */
    public Registration load(String password, byte[] input) throws IOException,
            ClassNotFoundException {
        BasicBinaryEncryptor binaryEncryptor = new BasicBinaryEncryptor();
        binaryEncryptor.setPassword(password);
        ByteArrayInputStream estream = new ByteArrayInputStream(
                binaryEncryptor.decrypt(input));
        return registrationLoader.load(estream);
    }

    private void copyData(InputStream input, ByteArrayOutputStream output)
            throws IOException {
        byte[] buff = new byte[1024];
        int read = 0;
        while (true) {
            read = input.read(buff);
            if (read == -1) {
                break;
            }
            output.write(buff, 0, read);
        }
    }

}
