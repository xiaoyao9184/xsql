package com.xy.xsql.tsql.core.element.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.element.constraint.CheckBuilder;
import com.xy.xsql.tsql.core.element.constraint.Foreigns;
import com.xy.xsql.tsql.core.element.constraint.PrimaryUniques;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Foreign;
import com.xy.xsql.tsql.model.datatypes.table.constraint.PrimaryUnique;
import com.xy.xsql.tsql.model.datatypes.table.table.TableConstraint;

import static com.xy.xsql.core.FiledBuilder.initSet2;

/**
 * Use
 * in --Disk-Based CREATE TABLE Syntax
 * Created by xiaoyao9184 on 2017/8/17.
 * @param <ParentBuilder>
 */
public class TableConstraintBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableConstraintBuilder<ParentBuilder>,ParentBuilder,TableConstraint> {

    public TableConstraintBuilder(TableConstraint tar) {
        super(tar);
    }

    public TableConstraintBuilder() {
        super(new TableConstraint());
    }


    public TableConstraintBuilder<ParentBuilder> withConstraintName(String constraintName) {
        target.setConstraintName(constraintName);
        return this;
    }

    public TableConstraintBuilder<ParentBuilder> withConstraint(Constraint constraint) {
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
    public TableConstraintBuilder<ParentBuilder> $CONSTRAINT(String constraintName){
        return withConstraintName(constraintName);
    }

    /**
     * Quick into
     * @return TablePrimaryUniqueBuilder
     */
    public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $PRIMARY_KEY(){
        return new PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(true);
    }

    /**
     * Quick into
     * @return TablePrimaryUniqueBuilder
     */
    public PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder> $UNIQUE(){
        return new PrimaryUniques.TablePrimaryUniqueBuilder<ParentBuilder>
                (initSet2(
                        PrimaryUnique::new,
                        target::getConstraint,
                        target::setConstraint))
                .in(this.and())
                .withUsePrimaryKey(false);
    }

    /**
     * Quick into
     * @return TableForeignBuilder
     */
    public Foreigns.TableForeignBuilder<ParentBuilder> $FOREIGN_KEY(){
        return new Foreigns.TableForeignBuilder<ParentBuilder>
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
