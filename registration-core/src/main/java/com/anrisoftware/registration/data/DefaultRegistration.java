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

    private int daysDemo;

    private DateTime registerDate;

    private DateTime installDate;

    private byte[] code;

    private String name;

    private String key;

    @Override
    public void setDaysDemo(int daysDemo) {
        this.daysDemo = daysDemo;
    }

    @Override
    public int getDaysDemo() {
        return daysDemo;
    }

    @Override
    public void setRegisterDate(DateTime registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public DateTime getRegisterDate() {
        return registerDate;
    }

    @Override
    public void setInstallDate(DateTime installDate) {
        this.installDate = installDate;
    }

    @Override
    public DateTime getInstallDate() {
        return installDate;
    }

    @Override
    public void setCode(byte[] code) {
        byte[] value = new byte[code.length];
        System.arraycopy(code, 0, value, 0, code.length);
        this.code = value;
    }

    @Override
    public byte[] getCode() {
        return code;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
