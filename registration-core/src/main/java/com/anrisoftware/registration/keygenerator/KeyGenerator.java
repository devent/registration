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
package com.anrisoftware.registration.keygenerator;

import java.util.Random;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Generates a random key. The key is formatted in four groups, separated by a
 * minus.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class KeyGenerator {

    private final Random random;

    /**
     * @see KeyGeneratorFactory#create()
     */
    @AssistedInject
    KeyGenerator() {
        this(new Random());
    }

    /**
     * @see KeyGeneratorFactory#create(Random)
     */
    @AssistedInject
    KeyGenerator(@Assisted Random random) {
        this.random = random;
    }

    /**
     * Generates and return the key.
     *
     * @return the {@link String} key.
     */
    public String generateKey() {
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                str.append((char) getCharacter());
            }
            str.append("-");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    private short getCharacter() {
        short c = (short) (random.nextFloat()
                * random.nextInt(Integer.MAX_VALUE) % 25 + 65);
        return c;
    }
}
