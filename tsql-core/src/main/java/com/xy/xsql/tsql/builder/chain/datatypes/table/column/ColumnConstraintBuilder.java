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

/**
 * ColumnConstraintBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/4.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ColumnConstraintBuilder<ParentBuilder>
        extends CodeTreeBuilder<ColumnConstraintBuilder<ParentBuilder>,ParentBuilder,ColumnConstraint> {

    public ColumnConstraintBuilder(ColumnConstraint tar) {
        super(tar);
    }

    public ColumnConstraintBuilder() {
        super(new ColumnConstraint());
    }

    /**
     * set
     * @param constraintName constraint name
     * @return THIS
     */
    public ColumnConstraintBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    /**
     * set
     * @param constraint Constraint
     * @return THIS
     */
    public ColumnConstraintBuilder<ParentBuilder> withConstraint(Constraint constraint) {
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
    public ColumnConstraintBuilder<ParentBuilder> $Constraint(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder> $PrimaryKey(){
        return new PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder> $Unique(){
        return new PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick in
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ParentBuilder> $ForeignKey(){
        return new Foreigns.ColumnForeignBuilder<ParentBuilder>
                (initSet2(
                        Foreign::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and());
    }

    /**
     * Quick in
     * @return CheckBuilder
     */
    public CheckBuilder<ParentBuilder> $Check(){
        return new CheckBuilder<ParentBuilder>
                (initSet2(
                        Check::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and());
    }
}
