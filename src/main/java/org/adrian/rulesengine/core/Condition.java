package org.adrian.rulesengine.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The basic condition, we might also call it "leaf" condition (compared to {@link CombinedCondition}).
 * E.g. color = "blue". Contains the right hand value ({@link #getValue()}, "blue"),
 * the operator ({@link #getOperator(), "="}),
 * and the function to retrieve the right hand value ({@link #getFieldAccessor(), color}.
 * @param <S> the type of the source, e.g. a java bean, xml or json etc.
 * @param <T> the type of the field to be retrieved
 */
@RequiredArgsConstructor
@Getter
public class Condition<S, T> implements Predicate<S> {
    private final Operator operator;
    /**
     * The function to retrieve the left hand value.
     * E.g. condition : color = "blue" this would be the function to retrieve the color.
     *
     * @param <S> the type of the source, e.g. a java bean, xml or json etc.
     * @param <T> the type of the field to be retrieved
     */
    private final Function<S, T> fieldAccessor;
    /**
     * The right hand value.
     * E.g. "blue" in condition : color = "blue"
     */
    private final T value;

    @Override
    public boolean test(S source) {
        return operator.test(fieldAccessor.apply(source), value);
    }
}
