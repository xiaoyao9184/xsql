package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms188047.aspx
 *
 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 [ WHERE <search_condition> ]

 *
 * Created by xiaoyao9184 on 2016/12/20.
 */
public class Where implements ElementList {

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
                .append(OtherEnum.SPACE)
                .append(GrammarEnum.WHERE)
                .append(OtherEnum.SPACE)
                .append(searchCondition)
                .append(OtherEnum.SPACE);
        return b.build();
    }

}
