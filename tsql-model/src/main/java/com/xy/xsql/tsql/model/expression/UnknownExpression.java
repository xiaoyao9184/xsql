package com.xy.xsql.tsql.model.expression;

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

}
