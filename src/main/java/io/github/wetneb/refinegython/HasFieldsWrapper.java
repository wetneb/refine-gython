package io.github.wetneb.refinegython;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyHashMap;
import org.graalvm.polyglot.proxy.ProxyObject;
import org.openrefine.expr.HasFields;

/**
 * Wraps a Java object meant to be interacted with via GREL
 * to a Javascript value.
 * 
 * @author Antonin Delpeuch
 *
 */
public class HasFieldsWrapper implements ProxyObject, ProxyHashMap {
    
    protected final HasFields obj;
    
    public HasFieldsWrapper(HasFields obj) {
        this.obj = obj;
    }

    @Override
    public Object getMember(String key) {
        Object returnValue = obj.getField(key);
        if (returnValue instanceof HasFields) {
            return new HasFieldsWrapper((HasFields) returnValue);
        } else {
            return returnValue;
        }
    }

    @Override
    public Object getMemberKeys() {
        return null; // not supported yet
    }

    @Override
    public boolean hasMember(String key) {
        return obj.getField(key) != null;
    }

    @Override
    public void putMember(String key, Value value) {
        // no-op
    }

    @Override
    public long getHashSize() {
        return 0; // not supported
    }

    @Override
    public boolean hasHashEntry(Value key) {
        return getHashValue(key) != null;
    }

    @Override
    public Object getHashValue(Value key) {
        if (key.isString()) {
            return getMember(key.asString());
        } else {
            return null; // unsupported
        }
    }

    @Override
    public void putHashEntry(Value key, Value value) {
        // no-op
    }

    @Override
    public Object getHashEntriesIterator() {
        return null; // not supported
    }
}
