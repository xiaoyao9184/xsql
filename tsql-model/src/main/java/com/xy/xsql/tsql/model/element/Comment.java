package com.xy.xsql.tsql.model.element;

/**
 * https://msdn.microsoft.com/en-us/library/ms181627.aspx
 * https://msdn.microsoft.com/en-us/library/ms178623.aspx
 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class Comment {

    private String string;

    public Comment(){}

    public Comment(String string){
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
