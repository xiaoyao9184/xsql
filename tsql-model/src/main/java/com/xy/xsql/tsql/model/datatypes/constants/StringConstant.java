package com.xy.xsql.tsql.model.datatypes.constants;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.UUID;

/**
 * Character string constants
 * uniqueidentifier constants(output like Character string)
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class StringConstant implements Constant, Expression {

    private boolean useNQuote;
    private String string;


    public StringConstant() {}

    protected StringConstant(String string){
        this.string = string;
    }

    public boolean isUseNQuote() {
        return useNQuote;
    }

    public void setUseNQuote(boolean useNQuote) {
        this.useNQuote = useNQuote;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }


}
