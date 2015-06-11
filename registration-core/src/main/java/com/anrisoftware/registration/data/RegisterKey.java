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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.google.inject.assistedinject.Assisted;

/**
 * Saves the registration.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class RegisterKey implements Registration {

    private final Registration registration;

    public int daysDemo;

    @Inject
    RegisterKey(@Assisted Registration registration) {
        this.registration = registration;
    }

    /**
     * Sets the days for the demo.
     *
     * @param daysDemo
     *            sets the {@link Integer} days.
     */
    public void setDaysDemo(int daysDemo) {
        this.daysDemo = daysDemo;
    }

    /**
     * Saves the registration into a file.
     *
     * @param file
     *            the {@link File} file.
     *
     * @throws IOException
     *             if there was an error saving the file.
     */
    public void save(File file) throws IOException {
        FileOutputStream fstream = new FileOutputStream(file);
        ObjectOutputStream stream = new ObjectOutputStream(fstream);
        stream.writeObject(registration);
        stream.close();
    }

    /**
     * Returns the days until the demo expire.
     *
     * @param date
     *            the current {@link DateTime} date.
     *
     * @return the {@link Long} days left.
     */
    public long getDaysLeftDemo(DateTime date) {
        DateTime installDate = registration.getInstallDate();
        long days = new Duration(installDate, date).getStandardDays();
        return daysDemo - days;
    }

    @Override
    public void setRegisterDate(DateTime registerDate) {
        registration.setRegisterDate(registerDate);
    }

    @Override
    public DateTime getRegisterDate() {
        return registration.getRegisterDate();
    }

    @Override
    public void setInstallDate(DateTime installDate) {
        registration.setInstallDate(installDate);
    }

    @Override
    public DateTime getInstallDate() {
        return registration.getInstallDate();
    }

    @Override
    public void setCode(byte[] code) {
        registration.setCode(code);
    }

    @Override
    public byte[] getCode() {
        return registration.getCode();
    }

    @Override
    public void setName(String name) {
        registration.setName(name);
    }

    @Override
    public String getName() {
        return registration.getName();
    }

    @Override
    public void setKey(String key) {
        registration.setKey(key);
    }

    @Override
    public String getKey() {
        return registration.getKey();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(registration).toString();
    }

}
