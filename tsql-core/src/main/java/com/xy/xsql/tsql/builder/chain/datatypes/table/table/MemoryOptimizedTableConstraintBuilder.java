package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet2;

/**
 * MemoryOptimizedTableConstraintBuilder
 * Use
 * in --Memory-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
@SuppressWarnings("unused")
public class MemoryOptimizedTableConstraintBuilder<ParentBuilder>
        extends CodeTreeBuilder<MemoryOptimizedTableConstraintBuilder<ParentBuilder>,ParentBuilder,TableConstraint> {

    public MemoryOptimizedTableConstraintBuilder(TableConstraint tar) {
        super(tar);
    }

    public MemoryOptimizedTableConstraintBuilder() {
        super(new TableConstraint());
    }

    /**
     * set
     * @param constraintName constraint name
     * @return THIS
     */
    public MemoryOptimizedTableConstraintBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    /**
     * set
     * @param constraint Constraint
     * @return THIS
     */
    public MemoryOptimizedTableConstraintBuilder<ParentBuilder> withConstraint(Constraint constraint) {
        target.setConstraint(constraint);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param constraintName constraint name
     * @return THIS
     */
    public MemoryOptimizedTableConstraintBuilder<ParentBuilder> $Constraint(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick in
     * @return HashBucketCountTablePrimaryUniqueBuilder
     */
    public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $PrimaryKey(){
        return new PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick in
     * @return HashBucketCountTablePrimaryUniqueBuilder
     */
    public PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder> $Unique(){
        return new PrimaryUniques.HashBucketCountTablePrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick in
     * @return ReferencesTableForeignBuilder
     */
    public Foreigns.ReferencesTableForeignBuilder<ParentBuilder> $ForeignKey(){
        return new Foreigns.ReferencesTableForeignBuilder<ParentBuilder>
                (initSet2(
                        Foreign::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and());
    }

    /**
     * Quick out
     * @param logicalExpression logicalExpression
     * @return PARENT
     */
    public ParentBuilder $Check(Expression logicalExpression){
        return new CheckBuilder<ParentBuilder>
                (initSet2(
                        Check::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withLogicalExpression(logicalExpression)
                .and();
    }

}
