package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.UnknownString;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
@Deprecated
public class StringExpression implements Expression {

    private boolean useQuote;
    private boolean useNQuote;
    private String string;

    public StringExpression(String string){
        this.string = string;
    }


    public StringExpression withQuote() {
        this.useQuote = useQuote;
        return this;
    }

    public StringExpression withNQuote() {
        this.useNQuote = useNQuote;
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
