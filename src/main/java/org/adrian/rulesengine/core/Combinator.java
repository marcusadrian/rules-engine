package org.adrian.rulesengine.core;

import java.util.List;
import java.util.function.Predicate;

/**
 * The operators for combining several conditions.
 * E.g. color="blue" OR color="red".
 */
public enum Combinator {
    AND {
        @Override
        <S> boolean test(List<Predicate<S>> predicates, S s) {
            return predicates.stream()
                    .allMatch(predicate -> predicate.test(s));
        }
    }, OR {
        @Override
        <S> boolean test(List<Predicate<S>> predicates, S s) {
            return predicates.stream()
                    .anyMatch(predicate -> predicate.test(s));
        }
    }, XOR {
        @Override
        <S> boolean test(List<Predicate<S>> predicates, S s) {
            return predicates.stream()
                    .filter(predicate -> predicate.test(s))
                    .count() == 1;
        }
    };

    abstract <S> boolean test(List<Predicate<S>> predicates, S s);
}
