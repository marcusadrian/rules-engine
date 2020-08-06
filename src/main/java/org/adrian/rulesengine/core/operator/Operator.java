package org.adrian.rulesengine.core.operator;

import java.util.function.BiPredicate;

public interface Operator<L, R> extends BiPredicate<L, R> {
    String getSymbol();
}
