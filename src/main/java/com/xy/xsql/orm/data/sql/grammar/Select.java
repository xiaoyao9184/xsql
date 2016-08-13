package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Select implements Element {
    private boolean distinct;
    private boolean top;
    private int top_count;
    private boolean top_percent;


    public Select addDistinct(){
        this.distinct = true;
        return this;
    }

    public Select addTop(int count, boolean percent){
        this.top = true;
        this.top_count = count;
        this.top_percent = percent;
        return this;
    }

    public String toString(){
        StringBuilder sql = new StringBuilder("SELECT");
        if(distinct){
            sql.append(" DISTINCT");
        }
        if(top){
            sql.append(" TOP ");
            sql.append(top_count);
            if(top_percent){
                sql.append(" PERCENT");
            }
        }
        return sql.append("\n").toString();
    }
}
