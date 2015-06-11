${project.name} ${project.version}

Start

  To start the application there are scripts installed in the bin/ directory.
For Linux it is the "bin/linux/${project.custom.app.linuxbin}" script, for
Windows it is the "bin/windows/${project.custom.app.windowsbin}" script. The
script can be run from anywhere, i.e.:

---
bin/linux/${project.custom.app.linuxbin}
---

---
bin/windows/${project.custom.app.windowsbin}
---

Debug

  A version for debug purpose is in the same directory as the start script and
is run in the same matter, i.e.:

---
bin/linux/${project.custom.app.linuxdebugbin}
---

---
bin/windows/${project.custom.app.windowsdebugbin}
---

Tested

  Tested on the following platforms and Java versions:

  * Fedora 20 amd64; Java 1.7.0_79 (14/05/2015)
  * Windows XP x86; Java 1.8.0_45 (14/05/2015)
  * Windows 7 x64; Java 1.8.0_45 (14/05/2015)
