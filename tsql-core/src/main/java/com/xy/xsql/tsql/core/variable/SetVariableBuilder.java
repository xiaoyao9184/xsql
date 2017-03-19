package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.variable.SetVariable;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class SetVariableBuilder<ParentBuilder>
        extends CodeTreeBuilder<SetVariableBuilder<ParentBuilder>,ParentBuilder,SetVariable> {


    public SetVariableBuilder(SetVariable tar) {
        super(tar);
    }

    public SetVariableBuilder() {
        super(new SetVariable());
    }

    public SetVariableBuilder<ParentBuilder> withLocalVariable(String variable){
        tar.setLocalVariable(e_variable(variable));
        return this;
    }

    public SetVariableBuilder<ParentBuilder> withCompound(Compound compound){
        tar.setCompound(compound);
        return this;
    }

    public SetVariableBuilder<ParentBuilder> withExpression(Expression expression){
        tar.setExpression(expression);
        return this;
    }



}
