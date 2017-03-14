package com.xy.xsql.tsql.model.element;

import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Unknown implements Block {

    private String string;

    public Unknown(){}

    public Unknown(String string){
        this.string = string;
    }



    @Override
    public String toString(){
        return this.string;
    }

}
