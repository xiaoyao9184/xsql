package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * https://msdn.microsoft.com/en-us/library/ms181627.aspx
 * https://msdn.microsoft.com/en-us/library/ms178623.aspx
 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class CommentString implements Element {

    private String string;

    public CommentString(){}

    public CommentString(String string){
        this.string = string;
    }


    @Override
    public String toString(){
        if(this.string.contains("\n")){
            return "/*\n" + this.string + "\n*/";
        }
        return "-- " + this.string;
    }

}
