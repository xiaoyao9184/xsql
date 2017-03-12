package com.xy.xsql.orm.data.sql.statements.dml;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.Top;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.info.Column;
import com.xy.xsql.orm.data.sql.element.info.GroupList;
import com.xy.xsql.orm.data.sql.element.info.TableName;
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
    private TableName tableName;
    //( column_list )
    private List<Column> columns;
    //VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]
    private GroupList<Value> valueGroupList;


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

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public GroupList<Value> getValueGroupList() {
        return valueGroupList;
    }

    public void setValueGroupList(GroupList<Value> valueGroupList) {
        this.valueGroupList = valueGroupList;
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

        /*
        { <object> | rowset_function_limited
          [ WITH ( <Table_Hint_Limited> [ ...n ] ) ]
        }
         */
        b.append(OtherEnum.SPACE)
                .append(tableName);

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
        b.append(GrammarEnum.VALUES)
                .append(this.valueGroupList);

        return new BaseElementsSentence(b.build(null));
    }


    /**
     * Value
     */
    @Deprecated
    public static class Value implements ElementList {
        //{ DEFAULT | NULL | expression }
        private boolean useDefault = false;
        private boolean useNull = false;
        private Expression expression;


        public boolean isUseDefault() {
            return useDefault;
        }

        public void setUseDefault(boolean useDefault) {
            this.useDefault = useDefault;
        }

        public boolean isUseNull() {
            return useNull;
        }

        public void setUseNull(boolean useNull) {
            this.useNull = useNull;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }


        @Override
        public List<Element> toElementList() {
            return new ListElementBuilder()
                    .withDelimiter(OtherEnum.SPACE)
                    .append(this.useDefault ?
                                GrammarEnum.DEFAULT :
                                this.useNull ?
                                        GrammarEnum.NULL :
                                        this.expression)
                    .build(null);
        }
    }
}
