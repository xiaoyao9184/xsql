package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.datatypes.table.table.TableTypeDefinition;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * TableTypeDefinitionBuilder
 * Created by xiaoyao9184 on 2017/3/17.
 */
@SuppressWarnings("unused")
public class TableTypeDefinitionBuilder<ParentBuilder>
        extends ParentHoldBuilder<TableTypeDefinitionBuilder<ParentBuilder>,ParentBuilder,TableTypeDefinition> {

    public TableTypeDefinitionBuilder() {
        super(new TableTypeDefinition());
    }

    public TableTypeDefinitionBuilder(TableTypeDefinition target) {
        super(target);
    }

    /**
     * in
     * @return ColumnDefinitionBuilder
     */
    public ColumnDefinitionBuilder<TableTypeDefinitionBuilder<ParentBuilder>> withColumnDefinition(){
        return new ColumnDefinitionBuilder<TableTypeDefinitionBuilder<ParentBuilder>>
                (list(target::getList, target::setList)
                        .addNew(ColumnDefinition::new))
                .in(this);
    }

    /**
     * in
     * @return TableConstraintBuilder
     */
    public TableConstraintBuilder<TableTypeDefinitionBuilder<ParentBuilder>> withTableConstraint(){
        return new TableConstraintBuilder<TableTypeDefinitionBuilder<ParentBuilder>>
                (list(target::getList, target::setList)
                        .addNew(TableConstraint::new))
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param columnDefinitions ColumnDefinition
     * @return THIS
     */
    public TableTypeDefinitionBuilder<ParentBuilder> withColumnDefinition(ColumnDefinition... columnDefinitions){
        if(CheckUtil.isNullOrEmpty(columnDefinitions)){
            return this;
        }
        list(target::getList, target::setList)
                .addAll(columnDefinitions);
        return this;
    }


    /**
     * Quick set
     * @param columnDefinitions ColumnDefinition
     * @return THIS
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_(ColumnDefinition... columnDefinitions){
        if(CheckUtil.isNullOrEmpty(columnDefinitions)){
            return this;
        }
        list(target::getList, target::setList)
                .addAll(columnDefinitions);
        return this;
    }

    /**
     * Quick inout set TableConstraintBuilder' PrimaryKey and ColumnName
     * @param columnNames columnNames
     * @return THIS
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_PrimaryKey(ColumnName... columnNames){
        List<PrimaryUnique.Column> columnList = Stream.of(columnNames)
                .map(columnName -> {
                    PrimaryUnique.Column column = new PrimaryUnique.Column();
                    column.setColumn(columnName.getName());
                    return column;
                })
                .collect(Collectors.toList());
        return withTableConstraint()
                .$PrimaryKey()
                .withColumn(columnList)
                .and();
    }

    /**
     * Quick inout set TableConstraintBuilder' Unique and ColumnName
     * @param columnNames columnNames
     * @return THIS
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_Unique(ColumnName... columnNames){
        List<PrimaryUnique.Column> columnList = Stream.of(columnNames)
                .map(columnName -> {
                    PrimaryUnique.Column column = new PrimaryUnique.Column();
                    column.setColumn(columnName.getName());
                    return column;
                })
                .collect(Collectors.toList());
        return withTableConstraint()
                .$Unique()
                .withColumn(columnList)
                .and();
    }

    /**
     * Quick inout set TableConstraintBuilder' Unique and ColumnName
     * @param logicalExpression logicalExpression
     * @return THIS
     */
    public TableTypeDefinitionBuilder<ParentBuilder> $_Check(Expression logicalExpression){
        return withTableConstraint()
                .$Check()
                .withLogicalExpression(logicalExpression)
                .and();
    }

}
