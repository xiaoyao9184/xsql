package com.xy.xsql.tsql.model.elements.variables;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class LocalVariable implements Expression {

    private String name;

    public LocalVariable() {

    }

    public LocalVariable(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString(){
        if(this.name.startsWith("@")){
            return this.name;
        }
        return "@" + this.name;
    }
}
