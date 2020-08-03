package org.adrian.rulesengine.core.operator;

public class StringContains extends AbstractOperator<String, String> {

    @Override
    protected boolean testIgnoreNull(String left, String right) {
        return left.contains(right);
    }

    @Override
    public String getSymbol() {
        return "contains";
    }
}
