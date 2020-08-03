package org.adrian.rulesengine.core.operator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Operators {

    private static final Equals EQ = new Equals();

    @SuppressWarnings("rawtypes")
    private static final LowerThan LT = new LowerThan();

    @SuppressWarnings("rawtypes")
    private static final LowerThanOrEquals LE = new LowerThanOrEquals();

    @SuppressWarnings("rawtypes")
    private static final GreaterThan GT = new GreaterThan();

    @SuppressWarnings("rawtypes")
    private static final GreaterThanOrEquals GE = new GreaterThanOrEquals();

    @SuppressWarnings("rawtypes")
    private static final Each EACH = new Each();

    @SuppressWarnings("rawtypes")
    private static final CollectionContains COLLECTION_CONTAINS = new CollectionContains();

    public static Equals eq() {
        return EQ;
    }

    @SuppressWarnings("unchecked")
    public static <T> LowerThan<T> lt() {
        return LT;
    }

    @SuppressWarnings("unchecked")
    public static <T> LowerThanOrEquals<T> le() {
        return LE;
    }

    @SuppressWarnings("unchecked")
    public static <T> GreaterThan<T> gt() {
        return GT;
    }

    @SuppressWarnings("unchecked")
    public static <T> GreaterThanOrEquals<T> ge() {
        return GE;
    }

    @SuppressWarnings("unchecked")
    public static <T, U> Each<T, U> each() {
        return EACH;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Collection {

        @SuppressWarnings("unchecked")
        public static <T> CollectionContains<T> contains() {
            return COLLECTION_CONTAINS;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class String {

        public static StringEquals.StringEqualsBuilder eq() {
            return new StringEquals.StringEqualsBuilder();
        }

        public static StringContains.StringContainsBuilder contains() {
            return new StringContains.StringContainsBuilder();
        }
    }

}
