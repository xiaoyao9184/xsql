package com.xy.xsql.tsql.builder.chain.datatypes.table.column;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.column.ColumnConstraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;

import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * ColumnConstraintBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/4.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ColumnConstraintBuilder<ParentBuilder>
        extends ParentHoldBuilder<ColumnConstraintBuilder<ParentBuilder>,ParentBuilder,ColumnConstraint> {

    public ColumnConstraintBuilder() {
        super(new ColumnConstraint());
    }

    public ColumnConstraintBuilder(ColumnConstraint target) {
        super(target);
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
                (object(target::getConstraint, target::setConstraint)
                        .init(PrimaryUnique::new))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick in
     * @return ColumnPrimaryUniqueBuilder
     */
    public PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder> $Unique(){
        return new PrimaryUniques.ColumnPrimaryUniqueBuilder<ParentBuilder>
                (object(target::getConstraint, target::setConstraint)
                        .init(PrimaryUnique::new))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick in
     * @return ColumnForeignBuilder
     */
    public Foreigns.ColumnForeignBuilder<ParentBuilder> $ForeignKey(){
        return new Foreigns.ColumnForeignBuilder<ParentBuilder>
                (object(target::getConstraint, target::setConstraint)
                        .init(Foreign::new))
                .in(this.and());
    }

    /**
     * Quick in
     * @return CheckBuilder
     */
    public CheckBuilder<ParentBuilder> $Check(){
        return new CheckBuilder<ParentBuilder>
                (object(target::getConstraint, target::setConstraint)
                        .init(Check::new))
                .in(this.and());
    }
}
