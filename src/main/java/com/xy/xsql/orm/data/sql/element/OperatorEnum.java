package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum OperatorEnum implements Element {
    ADDITION("+",Type.Arithmetic),
    SUBTRACTION("-",Type.Arithmetic),
    MULTIPLICATION("*",Type.Arithmetic),
    DIVISION("/",Type.Arithmetic),
    MODULO("%",Type.Arithmetic),

    Assignment("=",Type.Assignment),

    BITWISE_AND("&",Type.Bitwise),
    BITWISE_OR("|",Type.Bitwise),
    BITWISE_XOR("^",Type.Bitwise),

    EQUAL("=",Type.Comparison),
    GREATER(">",Type.Comparison),
    LESS("<",Type.Comparison),
    GREATER_EQUAL(">=",Type.Comparison),
    LESS_EQUAL("<=",Type.Comparison),
    NOT_EQUAL("<>",Type.Comparison),
    NOT_EQUAL_NOT_ISO("!=",Type.Comparison),
    NOT_LESS_NOT_ISO("!<",Type.Comparison),
    NOT_GREATER_NOT_ISO("!>",Type.Comparison),

    Add_EQUALS("+=",Type.Compound),
    Subtract_EQUALS("-=",Type.Compound),
    Multiply_EQUALS("*=",Type.Compound),
    Divide_EQUALS("/=",Type.Compound),
    Modulo_EQUALS ("%=",Type.Compound),
    Bitwise_AND_EQUALS("&=",Type.Compound),
    Bitwise_Exclusive_OR_EQUALS("^=",Type.Compound),
    Bitwise_OR_EQUALS("|=",Type.Compound),

    ALL(Type.Logical),
    AND(Type.Logical),
    ANY(Type.Logical),
    BETWEEN(Type.Logical),
    EXISTS(Type.Logical),
    IN(Type.Logical),
    LIKE(Type.Logical),
    NOT(Type.Logical),
    OR(Type.Logical),
    SOME(Type.Logical),

    //TODO
    ILIKE("ILIKE"),
    //TODO
    CONCAT("||"),

    Scope_Resolution("::",Type.Scope_Resolution),

    String_Concatenation("+",Type.String),
    String_Concatenation_Set("+=",Type.String),

    Positive("+",Type.Unary),
    Negative("-",Type.Unary),
    Bitwise_NOT("~",Type.Unary);

    private String string;
    private Type type;


    OperatorEnum(String string){
        this.string = string;
    }

    OperatorEnum(String string,Type type){
        this.string = string;
        this.type = type;
    }

    OperatorEnum(Type type){
        this.string = this.name();
        this.type = type;
    }

    @Override
    public String toString(){
        return this.string;
    }

    public Type toType(){
        return this.type;
    }

    public enum Type {
        Arithmetic,
        Logical,
        Assignment,
        Scope_Resolution,
        Bitwise,
        Comparison,
        String,
        Compound,
        Unary
    }
}
