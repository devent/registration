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
package com.anrisoftware.registration.app;

import javax.inject.Inject;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.anrisoftware.registration.commandline.AppCommandLine;
import com.anrisoftware.registration.help.AppHelpFactory;
import com.anrisoftware.registration.workers.AppWorkerFactory;
import com.anrisoftware.registration.workers.GenerateCodeWorkerFactory;
import com.anrisoftware.registration.workers.GenerateKeyWorkerFactory;

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

    static final String HELP_ARG = "-help";

    @Inject
    private GenerateCodeWorkerFactory generateCodeWorkerFactory;

    @Inject
    private GenerateKeyWorkerFactory generateKeyWorkerFactory;

    @Inject
    private AppHelpFactory appHelpFactory;

    @Option(name = NAME_ARG, depends = { EMAIL_ARG })
    private String name;

    @Option(name = EMAIL_ARG, depends = { NAME_ARG })
    private String email;

    @Option(name = KEY_ARG, forbids = { NAME_ARG, EMAIL_ARG })
    private String key;

    private boolean help;

    private AppWorkerFactory workerFactory;

    AppCommandLineParser() {
        this.help = false;
    }

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
        if (help) {
            this.workerFactory = appHelpFactory;
        } else if (isGenerateKey()) {
            this.workerFactory = generateKeyWorkerFactory;
        } else if (isGenerateCode()) {
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

    @Option(name = HELP_ARG, forbids = { NAME_ARG, EMAIL_ARG, KEY_ARG })
    public void setHelp(boolean help) {
        this.help = help;
    }

    public AppWorkerFactory getWorkerFactory() {
        return workerFactory;
    }

    private boolean isGenerateCode() {
        return key != null;
    }

    private boolean isGenerateKey() {
        return key == null;
    }

}
