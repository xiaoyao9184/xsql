package com.xy.xsql.tsql.style.constructor.builder.elements.variables;

import com.xy.xsql.tsql.builder.chain.elements.variables.SelectVariableBuilder;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.elements.variables.SelectVariable;

import static com.xy.xsql.core.ListBuilder.popLastItem;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_SELECT
        extends SelectVariableBuilder<b_SELECT> {

    public b_SELECT() {
        this.in(this);
    }


    public b_SELECT $assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withLocalVariable(i.getLocalVariable())
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $add_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.ADD_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $subtract_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.SUBTRACT_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $multiply_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.MULTIPLY_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $divide_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.DIVIDE_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $modulo_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.MODULO_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $bitwise_and_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.BITWISE_AND_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $bitwise_xor_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

    public b_SELECT $bitwise_or_assign(Expression expression) {
        SelectVariable.Item i = popLastItem(target.getItems());
        return withItem()
                .withCompound(Compound.BITWISE_OR_ASSIGNMENT)
                .withExpression(expression)
                .and()
                .and();
    }

}
