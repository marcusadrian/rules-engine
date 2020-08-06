package org.adrian.rulesengine.core.operator;

import java.util.Collection;
import java.util.function.BiPredicate;

public class Each<L, R> {

    private BiPredicate<L, R> operator;

    protected boolean test(Collection<? extends L> left, R right) {
        return left.stream().allMatch(e -> this.operator.test(e, right));
    }
}
