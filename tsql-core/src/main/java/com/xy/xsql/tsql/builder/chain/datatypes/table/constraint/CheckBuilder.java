package com.xy.xsql.tsql.builder.chain.datatypes.table.constraint;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.constraint.Check;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * CheckBuilder
 * Created by xiaoyao9184 on 2017/8/17.
 */
@SuppressWarnings("unused")
public class CheckBuilder<ParentBuilder>
        extends ParentHoldBuilder<CheckBuilder<ParentBuilder>,ParentBuilder,Check> {

    public CheckBuilder() {
        super(new Check());
    }

    public CheckBuilder(Check target) {
        super(target);
    }


    /**
     * set
     * @param useNotForReplication not for replication
     * @return THIS
     */
    public CheckBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
        target.setUseNotForReplication(useNotForReplication);
        return this;
    }

    /**
     * set
     * @param logicalExpression Expression
     * @return THIS
     */
    public CheckBuilder<ParentBuilder> withLogicalExpression(Expression logicalExpression){
        target.setLogicalExpression(logicalExpression);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @return THIS
     */
    public CheckBuilder<ParentBuilder> $NotForReplication(){
        target.setUseNotForReplication(true);
        return this;
    }

    /**
     * Quick set
     * @param logicalExpression logicalExpression
     * @return THIS
     */
    public ParentBuilder $(Expression logicalExpression){
        return withLogicalExpression(logicalExpression)
                .and();
    }
}