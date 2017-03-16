package com.xy.xsql.tsql.model.datatype;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class NumberConstant implements Expression {

    private Number number;

    public NumberConstant(Number number){
        this.number = number;
    }

    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(this)
                .build();
    }

    @Override
    public String toString(){
        return number.toString();
    }
}
