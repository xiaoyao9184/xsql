package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/12/22.
 */
public enum SpecialLexicalEnum implements Element {
    space(" "),
    double_quote("\""),
    percent("%"),
    ampersand("&"),
    quote("'"),
    left_paren("("),
    right_paren(")"),
    asterisk("*"),
    plus_sign("+"),
    comma(","),
    minus_sign("-"),
    period("."),
    solidus("/"),
    colon(":"),
    semicolon(";"),
    less_than_operator("<"),
    equals_operator("="),
    greater_than_operator(">"),
    question_mark("?"),

    left_bracket("["),
    left_bracket_trigraph("??("),
    right_bracket("]"),
    right_bracket_trigraph("??)"),
    circumflex("^"),
    underscore("_"),
    vertical_bar("|"),
    left_brace("{"),
    right_brace("}");

    private String string;

    SpecialLexicalEnum(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return this.string;
    }
}
