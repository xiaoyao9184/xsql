package com.xy.xsql.tsql.model.statement.dml;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.CheckUtil;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms189499.aspx
 *

 -- Syntax for SQL Server and Azure SQL Database

 <SELECT statement> ::=
 [ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ]
 <query_expression>
 [ ORDER BY { order_by_expression | column_position [ ASC | DESC ] }
 [ ,...n ] ]
 [ <FOR Clause>]
 [ OPTION ( <query_hint> [ ,...n ] ) ]
 <query_expression> ::=
 { <query_specification> | ( <query_expression> ) }
 [  { UNION [ ALL ] | EXCEPT | INTERSECT }
 <query_specification> | ( <query_expression> ) [...n ] ]
 <query_specification> ::=
 SELECT [ ALL | DISTINCT ]
 [TOP ( expression ) [PERCENT] [ WITH TIES ] ]
 < select_list >
 [ INTO new_table ]
 [ FROM { <table_source> } [ ,...n ] ]
 [ WHERE <search_condition> ]
 [ <GROUP BY> ]
 [ HAVING < search_condition > ]

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Select implements Statement {
    // [ ALL | DISTINCT ]
    private boolean useAll;
    private boolean useDistinct;
    //TOP
    private Top top;
    //< select_list >
    private SelectList selectList;
    //[ INTO new_table ]
    private Unknown newTable;
    //[ FROM { <table_source> } [ ,...n ] ]
    private From from;
    //[ WHERE <search_condition> ]
    private Where where;
    //[ <GROUP BY> ]
    private GroupBy groupBy;
    //[ HAVING < search_condition > ]
    private Having having;

    //[ ORDER BY { order_by_expression | column_position [ ASC | DESC ] }
    //[ ,...n ] ]
    private OrderBy orderBy;
    //[ <FOR Clause>]
    private For forClause;

    public boolean isUseAll() {
        return useAll;
    }

    public void setUseAll(boolean useAll) {
        this.useAll = useAll;
    }

    public boolean isUseDistinct() {
        return useDistinct;
    }

    public void setUseDistinct(boolean useDistinct) {
        this.useDistinct = useDistinct;
    }

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public SelectList getSelectList() {
        return selectList;
    }

    public void setSelectList(SelectList selectList) {
        this.selectList = selectList;
    }

    public Unknown getNewTable() {
        return newTable;
    }

    public void setNewTable(Unknown newTable) {
        this.newTable = newTable;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public Where getWhere() {
        return where;
    }

    public void setWhere(Where where) {
        this.where = where;
    }

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public Having getHaving() {
        return having;
    }

    public void setHaving(Having having) {
        this.having = having;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public For getForClause() {
        return forClause;
    }

    public void setForClause(For forClause) {
        this.forClause = forClause;
    }



    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.SELECT);

        //[ ALL | DISTINCT ]
        b.append(useAll ?
                Keywords.ALL :
                useDistinct ?
                        Keywords.DISTINCT :
                        null);

        //[TOP ( expression ) [PERCENT] [ WITH TIES ] ]
        b.append(top);

        //< select_list >
        b.append(selectList);

        //[ INTO new_table ]
        if (newTable != null) {
            b.append(Keywords.INTO)
                .append(newTable);
        }

        //[ FROM{ <table_source> } [ ,...n ] ]
        b.append(from);

        //[ WHERE <search_condition> ]
        b.append(where);

        //[ <GROUP BY> ]
        b.append(groupBy);

        //[ HAVING < search_condition > ]
        b.append(having);

        //[ ORDER BY { order_by_expression | column_position [ ASC | DESC ] }
        //[ ,...n ] ]
        b.append(orderBy);

        //[ <FOR Clause>]
        b.append(forClause);

        return b.build();
    }

}
