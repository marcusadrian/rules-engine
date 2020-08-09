package org.adrian.rulesengine.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.adrian.rulesengine.core.execution.ConditionExecution;
import org.adrian.rulesengine.core.execution.RuleExecution;
import org.adrian.rulesengine.core.operator.Operator;

import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * The basic condition, we might also call it "leaf" condition (compared to {@link CombinedCondition}).
 * E.g. color = "blue". Contains the right hand value ({@link #getValue()}, "blue"),
 * the operator ({@link #getOperator()}, "="),
 * and the function to retrieve the right hand value ({@link #getFieldAccessor()}, color).
 *
 * @param <S> the type of the source
 * @param <L> the type of the field to be retrieved
 * @param <R> the expected value of the field which makes the condition comply
 */
@RequiredArgsConstructor
@Getter
public class Condition<S, L, R> implements BiPredicate<S, RuleExecution<S>> {
    private final Operator<L, R> operator;
    /**
     * The function to retrieve the left hand value.
     * E.g. condition : color = "blue" this would be the function to retrieve the color.
     */
    private final Function<S, L> fieldAccessor;
    /**
     * The right hand value.
     * E.g. "blue" in condition : color = "blue"
     */
    private final R value;

    @Override
    public boolean test(S source, RuleExecution<S> ruleExecution) {
        ConditionExecution<S, L, R> conditionExecution = ruleExecution.newConditionExecution(this);
        return conditionExecution
                .leftValue(this.fieldAccessor.apply(source))
                .testResult(this.operator.test(
                        conditionExecution.getLeftValue(),
                        this.value))
                .getTestResult();

    }
}
