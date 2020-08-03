package org.adrian.rulesengine.core.operator;

import lombok.Builder;

@Builder
public class StringContains extends AbstractStringOperator {

    @Override
    protected boolean doTest(String left, String right) {
        return left.contains(right);
    }

    @Override
    protected String getRawSymbol() {
        return "contains";
    }
}
