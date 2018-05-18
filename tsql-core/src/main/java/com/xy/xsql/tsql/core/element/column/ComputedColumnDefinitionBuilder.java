package com.xy.xsql.tsql.core.element.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.constraint.CheckBuilder;
import com.xy.xsql.tsql.core.element.constraint.Foreigns;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ComputedColumnDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ComputedColumnDefinition> {

    public ComputedColumnDefinitionBuilder(ComputedColumnDefinition tar) {
        super(tar);
    }

    public ComputedColumnDefinitionBuilder() {
        super(new ComputedColumnDefinition());
    }

    public ComputedColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setName(columnName.getName());
        return this;
    }

    public ComputedColumnDefinitionBuilder<ParentBuilder> withComputedColumnExpression(Expression computedColumnExpression) {
        target.setComputedColumnExpression(computedColumnExpression);
        return this;
    }

    public ComputedColumnDefinitionBuilder<ParentBuilder> withPersistedNotNull(Boolean persistedNotNull) {
        target.setPersistedNotNull(persistedNotNull);
        return this;
    }

    public ComputedColumnDefinitionBuilder<ParentBuilder> withColumnConstraint(ColumnConstraint columnConstraint) {
        target.setConstraint(columnConstraint);
        return this;
    }

    /*
    Quick
     */

    /**
     * Quick set
     * @param computedColumnExpression computedColumnExpression
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> $(Expression computedColumnExpression){
        return withComputedColumnExpression(computedColumnExpression);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> $PERSISTED_NOT_NULL(){
        return withPersistedNotNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> $PERSISTED(){
        return withPersistedNotNull(false);
    }

    /**
     * Quick into
     * @param constraintName constraintName
     * @return ColumnConstraintBuilder
     */
    public ColumnConstraintBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $CONSTRAINT(String constraintName){
        return new ColumnConstraintBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>>
                (initSet(ColumnConstraint::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this)
                .withConstraintName(constraintName);
    }

    /**
     * Quick into
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $PRIMARY_KEY(){
        return $CONSTRAINT(null)
                .$PRIMARY_KEY();
    }

    /**
     * Quick into
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $UNIQUE(){
        return $CONSTRAINT(null)
                .$UNIQUE();
    }

    /**
     * Quick into
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $FOREIGN_KEY(){
        return $CONSTRAINT(null)
                .$FOREIGN_KEY();
    }

    /**
     * Quick inout set referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $REFERENCES(String referencedTableName, String refColumn){
        return $CONSTRAINT(null)
                .$FOREIGN_KEY()
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick inout set schemaName,referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $REFERENCES(String schemaName, String referencedTableName, String refColumn){
        return $CONSTRAINT(null)
                .$FOREIGN_KEY()
                .withSchemaName(schemaName)
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick into
     * @return CheckBuilder
     */
    public CheckBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $CHECK(){
        return $CONSTRAINT(null)
                .$CHECK();
    }

}
