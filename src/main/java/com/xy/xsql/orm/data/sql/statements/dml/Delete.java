package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

/**
 *
 * https://msdn.microsoft.com/en-us/library/ms189835.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ WITH <common_table_expression> [ ,...n ] ]
 DELETE
 [ TOP ( expression ) [ PERCENT ] ]
 [ FROM ]
 { { table_alias
 | <object>
 | rowset_function_limited
 [ WITH ( table_hint_limited [ ...n ] ) ] }
 | @table_variable
 }
 [ <OUTPUT Clause> ]
 [ FROM table_source [ ,...n ] ]
 [ WHERE { <search_condition>
 | { [ CURRENT OF
 { { [ GLOBAL ] cursor_name }
 | cursor_variable_name
 }
 ]
 }
 }
 ]
 [ OPTION ( <Query Hint> [ ,...n ] ) ]
 [; ]

 <object> ::=
 {
 [ server_name.database_name.schema_name.
 | database_name. [ schema_name ] .
 | schema_name.
 ]
 table_or_view_name
 }

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 DELETE FROM [database_name . [ schema ] . | schema. ] table_name
 [ WHERE <search_condition> ]
 [ OPTION ( <query_options> [ ,...n ]  ) ]
 [; ]

 *
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class Delete extends CustomizeSentence {
    //TOP
    private Top top;
    //
    private Table table;
    //FROM table_source
    private From from;
    //WHERE
    private Where where;



    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
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
                .append(GrammarEnum.DELETE);

        //[ TOP ( expression ) [ PERCENT ] ]
        b.append(OtherEnum.SPACE)
                .append(getTop().toElementList(),null);

        //[ FROM ]
        b.append(OtherEnum.SPACE)
                .append(GrammarEnum.FROM);

        /*
        { { table_alias
          | <object>
          | rowset_function_limited
          [ WITH ( table_hint_limited [ ...n ] ) ] }
          | @table_variable
        }
         */
        b.append(OtherEnum.SPACE)
                .append(getTable().getAliasName())
                .append(OtherEnum.SPACE);

        //[ FROM table_source [ ,...n ] ]
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
        if( getWhere() != null ){
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.WHERE)
                    .append(OtherEnum.SPACE)
                    .append(getWhere());
        }

        return new BaseElementsSentence(b.build(null));
    }

}
