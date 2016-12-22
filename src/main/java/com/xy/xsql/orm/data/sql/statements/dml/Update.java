package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.orm.util.ListBuilder;

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

    private Top top;
    private Table tableName;
    private List<Set> sets;
    private From from;
    private Where where;


    public void withWhere(Where where){
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
        b.append(OtherEnum.SPACE)
                .append(tableName);

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




    public class Set implements Expression {
        private Column columnName;
        private Expression expression;
        private boolean useNull = false;


        public Set withExpression(Expression expression) {
            this.expression = expression;
            return this;
        }

        public Set withColumn(Column column) {
            this.columnName = column;
            return this;
        }


        @Override
        public List<Element> toElementList() {
            return new ListBuilder<Element>()
                    .withItem(columnName)
                    .withItem(OtherEnum.SPACE)
                    .withItem(OperatorEnum.EQUAL)
                    .withItem(OtherEnum.SPACE)
                    .withItem(useNull ? GrammarEnum.NULL : expression)
                    .build(null);
        }


    }
}
