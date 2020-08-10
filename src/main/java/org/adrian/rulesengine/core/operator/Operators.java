package org.adrian.rulesengine.core.operator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Operators {

    private static final Equals EQ = new Equals();

    private static final LowerThan LT = new LowerThan();

    private static final LowerThanOrEquals LE = new LowerThanOrEquals();

    private static final GreaterThan GT = new GreaterThan();

    private static final GreaterThanOrEquals GE = new GreaterThanOrEquals();

    private static final Each EACH = new Each();

    private static final CollectionContains COLLECTION_CONTAINS = new CollectionContains();

    public static Equals eq() {
        return EQ;
    }

    public static <T extends Comparable<? super T>> LowerThan<T> lt() {
        return LT;
    }

    public static <T extends Comparable<? super T>> LowerThanOrEquals<T> le() {
        return LE;
    }

    public static <T extends Comparable<? super T>> GreaterThan<T> gt() {
        return GT;
    }

    public static <T extends Comparable<? super T>> GreaterThanOrEquals<T> ge() {
        return GE;
    }

    public static <T, U> Each<T, U> each() {
        return EACH;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Collection {

        public static <T> CollectionContains<T> contains() {
            return COLLECTION_CONTAINS;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class String {

        public static StringEquals.Builder eq() {
            return new StringEquals.Builder();
        }

        public static StringContains.Builder contains() {
            return new StringContains.Builder();
        }
    }

}
