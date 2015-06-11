/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of registration-appcli.
 *
 * registration-appcli is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * registration-appcli is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with registration-appcli. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.registration.app;

import javax.inject.Inject;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Parses the registration application command line arguments.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
final class AppCommandLineParser implements AppCommandLine {

    static final String KEY_ARG = "-key";

    static final String EMAIL_ARG = "-email";

    static final String NAME_ARG = "-name";

    @Inject
    private GenerateCodeWorkerFactory generateCodeWorkerFactory;

    @Inject
    private GenerateKeyWorkerFactory generateKeyWorkerFactory;

    @Option(name = NAME_ARG, required = true, depends = { EMAIL_ARG })
    private String name;

    @Option(name = EMAIL_ARG, required = true, depends = { NAME_ARG })
    private String email;

    @Option(name = KEY_ARG, required = true, forbids = { NAME_ARG, EMAIL_ARG })
    private String key;

    private AppWorkerFactory workerFactory;

    /**
     * Parses the command line arguments.
     *
     * @param args
     *            the command line arguments.
     *
     * @return this {@link AppCommandLineParser}.
     *
     * @throws CmdLineException
     *             if there was an error parsing the command line arguments.
     */
    public AppCommandLineParser parse(String[] args) throws CmdLineException {
        new CmdLineParser(this).parseArgument(args);
        if (isGenerateKey()) {
            this.workerFactory = generateKeyWorkerFactory;
        }
        if (isGenerateCode()) {
            this.workerFactory = generateCodeWorkerFactory;
        }
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getKey() {
        return key;
    }

    public AppWorkerFactory getWorkerFactory() {
        return workerFactory;
    }

    private boolean isGenerateCode() {
        return key == null;
    }

    private boolean isGenerateKey() {
        return key != null;
    }

}
