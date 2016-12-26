package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class UnknownString implements Element {

    private String string;
    private boolean useQuote;
    private boolean useNQuote;

    public UnknownString(String string){
        this.string = string;
    }


    public UnknownString withQuote(boolean useQuote) {
        this.useQuote = useQuote;
        return this;
    }

    public UnknownString withNQuote(boolean useNQuote) {
        this.useNQuote = useNQuote;
        return this;
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
