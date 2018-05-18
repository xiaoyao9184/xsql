package com.xy.xsql.tsql.model.datatypes.constants;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.UUID;

/**
 * Character string constants
 * uniqueidentifier constants(output like Character string)
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class StringConstant implements Constant, Expression {

    private boolean useQuote;
    private boolean useNQuote;
    private String string;


    public StringConstant() {
    }

    public StringConstant(String string){
        this.string = string;
    }

    public StringConstant(UUID uuid){
        this.string = uuid.toString().toUpperCase();
        this.useQuote = true;
    }


    public boolean isUseQuote() {
        return useQuote;
    }

    public void setUseQuote(boolean useQuote) {
        this.useQuote = useQuote;
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


    public StringConstant withQuote() {
        this.useQuote = true;
        return this;
    }

    public StringConstant withNQuote() {
        this.useNQuote = true;
        return this;
    }

    @Override
    public String toString(){
        if(this.useNQuote){
            return "N'" + this.string + "'";
        } else if(this.useQuote){
            return "'" + this.string + "'";
        }
        return this.string;
    }

}
