package org.adrian.rulesengine.core.execution;

import lombok.Getter;
import org.adrian.rulesengine.core.CombinedCondition;
import org.adrian.rulesengine.core.Condition;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

@Getter
public class RuleExecution<S> {

    private final Deque<CombinedConditionExecution<S>> executions = new LinkedList<>();
    private CombinedConditionExecution<S> root;
    private Boolean executionResult;

    public <L, R> ConditionExecution<S, L, R> newConditionExecution(Condition<S, L, R> condition) {
        ConditionExecution<S, L, R> execution = new ConditionExecution<S, L, R>().condition(condition);
        this.executions.getLast().add(execution);
        return execution;
    }

    public CombinedConditionExecution<S> newCombinedConditionExecution(CombinedCondition<S> combinedCondition) {
        CombinedConditionExecution<S> execution = new CombinedConditionExecution<>();
        execution.combinedCondition(combinedCondition);
        CombinedConditionExecution<S> last = this.executions.peekLast();
        if (last != null) {
            last.add(execution);
        } else {
            this.root = execution;
        }
        this.executions.add(execution);
        return execution;
    }

    public RuleExecution<S> executionResult(Boolean executionResult) {
        this.executionResult = executionResult;
        return this;
    }

    public void endCombinedConditionExecution() {
        this.executions.removeLast();
    }

    @Override
    public String toString() {
        return Optional.ofNullable(this.root)
                .map(CombinedConditionExecution::toString)
                .orElse("not executed");
    }
}
