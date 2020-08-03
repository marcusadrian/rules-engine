package org.adrian.rulesengine.core.operator;

import java.util.function.BiPredicate;

public interface Operator<T, U> extends BiPredicate<T, U> {
    String getSymbol();
}
