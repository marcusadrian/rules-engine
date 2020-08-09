package org.adrian.rulesengine.core.execution;

import lombok.Getter;
import org.adrian.rulesengine.core.Condition;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RuleExecution<S> {

    private List<ConditionExecution<S, ?, ?>> conditionExecutions = new ArrayList<>();
    private Boolean executionResult;

    public <L, R> ConditionExecution<S, L, R> newConditionExecution(Condition<S, L, R> condition) {
        ConditionExecution<S, L, R> execution = new ConditionExecution<S, L, R>().condition(condition);
        this.conditionExecutions.add(execution);
        return execution;
    }

    public RuleExecution<S> executionResult(Boolean executionResult) {
        this.executionResult = executionResult;
        return this;
    }
}
