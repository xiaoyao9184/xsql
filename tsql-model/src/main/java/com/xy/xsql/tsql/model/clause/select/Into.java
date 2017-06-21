package com.xy.xsql.tsql.model.clause.select;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 *
 *

 [ INTO new_table ]

 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class Into implements Clause {

    private TableName newTable;
    private StringConstant fileGroup;


    public TableName getNewTable() {
        return newTable;
    }

    public void setNewTable(TableName newTable) {
        this.newTable = newTable;
    }

    public StringConstant getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(StringConstant fileGroup) {
        this.fileGroup = fileGroup;
    }


    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.INTO)
                .append(newTable)
                .build();
    }
}
