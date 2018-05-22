package com.xy.xsql.tsql.model.datatypes.constants;

/**
 * money constants
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class MoneyConstant
        extends NumberConstant
        implements Constant {

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
