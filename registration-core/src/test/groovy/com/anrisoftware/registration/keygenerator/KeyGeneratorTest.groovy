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
package com.anrisoftware.registration.keygenerator

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.google.inject.Guice.createInjector
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Injector

/**
 * @see KeyGenerator
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Slf4j
class KeyGeneratorTest {

    @Test
    void "generate key"() {
        def random = new Random(1)
        def generator = generatorFactory.create random
        def key = generator.generateKey()
        assert key == "HMHN-KUQB-SVFI-ELBO"
    }

    static Injector injector

    static KeyGeneratorFactory generatorFactory

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.injector = createInjector(new KeyGeneratorModule())
        this.generatorFactory = injector.getInstance KeyGeneratorFactory
    }
}
