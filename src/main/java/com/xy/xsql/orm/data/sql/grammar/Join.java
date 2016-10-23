package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Join implements Element {

    private JoinType type;

    public Join() {

    }

    public Join inner(){
        this.type = JoinType.INNER;
        return this;
    }

    public Join left(){
        this.type = JoinType.LEFT;
        return this;
    }

    public Join right(){
        this.type = JoinType.RIGHT;
        return this;
    }

    public Join full(){
        this.type = JoinType.FULL;
        return this;
    }


    public String toString(){
        return this.type.name().toUpperCase() + " JOIN";
    }

    /**
     * Type
     */
    public enum JoinType {
        INNER,
        LEFT,
        RIGHT,
        FULL
    }
}
