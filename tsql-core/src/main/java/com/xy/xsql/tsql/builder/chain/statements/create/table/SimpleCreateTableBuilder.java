package com.xy.xsql.tsql.builder.chain.statements.create.table;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.statements.create.table.SimpleCreateTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;

/**
 * SimpleCreateTableBuilder
 * Created by xiaoyao9184 on 2017/8/4.
 */
@SuppressWarnings("unused")
public class SimpleCreateTableBuilder extends CodeBuilder<SimpleCreateTable> {

    public SimpleCreateTableBuilder(SimpleCreateTable target) {
        super(target);
    }

    public SimpleCreateTableBuilder() {
        super(new SimpleCreateTable());
    }

    /**
     * set
     * @param tableName TableName
     * @return THIS
     */
    public SimpleCreateTableBuilder withTableName(TableName tableName){
        target.setTableName(tableName);
        return this;
    }

    /**
     * set
     * @param columnDefinitionList ColumnDefinition
     * @return THIS
     */
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
        list(target::getColumnDefinitionList, target::setColumnDefinitionList)
                .addAll(columnDefinitions);
        return this;
    }

    /**
     * Quick in
     * @param names column name
     * @return ColumnDefinitionBuilder
     */
    public ColumnDefinitionBuilder<SimpleCreateTableBuilder> $(String... names) {
        return new ColumnDefinitionBuilder<SimpleCreateTableBuilder>
                (list(target::getColumnDefinitionList, target::setColumnDefinitionList)
                        .addNew(ColumnDefinition::new))
                .in(this)
                .withColumnName(c(names));
    }


}
