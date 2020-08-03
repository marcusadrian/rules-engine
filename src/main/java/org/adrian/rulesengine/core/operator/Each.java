package org.adrian.rulesengine.core.operator;

import java.util.Collection;
import java.util.function.BiPredicate;

public class Each<T, U> {

    private BiPredicate<T, U> operator;

    protected boolean test(Collection<? extends T> left, U right) {
        return left.stream().allMatch(e -> this.operator.test(e, right));
    }
}
