package com.xy.xsql.tsql.core.element.constraint;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.element.constraint.Check;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2017/8/17.
 */
public class CheckBuilder<ParentBuilder>
        extends CodeTreeBuilder<CheckBuilder<ParentBuilder>,ParentBuilder,Check> {

    public CheckBuilder(Check check) {
        super(check);
    }


    public CheckBuilder<ParentBuilder> withUseNotForReplication(boolean useNotForReplication){
        target.setUseNotForReplication(useNotForReplication);
        return this;
    }

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
    public CheckBuilder<ParentBuilder> $NOT_FOR_REPLICATION(){
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