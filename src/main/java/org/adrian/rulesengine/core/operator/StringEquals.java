package org.adrian.rulesengine.core.operator;

import lombok.Builder;
// TODO écrire builder
@Builder
public class StringEquals extends AbstractStringOperator {
    
    @Override
    protected String getRawSymbol() {
        return "eq";
    }

    @Override
    protected boolean doTest(String left, String right) {
        return this.ignoreCase ? left.equalsIgnoreCase(right) : left.equals(right);
    }
}
