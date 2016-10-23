package com.xy.xsql.orm.data.sql.relationship;

import com.xy.xsql.orm.data.sql.Relationship;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum Relationships {

    Equal(new Equal()),
    In(new In());


    private Relationship relationship;

    private Relationships(Relationship relationship){
        this.relationship = relationship;
    }

    public Relationship toElement(){
        return this.relationship;
    }

}
