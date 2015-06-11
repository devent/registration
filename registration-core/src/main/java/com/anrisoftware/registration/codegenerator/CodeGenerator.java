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
package com.anrisoftware.registration.codegenerator;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

/**
 * Generates from the key the registration code.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public class CodeGenerator {

    private final String key;

    /**
     * @see CodeGeneratorFactory#create(String)
     */
    @Inject
    CodeGenerator(@Assisted String key) {
        this.key = key;
    }

    /**
     * Generates the code.
     *
     * @return the generated {@link String} code.
     */
    public String generateCode() {
        StringBuilder str = new StringBuilder();
        String schluessel = this.key.replaceAll("[-]+", "");
        for (int i = 0; i < schluessel.length(); i++) {
            int zeichen = schluessel.charAt(i);
            str.append((char) (zeichen * Math.PI * Math.E % 10 + 48));
        }
        return str.toString();
    }
}
