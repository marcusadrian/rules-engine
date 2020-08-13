package org.adrian.rulesengine.core.execution;

import lombok.NonNull;

public class CombinedConditionExecutionElement<S> {
    private final CombinedConditionExecution<S> combinedConditionExecution;
    private final ConditionExecution<S, ?, ?> conditionExecution;

    public CombinedConditionExecutionElement(@NonNull CombinedConditionExecution<S> combinedConditionExecution) {
        this.combinedConditionExecution = combinedConditionExecution;
        this.conditionExecution = null;
    }

    public CombinedConditionExecutionElement(@NonNull ConditionExecution<S, ?, ?> conditionExecution) {
        this.combinedConditionExecution = null;
        this.conditionExecution = conditionExecution;
    }

    @Override
    public String toString() {
        return this.combinedConditionExecution != null ? this.combinedConditionExecution.toString() :
                this.conditionExecution.toString();
    }
}
