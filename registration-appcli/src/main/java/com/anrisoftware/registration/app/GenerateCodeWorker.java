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

import com.anrisoftware.registration.codegenerator.CodeGeneratorFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Generates the registration code.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
final class GenerateCodeWorker implements AppWorker {

    private final AppCommandLineParser commandLine;

    private final Appendable output;

    @Inject
    private CodeGeneratorFactory codeGeneratorFactory;

    /**
     * @see AppWorkerFactory#create(AppCommandLineParser, Appendable)
     */
    @Inject
    GenerateCodeWorker(@Assisted AppCommandLineParser commandLine,
            @Assisted Appendable output) {
        this.commandLine = commandLine;
        this.output = output;
    }

    @Override
    public Appendable call() throws Exception {
        String key = commandLine.getKey();
        String code = codeGeneratorFactory.create(key).generateCode();
        output.append(code);
        return output;
    }

}
