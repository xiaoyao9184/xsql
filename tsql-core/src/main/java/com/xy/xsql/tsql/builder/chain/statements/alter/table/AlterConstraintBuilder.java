package com.xy.xsql.tsql.builder.chain.statements.alter.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.statements.alter.table.AlterConstraint;

import java.util.Arrays;
import java.util.List;

/**
 * AlterConstraintBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AlterConstraintBuilder<ParentBuilder>
        extends ParentHoldBuilder<AlterConstraintBuilder<ParentBuilder>,ParentBuilder,AlterConstraint> {

    public AlterConstraintBuilder() {
        super(new AlterConstraint());
    }

    public AlterConstraintBuilder(AlterConstraint target) {
        super(target);
    }

    /**
     * set
     * @param useWith with
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> withUseWith(boolean useWith){
        target.setUseWith(useWith);
        return this;
    }

    /**
     * set
     * @param useCheck check
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> withUseCheck(boolean useCheck){
        target.setUseCheck(useCheck);
        return this;
    }

    /**
     * set
     * @param constraintNames constraint name
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> withConstraintNames(List<String> constraintNames){
        target.setConstraintNames(constraintNames);
        return this;
    }

    /**
     * set
     * @param constraintNames constraint name
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> withConstraintNames(String... constraintNames){
        target.setConstraintNames(Arrays.asList(constraintNames));
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> $With(){
        return withUseWith(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> $Check(){
        return withUseCheck(true);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> $Nocheck(){
        return withUseCheck(false);
    }

    /**
     * Quick set
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> $ConstraintAll(){
        return this;
    }

    /**
     * Quick set
     * @param constraintNames constraint name
     * @return THIS
     */
    public AlterConstraintBuilder<ParentBuilder> $Constraint(String... constraintNames){
        return withConstraintNames(constraintNames);
    }

}
