package com.xy.xsql.tsql.core.statement.ddl.create.table;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.core.element.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.element.column.ColumnDefinition;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.ddl.create.table.SimpleCreateTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.*;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class SimpleCreateTableBuilder extends CodeBuilder<SimpleCreateTable> {

    public SimpleCreateTableBuilder(SimpleCreateTable tar) {
        super(tar);
    }

    public SimpleCreateTableBuilder() {
        super(new SimpleCreateTable());
    }

    public SimpleCreateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    public SimpleCreateTableBuilder withColumnDefinition(List<ColumnDefinition> columnDefinitionList){
        target.setColumnDefinitionList(columnDefinitionList);
        return this;
    }

    /*
    Quick
     */

    /**
     * Quick set
     * @param tableName tableName
     * @return THIS
     */
    public SimpleCreateTableBuilder $(TableName tableName) {
        return withTableName(tableName);
    }

    /**
     * Quick set
     * @param columnDefinitions columnDefinitions
     * @return THIS
     */
    public SimpleCreateTableBuilder $(ColumnDefinition... columnDefinitions) {
        initAdd(Stream.of(columnDefinitions)
                .collect(Collectors.toList()),
                this.target::getColumnDefinitionList,
                this.target::setColumnDefinitionList);
        return this;
    }

    /**
     * Quick in
     * @param names column name
     * @return ColumnDefinitionBuilder
     */
    public ColumnDefinitionBuilder<SimpleCreateTableBuilder> $(String... names) {
        return new ColumnDefinitionBuilder<SimpleCreateTableBuilder>
                (initNew(ColumnDefinition::new,
                        target::getColumnDefinitionList,
                        target::setColumnDefinitionList))
                .in(this)
                .withColumnName(c(names));
    }

    /**
     * Quick
     * @return THIS
     */
    public static SimpleCreateTableBuilder CREATE_TABLE(){
        return new SimpleCreateTableBuilder();
    }

    /*
    Quick build
     */

    /**
     * Quick build Create Table
     * @param tableName multipart table name
     * @param columnDefinitions the last is view name
     * @return SimpleCreateTable
     */
    public static SimpleCreateTable CREATE_TABLE(TableName tableName, ColumnDefinition... columnDefinitions){
        return new SimpleCreateTableBuilder()
                .$(tableName)
                .$(columnDefinitions)
                .build();
    }

}
