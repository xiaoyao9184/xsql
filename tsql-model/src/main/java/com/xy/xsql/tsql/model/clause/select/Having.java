package com.xy.xsql.tsql.model.clause.select;

import com.xy.xsql.tsql.model.clause.Clause;
import com.xy.xsql.tsql.model.clause.SearchCondition;

/**
 * https://msdn.microsoft.com/en-us/library/ms180199.aspx
 *

 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 [ HAVING <search condition> ]

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class Having implements Clause {
    //
    private SearchCondition searchCondition;

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

}
