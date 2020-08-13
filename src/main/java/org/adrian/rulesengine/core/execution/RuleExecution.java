package org.adrian.rulesengine.core.execution;

import lombok.Getter;
import org.adrian.rulesengine.core.CombinedCondition;
import org.adrian.rulesengine.core.Condition;

import java.util.Deque;
import java.util.LinkedList;

@Getter
public class RuleExecution<S> {

    // private List<ConditionExecution<S, ?, ?>> conditionExecutions = new ArrayList<>();
    private Deque<CombinedConditionExecution<S>> executions = new LinkedList<>();
    private final CombinedConditionExecution<S> root = new CombinedConditionExecution<>();
    private Boolean executionResult;

    public RuleExecution() {
        this.executions.add(this.root);
    }

    public <L, R> ConditionExecution<S, L, R> newConditionExecution(Condition<S, L, R> condition) {
        ConditionExecution<S, L, R> execution = new ConditionExecution<S, L, R>().condition(condition);
        this.executions.getLast().add(execution);
        return execution;
    }

    public CombinedConditionExecution<S> newCombinedConditionExecution(CombinedCondition<S> combinedCondition) {
        CombinedConditionExecution<S> execution = new CombinedConditionExecution<>();
        this.executions.getLast().add(execution);
        return execution;
    }

    public RuleExecution<S> executionResult(Boolean executionResult) {
        this.executionResult = executionResult;
        return this;
    }

    public void endCombinedConditionExecution() {
        this.executions.removeLast();
    }
}
