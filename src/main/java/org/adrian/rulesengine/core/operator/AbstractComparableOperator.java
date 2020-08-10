package org.adrian.rulesengine.core.operator;

public abstract class AbstractComparableOperator<T extends Comparable<? super T>> extends AbstractOperator<T, T> {

    @Override
    protected boolean testIgnoreNull(T left, T right) {
        return this.testCompareToResult(left.compareTo(right));
    }

    protected abstract boolean testCompareToResult(int result);

}
