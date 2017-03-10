package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.List;

/**
 *
 *

 [ INTO new_table ]

 *
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class Into implements ElementList {

    private TableName newTable;


    public TableName getNewTable() {
        return newTable;
    }

    public void setNewTable(TableName newTable) {
        this.newTable = newTable;
    }


    @Override
    public List<Element> toElementList() {
        return new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.INTO)
                .append(newTable)
                .build();
    }
}
