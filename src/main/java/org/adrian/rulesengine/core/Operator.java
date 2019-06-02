package org.adrian.rulesengine.core;


import java.util.Collection;

/**
 * An operator of a condition, e.g. the '>' in the condition size > 15.
 */
public enum Operator {
    EQ {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            return left.equals(right);
        }
    },
    LT {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            return compareToHelper(left, right) < 0;
        }
    },
    GT {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            return compareToHelper(left, right) > 0;
        }
    },
    LE {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            return compareToHelper(left, right) <= 0;
        }
    },
    GE {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            return compareToHelper(left, right) >= 0;
        }
    },
    CONTAINS {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            if (left instanceof String) {
                return ((String) left).contains((String) right);
            }
            if (left instanceof Collection) {
                return ((Collection<?>) left).contains(right);
            }
            return false;
        }
    },
    EQUALS_IGNORE_CASE {
        @Override
        public boolean test(Object left, Object right) {
            if (isNull(left, right)) {
                return false;
            }
            return String.valueOf(left).equalsIgnoreCase(String.valueOf(right));
        }
    };

    abstract boolean test(Object left, Object right);

    private static int compareToHelper(Object left, Object right) {
        @SuppressWarnings("unchecked")
        Comparable<Object> cLeft = (Comparable<Object>) left;
        return cLeft.compareTo(right);
    }

    private static boolean isNull(Object left, Object right) {
        if (left == null || right == null) {
            return true;
        } else {
            return false;
        }
    }
}
