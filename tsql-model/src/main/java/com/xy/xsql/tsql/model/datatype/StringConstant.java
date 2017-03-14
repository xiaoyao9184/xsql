package com.xy.xsql.tsql.model.datatype;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class StringConstant implements Expression {

    private boolean useQuote;
    private boolean useNQuote;
    private String string;

    public StringConstant(String string){
        this.string = string;
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
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(this)
                .build();
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
