package org.adrian.rulesengine.core.operator;

public abstract class AbstractOperator<T, U> implements Operator<T, U> {

    @Override
    public boolean test(T left, U right) {
        if (left == null || right == null) {
            return false;
        }
        return this.testIgnoreNull(left, right);
    }

    protected abstract boolean testIgnoreNull(T left, U right);
}
