
/*
 * Function invoked to initialize the extension.
 */
function init() {
   
  Packages.org.openrefine.expr.MetaParser.registerLanguageParser(
    "gython",
    "Python 3 (Truffle)",
    Packages.io.github.wetneb.refinegython.GythonEvaluable.createParser(),
    "return value"
  );
}

