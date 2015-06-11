/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of registration-appcli.
 *
 * registration-appcli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * registration-appcli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * registration-appcli. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.registration.app

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import com.google.inject.Injector

/**
 * @see App
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Slf4j
class AppTest {

    @Test
    void "app test"() {
        def cases = [
            [
                args: [],
                expectedOutputPattern: /\w{4}-\w{4}-\w{4}-\w{4}/,
                expectedException: null
            ],
            [
                args: [
                    "-name",
                    "Erwin",
                    "-email",
                    "test"
                ],
                expectedOutputPattern: /\w{4}-\w{4}-\w{4}-\w{4}/,
                expectedException: null
            ],
            [
                args: [
                    "-key",
                    "BNTH-OOSR-LNRS-YHCF"
                ],
                expectedOutputPattern: /3674448096080427/,
                expectedException: null
            ],
            [
                args: [
                    "-help"
                ],
                expectedOutputPattern: /Description.*/,
                expectedException: null
            ],
        ]
        cases.eachWithIndex { testCase, i ->
            def output = new StringBuilder()
            App app = injector.getInstance App
            app.setOutput output

            if (testCase.expectedException == null) {
                app.start(testCase.args as String[])
                log.info "{}.test case: {} output: '{}'", i, testCase, output.toString()
                assert output.toString() =~ testCase.expectedOutputPattern
            } else {
                log.info "{}.test case: {}", i, testCase
                shouldFailWith(testCase.expectedException, {
                    app.start(testCase.args as String[])
                })
            }
        }
    }

    static Injector injector

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.injector = AppModule.SingletonHolder.injector.createChildInjector()
    }
}
