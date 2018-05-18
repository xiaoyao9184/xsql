package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;

import static com.xy.xsql.core.FiledBuilder.initSet2;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class ColumnConstraintBuilder<ParentBuilder>
        extends CodeTreeBuilder<ColumnConstraintBuilder<ParentBuilder>,ParentBuilder,ColumnConstraint> {

    public ColumnConstraintBuilder(ColumnConstraint tar) {
        super(tar);
    }

    public ColumnConstraintBuilder() {
        super(new ColumnConstraint());
    }


    public ColumnConstraintBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    public ColumnConstraintBuilder<ParentBuilder> withConstraint(Constraint constraint) {
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
    public ColumnConstraintBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick into
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder> $PRIMARY_KEY(){
        return new PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick into
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
        return new PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick into
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
        return new Foreigns.ColumnForeignBuilder<ParentBuilder>
                (initSet2(
                        Foreign::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and());
    }

    /**
     * Quick into
     * @return CheckBuilder
     */
    public CheckBuilder<ParentBuilder> $CHECK(){
        return new CheckBuilder<ParentBuilder>
                (initSet2(
                        Check::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and());
    }
}
