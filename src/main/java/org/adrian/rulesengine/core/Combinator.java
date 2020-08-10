package org.adrian.rulesengine.core;

import org.adrian.rulesengine.core.execution.RuleExecution;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * The operators for combining several conditions.
 * E.g. color="blue" OR color="red".
 */
public enum Combinator {
    AND {
        @Override
        <S> boolean test(List<BiPredicate<S, RuleExecution<S>>> predicates, S s, RuleExecution<S> ruleExecution) {
            return predicates.stream()
                    .allMatch(predicate -> predicate.test(s, ruleExecution));
        }
    }, OR {
        @Override
        <S> boolean test(List<BiPredicate<S, RuleExecution<S>>> predicates, S s, RuleExecution<S> ruleExecution) {
            return predicates.stream()
                    .anyMatch(predicate -> predicate.test(s, ruleExecution));
        }
    }, XOR {
        @Override
        <S> boolean test(List<BiPredicate<S, RuleExecution<S>>> predicates, S s, RuleExecution<S> ruleExecution) {
            return predicates.stream()
                    .filter(predicate -> predicate.test(s, ruleExecution))
                    .count() == 1;
        }
    };

    abstract <S> boolean test(List<BiPredicate<S, RuleExecution<S>>> predicates, S s, RuleExecution<S> ruleExecution);
}
