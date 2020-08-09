package org.adrian.rulesengine.core.execution;

import org.adrian.rulesengine.core.Condition;

public class RuleExecution<S> {

    private ConditionExecution<S> currentConditionExecution;
    public void startCondition(Condition<S,?,?> condition) {
        this.currentConditionExecution = new ConditionExecution.Builder<S>()
                .condition(condition)
                .build();
    }
}
