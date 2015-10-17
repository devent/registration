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

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.google.inject.Guice.createInjector
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import org.joda.time.DateTime
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.registration.data.RegisterKey
import com.anrisoftware.registration.data.RegisterKeyFactory
import com.anrisoftware.registration.data.Registration
import com.anrisoftware.registration.data.RegistrationDataModule
import com.anrisoftware.registration.data.RegistrationFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see RegistrationEncrypt
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.1
 */
@CompileStatic
@Slf4j
public class RegistrationEncryptTest {

    @Test
    void "store and load registration"() {
        DateTime installDate = new DateTime(2000, 1, 1, 0, 0, 0)
        DateTime registerDate = new DateTime(2012, 1, 1, 0, 0, 0)
        int daysDemo = 30
        byte[] code = "code aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".bytes
        String name = "name aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        String key = "key aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        Registration registration = registrationFactory.create()
        registration.installDate = installDate
        registration.registerDate = registerDate
        registration.daysDemo = daysDemo
        registration.code = code
        registration.name = name
        registration.key = key
        RegisterKey registerKey = registerKeyFactory.create(registration)
        String password = "somepass"
        def file = folder.newFile()
        registrationEncrypt.save(registerKey, password, file)
        assert file.isFile() == true
        //Thread.sleep 60000
        Registration registrationB = registrationEncrypt.load(password, file)
        assert registrationB.installDate == installDate
        assert registrationB.registerDate == registerDate
        assert registrationB.daysDemo == daysDemo
        assert registrationB.code == code
        assert registrationB.name == name
        assert registrationB.key == key
    }

    static Injector injector

    static RegistrationEncrypt registrationEncrypt

    static RegistrationFactory registrationFactory

    static RegisterKeyFactory registerKeyFactory

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.injector = createInjector new RegistrationDataModule()
        this.registrationEncrypt = injector.getInstance RegistrationEncrypt
        this.registrationFactory = injector.getInstance RegistrationFactory
        this.registerKeyFactory = injector.getInstance RegisterKeyFactory
    }
}
