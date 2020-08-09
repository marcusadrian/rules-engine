package org.adrian.rulesengine.core.operator;

public class StringContains extends AbstractStringOperator {

    protected abstract static class AbstractBuilder<T extends StringContains.AbstractBuilder<T>>
            extends AbstractStringOperator.AbstractBuilder<T> {

    }

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        public StringContains build() {
            return new StringContains(this);
        }
    }

    protected StringContains(AbstractBuilder<?> builder) {
        super(builder);
    }

    @Override
    protected boolean doTest(String left, String right) {
        return left.contains(right);
    }

    @Override
    protected String getRawSymbol() {
        return "contains";
    }
}
