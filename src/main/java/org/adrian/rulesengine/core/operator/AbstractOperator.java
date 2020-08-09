package org.adrian.rulesengine.core.operator;

public abstract class AbstractOperator<L, R> implements Operator<L, R> {

    @Override
    public boolean test(L left, R right) {
        if (left == null || right == null) {
            return false;
        }
        return this.testIgnoreNull(left, right);
    }

    protected abstract boolean testIgnoreNull(L left, R right);
}
