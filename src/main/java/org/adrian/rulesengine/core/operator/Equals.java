package org.adrian.rulesengine.core.operator;

public class Equals extends AbstractOperator<Object, Object> {

    @Override
    protected boolean testIgnoreNull(Object left, Object right) {
        return left.equals(right);
    }

    @Override
    public String getSymbol() {
        return "=";
    }
}
