package org.adrian.rulesengine.core;

import lombok.Getter;
import lombok.NonNull;
import org.adrian.rulesengine.core.execution.CombinedConditionExecution;
import org.adrian.rulesengine.core.execution.RuleExecution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class CombinedCondition<S> implements BiPredicate<S, RuleExecution<S>> {

    @Getter
    private final Combinator combinator;
    private final List<BiPredicate<S, RuleExecution<S>>> predicates;

    public static <S> Builder<S> builder(Class<S> clazz, Combinator combinator) {
        return new Builder<>(clazz, combinator);
    }

    public static class Builder<S> {
        private final List<BiPredicate<S, RuleExecution<S>>> predicates = new ArrayList<>();
        private final Combinator combinator;

        private Builder(Class<S> clazz, @NonNull Combinator combinator) {
            this.combinator = combinator;
        }

        public Builder<S> addPredicate(@NonNull BiPredicate<S, RuleExecution<S>> predicate) {
            this.predicates.add(predicate);
            return this;
        }

        public Builder<S> addAllPredicates(@NonNull Collection<BiPredicate<S, RuleExecution<S>>> predicates) {
            predicates.addAll(predicates);
            return this;
        }

        public CombinedCondition<S> build() {
            return new CombinedCondition<>(this);
        }
    }

    private CombinedCondition(Builder<S> builder) {
        this.predicates = Collections.unmodifiableList(builder.predicates);
        this.combinator = builder.combinator;
    }

    private CombinedCondition() {
        this.predicates = Collections.emptyList();
        this.combinator = Combinator.AND;
    }

    @Override
    public boolean test(S s, RuleExecution<S> ruleExecution) {
        CombinedConditionExecution<S> execution = ruleExecution.newCombinedConditionExecution(this);
        execution.testResult(this.combinator.test(this.predicates, s, ruleExecution));
        ruleExecution.endCombinedConditionExecution();
        return execution.getTestResult();
    }
}
