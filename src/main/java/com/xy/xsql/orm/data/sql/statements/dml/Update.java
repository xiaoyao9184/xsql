package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Alias;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.List;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms177523.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ WITH <common_table_expression> [...n] ]
 UPDATE
 [ TOP ( expression ) [ PERCENT ] ]
 { { tableName | <object> | rowset_function_limited
 [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
 }
 | @table_variable
 }
 SET
 { column_name = { expression | DEFAULT | NULL }
 | { udt_column_name.{ { property_name = expression
 | field_name = expression }
 | method_name ( argument [ ,...n ] )
 }
 }
 | column_name { .WRITE ( expression , @Offset , @Length ) }
 | @variable = expression
 | @variable = column = expression
 | column_name { += | -= | *= | /= | %= | &= | ^= | |= } expression
 | @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression
 | @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression
 } [ ,...n ]

 [ <OUTPUT Clause> ]
 [ FROM{ <table_source> } [ ,...n ] ]
 [ WHERE { <search_condition>
 | { [ CURRENT OF
 { { [ GLOBAL ] cursor_name }
 | cursor_variable_name
 }
 ]
 }
 }
 ]
 [ OPTION ( <query_hint> [ ,...n ] ) ]
 [ ; ]

 <object> ::=
 {
 [ server_name . database_name . schema_name .
 | database_name .[ schema_name ] .
 | schema_name .
 ]
 table_or_view_name}

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 UPDATE [ database_name . [ schema_name ] . | schema_name . ] table_name
 SET { column_name = { expression | NULL } } [ ,...n ]
 [ FROM from_clause ]
 [ WHERE <search_condition> ]
 [ OPTION ( LABEL = label_name ) ]
 [;]

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Update extends CustomizeSentence {
    //TOP
    private Top top;
    //
    private Alias<Void> tableAlias;
    private TableName tableName;
    //SET
    private List<Set> sets;
    //FROM
    private From from;
    //WHERE
    private Where where;


    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public Alias<Void> getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(Alias<Void> tableAlias) {
        this.tableAlias = tableAlias;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
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


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.UPDATE);

        //[ TOP ( expression ) [ PERCENT ] ]
        b.append(OtherEnum.SPACE)
                .append(top.toElementList(),null);

        /*
        { { table_alias | <object> | rowset_function_limited
             [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
          }
          | @table_variable
        }
         */
        if(this.tableAlias != null){
            b.append(OtherEnum.SPACE)
                    .append(tableAlias);
        } else if(this.tableName != null){
            b.append(OtherEnum.SPACE)
                    .append(tableName);
        }

        //SET { column_name = { expression | NULL } } [ ,...n ]
        b.append(OtherEnum.SPACE)
                .append(GrammarEnum.SET);
        for (Set set: sets) {
            b.append(set.toElementList(),null);
            b.append(OtherEnum.DELIMITER);
        }

        //[ FROM{ <table_source> } [ ,...n ] ]
        if(where != null){
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.FROM)
                    .append(OtherEnum.SPACE)
                    .append(from);
        }

        /*
        [ WHERE { <search_condition>
                | { [ CURRENT OF
            { { [ GLOBAL ] cursor_name }
                           | cursor_variable_name
            }
                    ]
                  }
        }
        ]
        */
        if(where != null){
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.WHERE)
                    .append(OtherEnum.SPACE)
                    .append(where);
        }

        return new BaseElementsSentence(b.build(null));
    }


    /**
     * Set
     */
    public static class Set implements ElementList {
        private Column columnName;
        private Expression expression;
        private boolean useNull = false;




        public Column getColumnName() {
            return columnName;
        }

        public void setColumnName(Column columnName) {
            this.columnName = columnName;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public boolean isUseNull() {
            return useNull;
        }

        public void setUseNull(boolean useNull) {
            this.useNull = useNull;
        }


        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(this.columnName)
                    .append(OperatorEnum.EQUAL)
                    .append(this.useNull ? GrammarEnum.NULL : this.expression)
                    .build(null);
        }
    }
}
