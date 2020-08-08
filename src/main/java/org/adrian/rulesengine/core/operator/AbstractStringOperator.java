package org.adrian.rulesengine.core.operator;

abstract class AbstractStringOperator extends AbstractOperator<String, String> {

    protected final boolean ignoreCase;
    protected final boolean ignoreBlanks;

    protected abstract static class AbstractBuilder<T extends AbstractBuilder<T>> {

        private boolean ignoreCase;
        private boolean ignoreBlanks;

        public T ignoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
            return this.self();
        }

        public T ignoreBlanks(boolean ignoreBlanks) {
            this.ignoreBlanks = ignoreBlanks;
            return this.self();
        }

        abstract AbstractStringOperator build();

        protected T self() {
            return (T) this;
        }
    }

    protected AbstractStringOperator(AbstractBuilder<?> builder) {
        this.ignoreCase = builder.ignoreCase;
        this.ignoreBlanks = builder.ignoreBlanks;
    }

    @Override
    protected boolean testIgnoreNull(String left, String right) {
        String l = left;
        String r = right;
        if (this.ignoreBlanks) {
            l = l.trim();
            r = r.trim();
        }
        return this.doTest(l, r);
    }

    abstract protected boolean doTest(String left, String right);

    @Override
    public String getSymbol() {
        String symbol = this.getRawSymbol();
        if (this.ignoreCase) {
            symbol += ".ignoreCase";
        }
        if (this.ignoreBlanks) {
            symbol += ".ignoreBlanks";
        }
        return symbol;
    }

    protected abstract String getRawSymbol();

}
