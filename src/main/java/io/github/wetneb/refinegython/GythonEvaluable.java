package io.github.wetneb.refinegython;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.openrefine.expr.Evaluable;
import org.openrefine.expr.HasFields;

import java.util.Properties;

/**
 * Evaluable object based on Python 3 code executed via Truffle Python.
 */
public class GythonEvaluable implements Evaluable {
    
    protected final Context context;
    protected final Value function;
    protected final String source;
    protected final String languagePrefix;
    
    public GythonEvaluable(Context context, Value function, String source, String languagePrefix) {
        this.context = context;
        this.function = function;
        this.source = source;
        this.languagePrefix = languagePrefix;
    }

    public static GythonParser createParser() {
        return new GythonParser();
    }

    @Override
    public Object evaluate(Properties bindings) {
        return unwrapTruffleValue(function.execute(
                bindings.get("value"),
                new HasFieldsWrapper((HasFields) bindings.get("cell")),
                new HasFieldsWrapper((HasFields) bindings.get("cells")),
                bindings.get("row"),
                bindings.get("rowIndex")));
    }

    private Object unwrapTruffleValue(Value value) {
        if (value.isString()) {
            return value.asString();
        } else if (value.isBoolean()) {
            return value.asBoolean();
        } else if (value.isDate()) {
            return value.asDate();
        } else if (value.isDuration()) {
            return value.asDuration();
        } else if (value.isNull()) {
            return null;
        } else {
            return value;
        }
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getLanguagePrefix() {
        return languagePrefix;
    }

}
