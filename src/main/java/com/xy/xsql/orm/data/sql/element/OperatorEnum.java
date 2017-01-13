package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum OperatorEnum implements Element {

    /**
     * Arithmetic Operators
     * https://msdn.microsoft.com/en-us/library/ms187716.aspx
     */
    ADDITION("+",Type.Arithmetic),
    SUBTRACTION("-",Type.Arithmetic),
    MULTIPLICATION("*",Type.Arithmetic),
    DIVISION("/",Type.Arithmetic),
    MODULO("%",Type.Arithmetic),

    /**
     * Assignment Operator
     * https://msdn.microsoft.com/en-us/library/ms188343.aspx
     */
    Assignment("=",Type.Assignment),

    /**
     * Bitwise Operators
     * https://msdn.microsoft.com/en-us/library/ms176122.aspx
     */
    BITWISE_AND("&",Type.Bitwise),
    BITWISE_AND_EQUALS("&=",Type.Bitwise),
    BITWISE_OR("|",Type.Bitwise),
    BITWISE_OR_EQUALS("|=",Type.Bitwise),
    BITWISE_XOR("^",Type.Bitwise),
    BITWISE_XOR_EQUALS("^=",Type.Bitwise),
    BITWISE_NOT("~",Type.Bitwise),

    /**
     * Comparison Operators
     * https://msdn.microsoft.com/en-us/library/ms188074.aspx
     */
    EQUAL("=",Type.Comparison),
    GREATER(">",Type.Comparison),
    LESS("<",Type.Comparison),
    GREATER_EQUAL(">=",Type.Comparison),
    LESS_EQUAL("<=",Type.Comparison),
    NOT_EQUAL("<>",Type.Comparison),
    NOT_EQUAL_NOT_ISO("!=",Type.Comparison),
    NOT_LESS_NOT_ISO("!<",Type.Comparison),
    NOT_GREATER_NOT_ISO("!>",Type.Comparison),

    /**
     * Compound Operators
     * https://msdn.microsoft.com/en-us/library/cc645922.aspx
     */
    Add_EQUALS("+=",Type.Compound),
    Subtract_EQUALS("-=",Type.Compound),
    Multiply_EQUALS("*=",Type.Compound),
    Divide_EQUALS("/=",Type.Compound),
    Modulo_EQUALS ("%=",Type.Compound),
    Bitwise_AND_EQUALS("&=",Type.Compound),
    Bitwise_Exclusive_OR_EQUALS("^=",Type.Compound),
    Bitwise_OR_EQUALS("|=",Type.Compound),

    /**
     * Logical Operators
     * https://msdn.microsoft.com/en-us/library/ms189773.aspx
     */
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

    /**
     * Scope Resolution Operator
     * https://msdn.microsoft.com/en-us/library/dd206995.aspx
     */
    Scope_Resolution("::",Type.Scope_Resolution),

    /**
     * Set Operators
     * https://msdn.microsoft.com/en-us/library/ff848745.aspx
     */
    EXCEPT("EXCEPT",Type.Set),
    INTERSECT("INTERSECT",Type.Set),
    UNION("UNION",Type.Set),
    UNION_ALL("UNION ALL",Type.Set),

    /**
     * String Operators
     * https://msdn.microsoft.com/en-us/library/ms190301.aspx
     */
    String_Concatenation("+",Type.String),
    String_Concatenation_Set("+=",Type.String),

    /**
     * Unary Operators
     * https://msdn.microsoft.com/en-us/library/ms188400.aspx
     */
    Positive("+",Type.Unary),
    Negative("-",Type.Unary),
    Bitwise_NOT("~",Type.Unary),



    @Deprecated
    ILIKE("ILIKE"),

    @Deprecated
    CONCAT("||");


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
        Set,
        String,
        Compound,
        Unary
    }
}
