package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.Table;
import com.xy.xsql.orm.data.sql.expression.operator.relational.Group;
import com.xy.xsql.orm.data.sql.sentence.BaseElementsSentence;
import com.xy.xsql.orm.data.sql.sentence.CustomizeSentence;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.List;

/**
 *
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

 *

 -- External tool only syntax

 INSERT
 {
 [BULK]
 [ database_name . [ schema_name ] . | schema_name . ]
 [ table_name | view_name ]
 ( <column_definition> )
 [ WITH (
 [ [ , ] CHECK_CONSTRAINTS ]
 [ [ , ] FIRE_TRIGGERS ]
 [ [ , ] KEEP_NULLS ]
 [ [ , ] KILOBYTES_PER_BATCH = kilobytes_per_batch ]
 [ [ , ] ROWS_PER_BATCH = rows_per_batch ]
 [ [ , ] ORDER ( { column [ ASC | DESC ] } [ ,...n ] ) ]
 [ [ , ] TABLOCK ]
 ) ]
 }

 [; ] <column_definition> ::=
 column_name <data_type>
 [ COLLATE collation_name ]
 [ NULL | NOT NULL ]

 <data type> ::=
 [ type_schema_name . ] type_name
 [ ( precision [ , scale ] | max ]

 *
 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 INSERT INTO [ database_name . [ schema_name ] . | schema_name . ] table_name
 [ ( column_name [ ,...n ] ) ]
 {
 VALUES ( { NULL | expression } )
 | SELECT <select_criteria>
 }
 [ OPTION ( <query_option> [ ,...n ] ) ]
 [;]

 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class Insert extends CustomizeSentence {

    //TOP
    private Top top;
    //INTO
    private boolean useInto;
    //
    private Table table;
    //( column_list )
    private List<Column> columns;
    //VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
    private boolean useValues = true;
    private List<Group> groupValues;


    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public boolean isUseInto() {
        return useInto;
    }

    public void setUseInto(boolean useInto) {
        this.useInto = useInto;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public boolean isUseValues() {
        return useValues;
    }

    public void setUseValues(boolean useValues) {
        this.useValues = useValues;
    }

    public List<Group> getGroupValues() {
        return groupValues;
    }

    public void setGroupValues(List<Group> groupValues) {
        this.groupValues = groupValues;
    }


    @Override
    public BaseElementsSentence toBaseElementsSentence() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.INSERT);

        //[ TOP ( expression ) [ PERCENT ] ]
        b.append(OtherEnum.SPACE)
                .append(getTop().toElementList(),null);

        //[ INTO ]
        if(isUseInto()){
            b.append(OtherEnum.SPACE)
                    .append(GrammarEnum.INTO);
        }

        //
        b.append(OtherEnum.SPACE)
                .append(getTable());

        //[ ( column_list ) ]
        if(!CheckUtil.isNullOrEmpty(getColumns())){
            b.append(OtherEnum.GROUP_START);
            for (Column column: getColumns()) {
                b.append(column);
                b.append(OtherEnum.DELIMITER);
            }
            b.append(OtherEnum.GROUP_END);
        }
        //VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
        b.append(isUseValues() ? GrammarEnum.VALUES : null);
        for (Group group: getGroupValues()) {
            b.append(group.toElementList(),OtherEnum.DELIMITER);
            b.append(OtherEnum.SPACE);
        }

        return new BaseElementsSentence(b.build(null));
    }
}
