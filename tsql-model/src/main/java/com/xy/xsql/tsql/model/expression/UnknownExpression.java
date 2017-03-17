package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * TODO maybe use Unknown to replace
 * Created by xiaoyao9184 on 2017/3/15.
 */
public class UnknownExpression implements Expression {

    private String string;

    public UnknownExpression(String string){
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(this)
                .build();
    }
}
