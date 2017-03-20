package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.variable.SelectVariable;

import static com.xy.xsql.core.ListBuilder.initNew;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class SelectVariableBuilder<ParentBuilder>
        extends CodeTreeBuilder<SelectVariableBuilder<ParentBuilder>,ParentBuilder,SelectVariable> {


    public SelectVariableBuilder(SelectVariable tar) {
        super(tar);
    }

    public SelectVariableBuilder() {
        super(new SelectVariable());
    }

    public SelectVariableItemBuilder<SelectVariableBuilder<ParentBuilder>> withItem(){
        return new SelectVariableItemBuilder<SelectVariableBuilder<ParentBuilder>>
                (initNew(SelectVariable.Item::new,
                        target::getItems,
                        target::setItems))
                .in(this);
    }


    public class SelectVariableItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<SelectVariableItemBuilder<ParentBuilder>,ParentBuilder,SelectVariable.Item> {

        public SelectVariableItemBuilder(SelectVariable.Item tar) {
            super(tar);
        }

        public SelectVariableItemBuilder<ParentBuilder> withLocalVariable(String variable){
            target.setLocalVariable(e_variable(variable));
            return this;
        }

        public SelectVariableItemBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        public SelectVariableItemBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }
    }

}
