package io.github.wetneb.refinegython;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.openrefine.expr.LanguageSpecificParser;
import org.openrefine.expr.ParsingException;


/**
 * Parser for Python 3 expressions based on Truffle Python.
 */
public class GythonParser implements LanguageSpecificParser {
    
    static Context polyglotContext = null;

    private Context getContext() {
        if (polyglotContext == null) {
            polyglotContext = Context.newBuilder().allowAllAccess(true).build();
        }
        return polyglotContext;
    }

    @Override
    public GythonEvaluable parse(String s, String languagePrefix) throws ParsingException {
        String functionName = String.format("__temp_%d__", Math.abs(s.hashCode()));
        StringBuffer sb = new StringBuffer(1024);
        sb.append("def ");
        sb.append(functionName);
        sb.append("(value, cell, cells, row, rowIndex):");
        String[] lines = s.split("\r\n|\r|\n");
        for (String line : lines) {
            sb.append("\n  ");
            sb.append(line);
        }
        try {
            getContext().eval("python", sb.toString());
            Value function = getContext().eval("python", functionName);
            return new GythonEvaluable(getContext(), function, s, languagePrefix);
        } catch (PolyglotException exception) {
            throw new ParsingException(exception.getMessage());
        }
    }

}
