package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterConstraint;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class AlterConstraintBuilder<ParentBuilder>
        extends CodeTreeBuilder<AlterConstraintBuilder<ParentBuilder>,ParentBuilder,AlterConstraint> {

    public AlterConstraintBuilder(AlterConstraint target) {
        super(target);
    }

    public AlterConstraintBuilder<ParentBuilder> withUseWith(boolean useWith){
        target.setUseWith(useWith);
        return this;
    }

    public AlterConstraintBuilder<ParentBuilder> withUseCheck(boolean useCheck){
        target.setUseCheck(useCheck);
        return this;
    }

    public AlterConstraintBuilder<ParentBuilder> withConstraintNames(List<String> constraintNames){
        target.setConstraintNames(constraintNames);
        return this;
    }

    public AlterConstraintBuilder<ParentBuilder> withConstraintNames(String... constraintNames){
        target.setConstraintNames(Arrays.asList(constraintNames));
        return this;
    }

    /*
    Quick
     */

    public AlterConstraintBuilder<ParentBuilder> $WITH(){
        return withUseWith(true);
    }

    public AlterConstraintBuilder<ParentBuilder> $CHECK(){
        return withUseCheck(true);
    }

    public AlterConstraintBuilder<ParentBuilder> $NOCHECK(){
        return withUseCheck(false);
    }

    public AlterConstraintBuilder<ParentBuilder> $CONSTRAINT_ALL(){
        return this;
    }

    public AlterConstraintBuilder<ParentBuilder> $CONSTRAINT(String... constraintNames){
        return withConstraintNames(constraintNames);
    }

}
