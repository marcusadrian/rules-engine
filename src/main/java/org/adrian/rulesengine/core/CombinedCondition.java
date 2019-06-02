package org.adrian.rulesengine.core;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Predicate;

/**
 * A Combination of any list of {@link Predicate}s.
 * These predicates are typically {@link Condition}'s or {@link CombinedCondition}'s
 * themselves.
 * @param <S> the type of the source, e.g. a java bean, xml or json etc.
 */
@AllArgsConstructor
public class CombinedCondition<S> implements Predicate<S> {

    private final List<Predicate<S>> predicates;
    private final Combinator combinator;

    @Override
    public boolean test(S s) {
        return this.combinator.test(predicates, s);
    }
}
