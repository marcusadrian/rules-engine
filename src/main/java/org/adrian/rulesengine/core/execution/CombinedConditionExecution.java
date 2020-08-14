package org.adrian.rulesengine.core.execution;

import lombok.Getter;
import org.adrian.rulesengine.core.CombinedCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombinedConditionExecution<S> {
    private CombinedCondition<S> combinedCondition;
    @Getter
    private Boolean testResult;
    @Getter
    private List<CombinedConditionExecutionElement<S>> elements = new ArrayList<>();

    public CombinedConditionExecution<S> combinedCondition(CombinedCondition<S> combinedCondition) {
        this.combinedCondition = combinedCondition;
        return this;
    }

    public CombinedConditionExecution<S> testResult(Boolean testResult) {
        this.testResult = testResult;
        return this;
    }

    public void add(ConditionExecution<S, ?, ?> conditionExecution) {
        this.elements.add(new CombinedConditionExecutionElement<>(conditionExecution));
    }

    public void add(CombinedConditionExecution<S> combinedConditionExecution) {
        this.elements.add(new CombinedConditionExecutionElement<>(combinedConditionExecution));
    }

    @Override
    public String toString() {
        return String.format("%s %s -> %s",
                Optional.ofNullable(this.combinedCondition)
                        .map(CombinedCondition::getCombinator)
                        .orElse(null),
                this.elements,
                this.testResult);
    }
}
