Refine TrufflePython
====================

An experimental OpenRefine extension which adds support for Python 3 as an expression language.
Based on GraalPython (aka TrufflePython).

It is based on OpenRefine 4.0 (to be released). It is intended to run on the GraalVM JDK 21.

<!-- [Download the latest release](https://github.com/wetneb/refine-gython/releases/latest) -->


Building it
-----------

Run
```
mvn package
```

This creates a zip file in the `target` folder, which can then be [installed in OpenRefine](https://docs.openrefine.org/manual/installing#installing-extensions).

Developing it
-------------

To avoid having to unzip the extension in the corresponding directory every time you want to test it, you can also use another set up: simply create a symbolic link from your extensions folder in OpenRefine to the local copy of this repository.
You will still need to restart OpenRefine every time you make changes.
