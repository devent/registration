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
package com.anrisoftware.registration.help;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;

import com.anrisoftware.registration.appexceptions.AppException;
import com.anrisoftware.registration.commandline.AppCommandLine;
import com.anrisoftware.registration.workers.AppWorker;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.assistedinject.Assisted;

/**
 * Prints the application help.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public final class AppHelp implements AppWorker {

    private final Appendable output;

    @Inject
    private AppHelpLogger log;

    private Locale locale;

    private TextResource helpResource;

    @Inject
    AppHelp(@Assisted AppCommandLine commandLine, @Assisted Appendable output) {
        this.output = output;
        this.locale = Locale.getDefault();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Inject
    final void setTexts(TextsFactory factory) {
        Texts texts = factory.create(AppHelp.class.getSimpleName());
        this.helpResource = texts.getResource("help", locale);
    }

    /**
     * Print the help in the specified output.
     *
     * @param output
     *            the {@link Appendable} output.
     *
     * @throws AppException
     *             if there was an error printing the help.
     */
    public void printHelp(Appendable output) throws AppException {
        try {
            output.append(helpResource.getText());
        } catch (ResourcesException e) {
            throw log.errorLoadResource(e, helpResource.getName());
        } catch (IOException e) {
            throw log.errorPrintHelp(e, output);
        }
    }

    @Override
    public Appendable call() throws Exception {
        printHelp(output);
        return output;
    }

}
