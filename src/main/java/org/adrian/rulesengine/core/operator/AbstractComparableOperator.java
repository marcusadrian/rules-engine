package org.adrian.rulesengine.core.operator;

public abstract class AbstractComparableOperator<T> extends AbstractOperator<Comparable<? super T>, T> {

    @Override
    protected boolean testIgnoreNull(Comparable<? super T> left, T right) {
        return this.testCompareToResult(left.compareTo(right));
    }

    protected abstract boolean testCompareToResult(int result);

}
