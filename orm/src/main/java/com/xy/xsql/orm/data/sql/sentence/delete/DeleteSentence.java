package com.xy.xsql.orm.data.sql.sentence.delete;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.tsql.model.clause.Top;

import java.util.ArrayList;
import java.util.List;

/**
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
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class DeleteSentence extends CustomizeSentence {
    //TOP
    private Top top;
    //
    private Table table;
    //FROM table_source
    private List<Table> tables;
    //WHERE
    private Expression where;


    public DeleteSentence() {
        super();
    }

    public void withTop(Top top){
        this.top = top;
    }

    public void withTable(Table table) {
        if(this.tables == null){
            this.tables = new ArrayList<>();
        }
        this.tables.add(table);
    }

    public void withFromTable(Table table) {
        this.table = table;
    }

    public void withWhere(Expression where) {
        this.where = where;
    }

    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.DELETE);

        //[ TOP ( expression ) [ PERCENT ] ]
        builder.append(OtherEnum.SPACE)
                .append(top.toElementList(),null);

        //[ FROM ]
        builder.append(OtherEnum.SPACE)
                .append(GrammarEnum.FROM);

        /*
        { { table_alias
          | <object>
          | rowset_function_limited
          [ WITH ( table_hint_limited [ ...n ] ) ] }
          | @table_variable
        }
         */
        builder.append(OtherEnum.SPACE)
                .append(table.getAliasName())
                .append(OtherEnum.SPACE);

        //[ FROM table_source [ ,...n ] ]
        if(tables != null && tables.size() > 0){
            builder.append(OtherEnum.SPACE)
                    .append(GrammarEnum.FROM);
            int i = 0;
            for(Table t : tables){
                if(i != 0){
                    builder.append(OtherEnum.DELIMITER);
                }
                builder.append(t.getFullName());
                i++;
            }
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
        if( where != null ){
            builder.append(OtherEnum.SPACE)
                    .append(GrammarEnum.WHERE)
                    .append(OtherEnum.SPACE)
                    .append(where);
        }

        return new BaseElementsSentence(builder.build(null));
    }

    @Override
    public String toString() {
        return toBaseElementsSentence().toString();
    }
}
