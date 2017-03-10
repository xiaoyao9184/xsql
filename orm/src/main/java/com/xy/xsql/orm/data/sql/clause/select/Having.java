package com.xy.xsql.orm.data.sql.clause.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms180199.aspx
 *

 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 [ HAVING <search condition> ]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class Having implements ElementList {
    //
    private SearchCondition searchCondition;

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }


    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.HAVING)
                .append(searchCondition);
        return b.build();
    }
}
