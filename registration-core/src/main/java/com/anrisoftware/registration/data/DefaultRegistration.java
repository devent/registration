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

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * Registration data.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
public final class DefaultRegistration implements Registration, Serializable {

    private DateTime registerDate;

    private DateTime installDate;

    private byte[] code;

    private String name;

    private String key;

    /**
     * Sets the registration date.
     *
     * @param registerDate
     *            the {@link DateTime} date.
     */
    public void setRegisterDate(DateTime registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * The registration date.
     *
     * @return the {@link DateTime} date.
     */
    public DateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets the install date.
     *
     * @param installDate
     *            the {@link DateTime} date.
     */
    public void setInstallDate(DateTime installDate) {
        this.installDate = installDate;
    }

    /**
     * The install date.
     *
     * @return the {@link DateTime} date.
     */
    public DateTime getInstallDate() {
        return installDate;
    }

    /**
     * Sets the registration code.
     *
     * @param code
     *            the code as a {@link Byte} array.
     */
    public void setCode(byte[] code) {
        byte[] value = new byte[code.length];
        System.arraycopy(code, 0, value, 0, code.length);
        this.code = value;
    }

    /**
     * Returns the registration code.
     *
     * @return the code as a {@link Byte} array.
     */
    public byte[] getCode() {
        return code;
    }

    /**
     * Returns the registration name.
     *
     * @param name
     *            the {@link String} name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the registration name.
     *
     * @return the {@link String} name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the registration key.
     *
     * @param key
     *            the {@link String} key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the registration key.
     *
     * @return the {@link String} key.
     */
    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
