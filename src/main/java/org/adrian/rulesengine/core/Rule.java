package org.adrian.rulesengine.core;

import lombok.NonNull;
import org.adrian.rulesengine.core.execution.RuleExecution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * A rule takes some {@code source}, tests it against some conditions ({@code predicates})
 * and applies some {@code action} if the conditions are met.
 *
 * @param <S> the type of the source
 */
public class Rule<S> {

    private CombinedCondition<S> root;
    private final BiConsumer<S, RuleExecution<S>> action;

    public static <S> Builder<S> builder(Class<S> clazz) {
        return new Builder<>(clazz);
    }

    public static class Builder<S> {

        private final Class<S> clazz;
        private final List<BiPredicate<S, RuleExecution<S>>> predicates = new ArrayList<>();
        private BiConsumer<S, RuleExecution<S>> action = (s, r) -> {
        };

        private Builder(Class<S> clazz) {
            this.clazz = clazz;
        }

        public Builder<S> addPredicate(@NonNull BiPredicate<S, RuleExecution<S>> predicate) {
            this.predicates.add(predicate);
            return this;
        }

        public Builder<S> addAllPredicates(@NonNull Collection<BiPredicate<S, RuleExecution<S>>> predicates) {
            predicates.addAll(predicates);
            return this;
        }

        public Builder<S> action(@NonNull BiConsumer<S, RuleExecution<S>> action) {
            this.action = action;
            return this;
        }

        public Builder<S> action(@NonNull Consumer<S> action) {
            this.action = (s, r) -> action.accept(s);
            return this;
        }

        public Rule<S> build() {
            return new Rule<>(this);
        }
    }

    private Rule(Builder<S> builder) {
        this.root = CombinedCondition.builder(builder.clazz, Combinator.AND)
                .addAllPredicates(Collections.unmodifiableList(builder.predicates))
                .build();
        this.action = builder.action;
    }

    /**
     * Only testing the conditions ({@code predicates}).
     *
     * @param source the source
     * @return {@code true} if all conditions (predicates) are met, otherwise {@code false}
     */
    public RuleExecution<S> test(S source) {
        RuleExecution<S> execution = new RuleExecution<>();
        this.cleanupRoot();
        boolean executionResult = this.root.test(source, execution);
        return execution.executionResult(executionResult);
    }

    // strip off outer AND CombinedCondition if unnecessary
    private void cleanupRoot() {
        List<BiPredicate<S, RuleExecution<S>>> predicates = this.root.getPredicates();
        if (predicates.size() == 1) {
            BiPredicate<S, RuleExecution<S>> predicate = predicates.get(0);
            if (predicate instanceof CombinedCondition) {
                this.root = (CombinedCondition<S>) predicate;
            }
        }
    }

    /**
     * Testing the conditions ({@code predicates}) and applying the {@code action}
     * only in the case the conditions are met.
     *
     * @param source the source
     */
    public RuleExecution<S> fire(S source) {
        RuleExecution<S> execution = this.test(source);
        if (execution.getExecutionResult()) {
            this.action.accept(source, execution);
        }
        return execution;
    }

    /**
     * Calling {@link #fire(Object)} for every single element in the passed in collection.
     *
     * @param sources the sources
     */
    public void fire(Collection<? extends S> sources) {
        sources.forEach(this::fire);
    }
}
