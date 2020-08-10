package org.adrian.rulesengine.core.operator;

public class GreaterThanOrEquals<T extends Comparable<? super T>> extends AbstractComparableOperator<T> {

    @Override
    protected boolean testCompareToResult(int result) {
        return result >= 0;
    }

    @Override
    public String getSymbol() {
        return ">=";
    }
}
