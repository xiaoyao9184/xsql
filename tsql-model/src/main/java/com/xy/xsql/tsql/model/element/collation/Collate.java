package com.xy.xsql.tsql.model.element.collation;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

import static com.xy.xsql.tsql.model.Keywords.k;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class Collate implements Clause {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(Keywords.COLLATE)
                .append(name == null ?
                        k(Other.__, Keywords.DATABASE,Keywords.DEFAULT) :
                        new Unknown(name))
                .build();
    }
}
