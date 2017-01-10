package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Alias;
import com.xy.xsql.orm.data.sql.element.info.TableName;
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
    //FROM
    private boolean useForm;
    //
    private Alias<Alias> tableAlias;
    private TableName tableName;
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

    public boolean isUseForm() {
        return useForm;
    }

    public void setUseForm(boolean useForm) {
        this.useForm = useForm;
    }

    public Alias<Alias> getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(Alias<Alias> tableAlias) {
        this.tableAlias = tableAlias;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
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
        if(useForm){
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.FROM);
        }

        /*
        { { table_alias
          | <object>
          | rowset_function_limited
          [ WITH ( table_hint_limited [ ...n ] ) ] }
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
