package org.adrian.rulesengine.core.operator;

import java.util.Collection;

public class CollectionContains<T> extends AbstractOperator<Collection<? super T>, T> {

    @Override
    protected boolean testIgnoreNull(Collection<? super T> left, T right) {
        return left.contains(right);
    }

    @Override
    public String getSymbol() {
        return "contains";
    }
}
