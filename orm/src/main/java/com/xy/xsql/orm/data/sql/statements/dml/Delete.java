package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.clause.With;
import com.xy.xsql.orm.data.sql.clause.From;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.clause.Where;
import com.xy.xsql.orm.data.sql.clause.Option;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Alias;
import com.xy.xsql.orm.data.sql.element.info.TableName;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;

import java.util.List;

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
public class Delete extends CustomizeSentence implements ElementList {
    //[ WITH <common_table_expression> [ ,...n ] ]
    private With with;
    //TOP
    private Top top;
    //FROM
    private boolean useForm;
    /*
    { { table_alias
      | <object>
      | rowset_function_limited
      [ WITH ( table_hint_limited [ ...n ] ) ] }
      | @table_variable
    }
     */
    private Alias<Void> tableAlias;
    private TableName tableName;
    //TODO rowset_function_limited
    //TODO @table_variable

    //TODO  [ <OUTPUT Clause> ]

    //[ FROM table_source [ ,...n ] ]
    private From from;
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
    private Where where;
    //TODO CURRENT

    //[ OPTION ( <Query Hint> [ ,...n ] ) ]
    private Option option;



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

    public With getWith() {
        return with;
    }

    public void setWith(With with) {
        this.with = with;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        return new BaseElementsSentence(toElementList());
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();

        //[ WITH <common_table_expression> [ ,...n ] ]
        b.append(this.with);

        //DELETE
        b.append(GrammarEnum.DELETE);

        //[ TOP ( expression ) [ PERCENT ] ]
        b.append(this.top);

        //[ FROM ]
        if(useForm){
            b.append(GrammarEnum.FROM);
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
            b.append(tableAlias);
        } else if(this.tableName != null){
            b.append(tableName);
        }

        //[ FROM table_source [ ,...n ] ]
        b.append(this.from);

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
        b.append(this.where);

        //[ OPTION ( <Query Hint> [ ,...n ] ) ]
        b.append(this.option);

        return b.build();
    }
}
