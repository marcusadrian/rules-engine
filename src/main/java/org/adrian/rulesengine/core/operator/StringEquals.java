package org.adrian.rulesengine.core.operator;

public class StringEquals extends AbstractOperator<String, String> {

    private boolean ignoreCase;
    private boolean ignoreBlanks;

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

    private boolean doTest(String left, String right) {
        return this.ignoreCase ? left.equalsIgnoreCase(right) : left.equals(right);
    }

    @Override
    public String getSymbol() {
        String symbol = "eq";
        if (this.ignoreCase) {
            symbol += ".ignoreCase";
        }
        if (this.ignoreBlanks) {
            symbol += ".ignoreBlanks";
        }
        return symbol;
    }
}
