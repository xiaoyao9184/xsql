package com.xy.xsql.tsql.model.expression;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class KeywordExpression implements Expression {

    private Keywords keywords;

    public KeywordExpression(Keywords keywords){
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return keywords.toString();
    }

    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(this)
                .build();
    }

}
