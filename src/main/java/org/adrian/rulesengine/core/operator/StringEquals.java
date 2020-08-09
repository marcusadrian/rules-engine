package org.adrian.rulesengine.core.operator;

public class StringEquals extends AbstractStringOperator {

    private final boolean acceptNull;

    protected abstract static class AbstractBuilder<T extends StringEquals.AbstractBuilder<T>>
            extends AbstractStringOperator.AbstractBuilder<T> {

        private boolean acceptNull;

        public T acceptNull() {
            this.acceptNull = true;
            return this.self();
        }
    }

    public static class Builder extends AbstractBuilder<Builder> {

        @Override
        public StringEquals build() {
            return new StringEquals(this);
        }
    }

    protected StringEquals(AbstractBuilder<?> builder) {
        super(builder);
        this.acceptNull = builder.acceptNull;
    }

    @Override
    public boolean test(String left, String right) {
        if (this.acceptNull && left == null && right == null) {
            return true;
        }
        return super.test(left, right);
    }

    @Override
    protected String getRawSymbol() {
        return "eq";
    }

    @Override
    protected boolean doTest(String left, String right) {
        return this.ignoreCase ? left.equalsIgnoreCase(right) : left.equals(right);
    }
}
