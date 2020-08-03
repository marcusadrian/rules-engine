package org.adrian.rulesengine.core.operator;

abstract class AbstractStringOperator extends AbstractOperator<String, String> {

    protected boolean ignoreCase;
    protected boolean ignoreBlanks;

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
