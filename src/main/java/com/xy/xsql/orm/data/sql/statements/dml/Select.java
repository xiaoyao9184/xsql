package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.clause.select.*;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.orm.util.CheckUtil;

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
public class Select extends CustomizeSentence {
    // [ ALL | DISTINCT ]
    private boolean useAll;
    private boolean useDistinct;
    //TOP
    private Top top;
    //< select_list >
    private SelectList selectList;
    //[ INTO new_table ]
    private String newTable;
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

    public String getNewTable() {
        return newTable;
    }

    public void setNewTable(String newTable) {
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
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.SELECT);

        //[ ALL | DISTINCT ]
        b.append(useAll ?
                GrammarEnum.ALL :
                useDistinct ?
                        GrammarEnum.DISTINCT :
                        null);

        //[TOP ( expression ) [PERCENT] [ WITH TIES ] ]
        b.append(top == null ? null : top);

        //< select_list >
        b.append(selectList);

        //[ INTO new_table ]
        if (!CheckUtil.isNullOrEmpty(newTable)) {
            b.append(GrammarEnum.INTO)
                .append(newTable);
        }

        //[ FROM{ <table_source> } [ ,...n ] ]
        b.append(from == null ? null : from);

        //[ WHERE <search_condition> ]
        b.append(where == null ? null : where);

        //[ <GROUP BY> ]
        b.append(groupBy == null ? null : groupBy);

        //[ HAVING < search_condition > ]
        b.append(having == null ? null : having);

        //[ ORDER BY { order_by_expression | column_position [ ASC | DESC ] }
        //[ ,...n ] ]
        b.append(orderBy == null ? null : orderBy);

        //[ <FOR Clause>]
        b.append(forClause == null ? null : forClause);

        return new BaseElementsSentence(b.build(null));
    }

}
