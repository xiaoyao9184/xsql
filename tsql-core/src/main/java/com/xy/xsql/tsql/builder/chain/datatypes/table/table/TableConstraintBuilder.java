package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.CheckBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.Foreigns;
import com.xy.xsql.tsql.builder.chain.datatypes.table.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;

import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * TableConstraintBuilder
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
@SuppressWarnings("unused")
public class TableConstraintBuilder<ParentBuilder>
        extends ParentHoldBuilder<TableConstraintBuilder<ParentBuilder>,ParentBuilder,TableConstraint> {

    public TableConstraintBuilder() {
        super(new TableConstraint());
    }

    public TableConstraintBuilder(TableConstraint target) {
        super(target);
    }

    /**
     * set
     * @param constraintName constraint name
     * @return THIS
     */
    public TableConstraintBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    /**
     * set
     * @param constraint Constraint
     * @return THIS
     */
    public TableConstraintBuilder<ParentBuilder> withConstraint(Constraint constraint) {
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
    public TableConstraintBuilder<ParentBuilder> $Constraint(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick in
     * @return TablePrimaryUniqueBuilder
     */
    public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $PrimaryKey(){
        return new PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder>
                (object(target::getConstraint, target::setConstraint)
                        .init(PrimaryUnique::new))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick in
     * @return TablePrimaryUniqueBuilder
     */
    public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $Unique(){
        return new PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder>
                (object(target::getConstraint, target::setConstraint)
                        .init(PrimaryUnique::new))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick in
     * @return TableForeignBuilder
     */
    public Foreigns.TableForeignBuilder<ParentBuilder> $ForeignKey(){
        return new Foreigns.TableForeignBuilder<ParentBuilder>
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
