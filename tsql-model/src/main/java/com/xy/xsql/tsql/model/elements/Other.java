package com.xy.xsql.tsql.model.elements;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum Other {
    SPACE(" "),
    DELIMITER(","),
    GROUP_START("("),
    GROUP_END(")"),
    __("_"),
    POINT("."),
    ASTERISK("*"),
    QUOTATION("'"),
    SEMICOLON(";");

    private String string;

    Other(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return this.string;
    }
}
