#!/bin/bash
#
# Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
#
# This file is part of registration-core.
#
# registration-core is free software: you can redistribute it and/or modify it
# under the terms of the GNU Lesser General Public License as published by the
# Free Software Foundation, either version 3 of the License, or (at your
# option) any later version.
#
# registration-core is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
# details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with registration-core. If not, see <http://www.gnu.org/licenses/>.
#

java -classpath "$PWD/schluessel.jar" devent.keygenerator.GeneratorAusSchluessel $1
