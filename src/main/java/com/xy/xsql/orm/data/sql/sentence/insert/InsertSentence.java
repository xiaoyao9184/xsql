package com.xy.xsql.orm.data.sql.sentence.insert;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.expression.operator.relational.Group;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.util.CheckUtil;
import com.xy.xsql.orm.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms174335.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ WITH <common_table_expression> [ ,...n ] ]
 INSERT
 {
 [ TOP ( expression ) [ PERCENT ] ]
 [ INTO ]
 { <object> | rowset_function_limited
 [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
 }
 {
 [ ( column_list ) ]
 [ <OUTPUT Clause> ]
 { VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
 | derived_table
 | execute_statement
 | <dml_table_source>
 | DEFAULT VALUES
 }
 }
 }
 [;]

 <object> ::=
 {
 [ server_name . database_name . schema_name .
 | database_name .[ schema_name ] .
 | schema_name .
 ]
 table_or_view_name
 }

 <dml_table_source> ::=
 SELECT <select_list>
 FROM ( <dml_statement_with_output_clause> )
 [AS] table_alias [ ( column_alias [ ,...n ] ) ]
 [ WHERE <search_condition> ]
 [ OPTION ( <query_hint> [ ,...n ] ) ]

 * Created by xiaoyao9184 on 2016/10/15.
 */
public class InsertSentence extends CustomizeSentence {

    //TOP
    private Top top;
    private boolean useInto;
    private Table table;
    //
    private List<Column> columns;
    //{ VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
    private boolean useValues = true;
    private List<Group> groupValues;

    public InsertSentence() {
        super();
    }


    public void withTable(Table table) {
        this.table = table;
    }

    public void withColumn(Column column) {
        if(this.columns == null){
            this.columns = new ArrayList<>();
        }
        this.columns.add(column);
    }

    public void withUseValues(boolean useValues) {
        this.useValues = useValues;
    }

    public void withValues(Expression value) {
        if(this.groupValues == null){
            this.groupValues = new ArrayList<>();
            this.groupValues.add(new Group());
        }
        this.groupValues.get(0).withExpression(value);
    }

    public void withValues(int index, Expression value) {
        if(this.groupValues == null){
            this.groupValues = new ArrayList<>();
        }
        try {
            ListUtil.fill(this.groupValues,index+1,Group.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        this.groupValues.get(index).withExpression(value);
    }

    public Table getTable() {
        return table;
    }

    public List<Column> getColumn() {
        return columns;
    }

    public boolean isUseValues() {
        return useValues;
    }

    public Group getValues() {
        return groupValues.get(0);
    }

    public List<Group> getGroupValues() {
        return groupValues;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(GrammarEnum.INSERT);

        //[ TOP ( expression ) [ PERCENT ] ]
        builder.append(OtherEnum.SPACE)
                .append(top.toElementList(),null);

        //[ INTO ]
        if(useInto){
            builder.append(OtherEnum.SPACE)
                    .append(GrammarEnum.INTO);
        }

        //
        builder.append(OtherEnum.SPACE)
                .append(table);

        //[ ( column_list ) ]
        if(!CheckUtil.isNullOrEmpty(columns)){
            builder.append(OtherEnum.GROUP_START);
            for (Column column: columns) {
                builder.append(column);
                builder.append(OtherEnum.DELIMITER);
            }
            builder.append(OtherEnum.GROUP_END);
        }
        //{ VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
        builder.append(useValues ? GrammarEnum.VALUES : null);
        for (Group group: groupValues) {
            builder.append(group.toElementList(),OtherEnum.DELIMITER);
            builder.append(OtherEnum.SPACE);
        }

        return new BaseElementsSentence(builder.build(null));
    }

}
