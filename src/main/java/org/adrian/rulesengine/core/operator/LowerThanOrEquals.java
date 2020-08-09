package org.adrian.rulesengine.core.operator;

public class LowerThanOrEquals<T> extends AbstractComparableOperator<T> {

    @Override
    protected boolean testCompareToResult(int result) {
        return result <= 0;
    }

    @Override
    public String getSymbol() {
        return "<=";
    }
}