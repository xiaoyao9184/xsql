package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.column.ComputedColumnDefinition;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * ComputedColumnDefinitionBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/3/12.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ComputedColumnDefinitionBuilder<ParentBuilder>
        extends CodeTreeBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>,ParentBuilder,ComputedColumnDefinition> {

    public ComputedColumnDefinitionBuilder(ComputedColumnDefinition tar) {
        super(tar);
    }

    public ComputedColumnDefinitionBuilder() {
        super(new ComputedColumnDefinition());
    }

    /**
     * set
     * @param columnName ColumnName
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> withColumnName(ColumnName columnName) {
        target.setName(columnName.getName());
        return this;
    }

    /**
     * set
     * @param computedColumnExpression Expression
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> withComputedColumnExpression(Expression computedColumnExpression) {
        target.setComputedColumnExpression(computedColumnExpression);
        return this;
    }

    /**
     * set
     * @param persistedNotNull persisted not null
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> withPersistedNotNull(Boolean persistedNotNull) {
        target.setPersistedNotNull(persistedNotNull);
        return this;
    }

    /**
     * set
     * @param columnConstraint ColumnConstraint
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> withColumnConstraint(ColumnConstraint columnConstraint) {
        target.setConstraint(columnConstraint);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param computedColumnExpression Expression
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> $(Expression computedColumnExpression){
        return withComputedColumnExpression(computedColumnExpression);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> $PersistedNotNull(){
        return withPersistedNotNull(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public ComputedColumnDefinitionBuilder<ParentBuilder> $Persisted(){
        return withPersistedNotNull(false);
    }

    /**
     * Quick in
     * @param constraintName constraint name
     * @return ColumnConstraintBuilder
     */
    public ColumnConstraintBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $Constraint(String constraintName){
        return new ColumnConstraintBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>>
                (initSet(ColumnConstraint::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this)
                .withConstraintName(constraintName);
    }

    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $PrimaryKey(){
        return $Constraint(null)
                .$PrimaryKey();
    }

    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $Unique(){
        return $Constraint(null)
                .$Unique();
    }

    /**
     * Quick in
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $ForeignKey(){
        return $Constraint(null)
                .$ForeignKey();
    }

    /**
     * Quick inout set referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $References(
            String referencedTableName, String refColumn){
        return $Constraint(null)
                .$ForeignKey()
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick inout set schemaName,referencedTableName
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $References(
            String schemaName, String referencedTableName, String refColumn){
        return $Constraint(null)
                .$ForeignKey()
                .withSchemaName(schemaName)
                .withReferencedTableName(referencedTableName)
                .withRefColumn(refColumn);
    }

    /**
     * Quick in
     * @return CheckBuilder
     */
    public CheckBuilder<ComputedColumnDefinitionBuilder<ParentBuilder>> $Check(){
        return $Constraint(null)
                .$Check();
    }

}
