package org.adrian.rulesengine.core;

import lombok.AllArgsConstructor;
import org.adrian.rulesengine.core.execution.RuleExecution;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * A Combination of any list of {@link Predicate}s.
 * These predicates are typically {@link Condition}'s or {@link CombinedCondition}'s
 * themselves.
 *
 * @param <S> the type of the source
 */
@AllArgsConstructor
public class CombinedCondition<S> implements BiPredicate<S, RuleExecution<S>> {

    private final Combinator combinator;
    private final List<BiPredicate<S, RuleExecution<S>>> predicates;

    @Override
    public boolean test(S s, RuleExecution<S> ruleExecution) {
        return this.combinator.test(this.predicates, s, ruleExecution);
    }
}
