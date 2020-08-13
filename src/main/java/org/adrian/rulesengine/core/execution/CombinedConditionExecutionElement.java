package org.adrian.rulesengine.core.execution;

public class CombinedConditionExecutionElement<S> {
    private final CombinedConditionExecution<S> combinedConditionExecution;
    private final ConditionExecution<S, ?, ?> conditionExecution;

    public CombinedConditionExecutionElement(CombinedConditionExecution<S> combinedConditionExecution) {
        this.combinedConditionExecution = combinedConditionExecution;
        this.conditionExecution = null;
    }

    public CombinedConditionExecutionElement(ConditionExecution<S, ?, ?> conditionExecution) {
        this.combinedConditionExecution = null;
        this.conditionExecution = conditionExecution;
    }

}
