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
package com.anrisoftware.registration.data

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.google.inject.Guice.createInjector
import groovy.util.logging.Slf4j

import org.joda.time.DateTime
import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Injector

/**
 * @see Registration
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Slf4j
class RegistrationTest {

    @Test
    void "serialize registration data"() {
        DateTime date = DateTime.now()
        byte[] code = "code".bytes
        String name = "name"
        String key = "key"
        def registrationA = registrationFactory.create()
        registrationA.installDate = date
        registrationA.code = code
        registrationA.name = name
        registrationA.key = key

        def registrationB = reserialize registrationA
        assert registrationB.registerDate == registrationA.registerDate
        assert registrationB.installDate == registrationA.installDate
        assert registrationB.code == registrationA.code
        assert registrationB.name == registrationA.name
        assert registrationB.key == registrationA.key
    }

    @Test
    void "demo days left"() {
        DateTime installDate = new DateTime(2000, 1, 1, 0, 0, 0)
        DateTime registerDate = new DateTime(2012, 1, 1, 0, 0, 0)
        byte[] code = "code".bytes
        String name = "name"
        String key = "key"
        def registration = registrationFactory.create()
        registration.installDate = installDate
        registration.registerDate = registerDate
        registration.code = code
        registration.name = name
        registration.key = key
        def registerKey = registerKeyFactory.create(registration)

        def cases = [
            [daysDemo: 30, date: new DateTime(2000, 1, 10, 0, 0, 0), daysLeft: 21],
            [daysDemo: 30, date: new DateTime(2000, 1, 20, 0, 0, 0), daysLeft: 11],
            [daysDemo: 30, date: new DateTime(2000, 2, 1, 0, 0, 0), daysLeft: -1],
        ]
        cases.eachWithIndex { it, i ->
            log.info "{}.test case: {}", i, it
            registerKey.setDaysDemo it.daysDemo
            assert registerKey.getDaysLeftDemo(it.date) == it.daysLeft
        }
    }

    static Injector injector

    static RegistrationFactory registrationFactory

    static RegisterKeyFactory registerKeyFactory

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.injector = RegistrationDataModule.SingletonHolder.injector.createChildInjector()
        this.registrationFactory = RegistrationDataModule.getRegistrationFactory()
        this.registerKeyFactory = RegistrationDataModule.getRegisterKeyFactory()
    }
}
