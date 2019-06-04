package org.adrian.rulesengine.core;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A rule takes some {@code source}, tests it against some conditions ({@code predicates})
 * and applies some {@code action} if the conditions are met.
 *
 * @param <S> the type of the source, e.g. a java bean, xml or json etc.
 */
@AllArgsConstructor
public class Rule<S> {

    private final List<Predicate<S>> predicates;
    private final Consumer<S> action;

    public Rule(Predicate<S> predicate, Consumer<S> action) {
        this.predicates = List.of(predicate);
        this.action = action;
    }

    /**
     * Only testing the conditions ({@code predicates}).
     * @param source the source, e.g. a java bean, xml or json etc.
     * @return {@code true} if all conditions (predicates) are met, otherwise {@code false}
     */
    public boolean test(S source) {
        return predicates
                .stream()
                .allMatch(p -> p.test(source));
    }

    /**
     * Testing the conditions ({@code predicates}) and applying the {@code action}
     * only in the case the conditions are met.
     * @param source the source, e.g. a java bean, xml or json etc.
     */
    public void fire(S source) {
        if (test(source)) {
            action.accept(source);
        }
    }

    /**
     * Calling {@link #fire(Object)} for every single element in the passed in collection.
     * @param sources the sources, e.g. java beans, xml or json objects etc.
     */
    public void fire(Collection<? extends S> sources) {
        sources.forEach(this::fire);
    }
}
