package org.adrian.rulesengine.core.execution;

import lombok.Getter;
import org.adrian.rulesengine.core.Condition;
@Getter
public class ConditionExecution<S, L, R> {
    private Condition<S, L, R> condition;
    private L leftValue;
    private Boolean testResult;

    public ConditionExecution<S, L, R> condition(Condition<S, L, R> condition) {
        this.condition = condition;
        return this;
    }

    public ConditionExecution<S, L, R> leftValue(L leftValue) {
        this.leftValue = leftValue;
        return this;
    }

    public ConditionExecution<S, L, R> testResult(Boolean testResult) {
        this.testResult = testResult;
        return this;
    }
}
