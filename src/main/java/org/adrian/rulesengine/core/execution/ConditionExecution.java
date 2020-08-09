package org.adrian.rulesengine.core.execution;

import org.adrian.rulesengine.core.Condition;

public class ConditionExecution<S> {
    private final Condition<S, ?, ?> condition;
    private final Object leftValue;

    public static class Builder<S> {
        private Condition<S, ?, ?> condition;
        private Object leftValue;

        public Builder<S> condition(Condition<S, ?, ?> condition) {
            this.condition = condition;
            return this;
        }

        public Builder<S> leftValue(Object leftValue) {
            this.leftValue = leftValue;
            return this;
        }

        public ConditionExecution<S> build() {
            return new ConditionExecution<>(this);
        }
    }

    private ConditionExecution(Builder<S> builder) {
        this.condition = builder.condition;
        this.leftValue = builder.leftValue;
    }
}
