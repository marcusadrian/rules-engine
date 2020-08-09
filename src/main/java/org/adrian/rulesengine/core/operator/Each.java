package org.adrian.rulesengine.core.operator;

import java.util.Collection;
import java.util.function.BiPredicate;

public class Each<L, R> extends AbstractOperator<Collection<? extends L>, R> {

    private BiPredicate<L, R> operator;

    @Override
    protected boolean testIgnoreNull(Collection<? extends L> left, R right) {
        return left.stream().allMatch(e -> this.operator.test(e, right));
    }

    @Override
    public String getSymbol() {
        return "each";
    }
}
