package com.xy.xsql.tsql.model.element;

import com.xy.xsql.tsql.model.Block;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum Other implements Block {
    SPACE(" "),
    DELIMITER(","),
    GROUP_START("("),
    GROUP_END(")");

    private String string;

    Other(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return this.string;
    }
}
