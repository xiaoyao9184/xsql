package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.column.ColumnDefinitionBuilder;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnDefinition;
import com.xy.xsql.tsql.model.datatypes.table.table.TableTypeDefinition;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * TableTypeDefinitionBuilder
 * Created by xiaoyao9184 on 2017/3/17.
 */
@SuppressWarnings("unused")
public class TableTypeDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableTypeDefinitionBuilder<ParentBuilder>,ParentBuilder,TableTypeDefinition> {

    public TableTypeDefinitionBuilder(TableTypeDefinition tar) {
        super(tar);
    }

    public TableTypeDefinitionBuilder() {
        super(new TableTypeDefinition());
    }

    /**
     * in
     * @return ColumnDefinitionBuilder
     */
    public ColumnDefinitionBuilder<TableTypeDefinitionBuilder<ParentBuilder>> withColumnDefinition(){
        return new ColumnDefinitionBuilder<TableTypeDefinitionBuilder<ParentBuilder>>
                ((ColumnDefinition)
                        initNew(ColumnDefinition::new,
                                target::getList,
                                target::setList))
                .in(this);
    }

    /**
     * in
     * @return TableConstraintBuilder
     */
    public TableConstraintBuilder<TableTypeDefinitionBuilder<ParentBuilder>> withTableConstraint(){
        return new TableConstraintBuilder<TableTypeDefinitionBuilder<ParentBuilder>>
                ((TableConstraint)
                        initNew(TableConstraint::new,
                                target::getList,
                                target::setList))
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
        initAdd(Arrays.asList(columnDefinitions),
                target::getList,
                target::setList);
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
        initAdd(Arrays.asList(columnDefinitions),
                target::getList,
                target::setList);
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
