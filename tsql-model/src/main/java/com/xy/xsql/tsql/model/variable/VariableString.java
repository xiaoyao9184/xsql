package com.xy.xsql.tsql.model.variable;

import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class VariableString implements Block {

    private String name;

    public VariableString() {

    }

    public VariableString(String name) {
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
