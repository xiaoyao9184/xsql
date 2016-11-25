package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum OperatorEnum implements Element {
    ADDITION("+"),
    BITWISE_AND("&"),
    BITWISE_OR("|"),
    BITWISE_XOR("^"),
    CONCAT("||"),
    DIVISION("/"),
    MODULO("%"),
    MULTIPLICATION("*"),
    SUBTRACTION("-"),

    AND("AND"),
    OR("OR"),

    EQUAL("="),
    NOT_EQUAL("<>"),
    GREATER(">"),
    GREATER_EQUAL(">="),
    LESS("<"),
    LESS_EQUAL("<="),

    BETWEEN("BETWEEN"),
    IN("IN"),
    LIKE("LIKE"),
    ILIKE("ILIKE"),
    NOT("NOT"), 
    EXISTS("EXISTS"), 
    IS("IS"), 
    NULL("NULL"), ESCAPE("ESCAPE");

    private String string;

    OperatorEnum(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return this.string;
    }
}
