package com.xy.xsql.tsql.model.clause;

/**
 * https://msdn.microsoft.com/en-us/library/ms188047.aspx
 *
 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 [ WHERE <search_condition> ]

 *
 * Created by xiaoyao9184 on 2016/12/20.
 */
public class Where implements Clause {

    private SearchCondition searchCondition;

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(SearchCondition searchCondition) {
        this.searchCondition = searchCondition;
    }

}
