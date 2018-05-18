package com.xy.xsql.tsql.core.element.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.constraint.CheckBuilder;
import com.xy.xsql.tsql.core.element.constraint.Foreigns;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import static com.xy.xsql.core.FiledBuilder.initSet2;

/**
 * Use
 * in --Memory optimized CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class MemoryOptimizedColumnConstraintBuilder<ParentBuilder>
        extends CodeTreeBuilder<MemoryOptimizedColumnConstraintBuilder<ParentBuilder>,ParentBuilder,ColumnConstraint> {

    public MemoryOptimizedColumnConstraintBuilder(ColumnConstraint tar) {
        super(tar);
    }

    public MemoryOptimizedColumnConstraintBuilder() {
        super(new ColumnConstraint());
    }


    public MemoryOptimizedColumnConstraintBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    public MemoryOptimizedColumnConstraintBuilder<ParentBuilder> withConstraint(Constraint constraint) {
        target.setConstraint(constraint);
        return this;
    }

    /*
    Quick
     */

    /**
     * Quick set
     * @param constraintName constraintName
     * @return THIS
     */
    public MemoryOptimizedColumnConstraintBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick in
     * @return HashBucketCountColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> $PRIMARY_KEY(){
        return new PrimaryUniques.HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick in
     * @return HashBucketCountColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
        return new PrimaryUniques.HashBucketCountColumnPrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick in
     * @return ReferencesColumnForeignBuilder
     */
    public Foreigns.ReferencesColumnForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
        return new Foreigns.ReferencesColumnForeignBuilder<ParentBuilder>
                (initSet2(
                        Foreign::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and());
    }

    /**
     * Quick out
     * @param logicalExpression logicalExpression
     * @return ParentBuilder
     */
    public ParentBuilder $CHECK(Expression logicalExpression){
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
