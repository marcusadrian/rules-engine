package org.adrian.rulesengine.core.operator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Operators {

    private static final Equals EQ = new Equals();
    private static final GreaterThan GT = new GreaterThan();

    public static Equals eq() {
        return EQ;
    }

    public static <T> GreaterThan<T> gt() {
        @SuppressWarnings("unchecked")
        GreaterThan<T> gt = GT;
        return gt;
    }
}
