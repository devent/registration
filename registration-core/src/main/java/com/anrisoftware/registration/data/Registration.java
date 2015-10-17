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

import org.joda.time.DateTime;

/**
 * Registration data.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public interface Registration {

    /**
     * Sets the days for the demo.
     *
     * @param daysDemo
     *            sets the {@link Integer} days.
     *
     * @since 1.1
     */
    void setDaysDemo(int daysDemo);

    /**
     * Returns the days for the demo.
     *
     * @return the {@link Integer} days.
     *
     * @since 1.1
     */
    int getDaysDemo();

    /**
     * Sets the registration date.
     *
     * @param registerDate
     *            the {@link DateTime} date.
     */
    void setRegisterDate(DateTime registerDate);

    /**
     * The registration date.
     *
     * @return the {@link DateTime} date.
     */
    DateTime getRegisterDate();

    /**
     * Sets the install date.
     *
     * @param installDate
     *            the {@link DateTime} date.
     */
    void setInstallDate(DateTime installDate);

    /**
     * The install date.
     *
     * @return the {@link DateTime} date.
     */
    DateTime getInstallDate();

    /**
     * Sets the registration code.
     *
     * @param code
     *            the code as a {@link Byte} array.
     */
    void setCode(byte[] code);

    /**
     * Returns the registration code.
     *
     * @return the code as a {@link Byte} array.
     */
    byte[] getCode();

    /**
     * Returns the registration name.
     *
     * @param name
     *            the {@link String} name.
     */
    void setName(String name);

    /**
     * Sets the registration name.
     *
     * @return the {@link String} name.
     */
    String getName();

    /**
     * Sets the registration key.
     *
     * @param key
     *            the {@link String} key.
     */
    void setKey(String key);

    /**
     * Returns the registration key.
     *
     * @return the {@link String} key.
     */
    String getKey();

}
