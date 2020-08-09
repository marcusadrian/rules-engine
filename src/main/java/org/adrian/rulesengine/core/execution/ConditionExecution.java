package org.adrian.rulesengine.core.execution;

import org.adrian.rulesengine.core.Condition;

public class ConditionExecution<S, L, R> {
    private final Condition<S, L, R> condition;
    private final L leftValue;

    public static class Builder<S, L, R> {
        private Condition<S, L, R> condition;
        private L leftValue;

        public Builder<S, L, R> condition(Condition<S, L, R> condition) {
            this.condition = condition;
            return this;
        }

        public Builder<S, L, R> leftValue(L leftValue) {
            this.leftValue = leftValue;
            return this;
        }

        public ConditionExecution<S, L, R> build() {
            return new ConditionExecution<>(this);
        }
    }

    private ConditionExecution(Builder<S, L, R> builder) {
        this.condition = builder.condition;
        this.leftValue = builder.leftValue;
    }
}
