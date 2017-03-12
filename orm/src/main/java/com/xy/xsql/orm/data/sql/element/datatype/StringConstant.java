package com.xy.xsql.orm.data.sql.element.datatype;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class StringConstant implements Expression {

    private boolean useQuote;
    private boolean useNQuote;
    private String string;

    public StringConstant(String string){
        this.string = string;
    }


    public StringConstant withQuote() {
        this.useQuote = true;
        return this;
    }

    public StringConstant withNQuote() {
        this.useNQuote = true;
        return this;
    }

    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .append(this)
                .build();
    }

    @Override
    public String toString(){
        if(this.useNQuote){
            return "N'" + this.string + "'";
        } else if(this.useQuote){
            return "'" + this.string + "'";
        }
        return this.string;
    }

}
