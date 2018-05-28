package com.xy.xsql.tsql.style.constructor.builder.elements.variables;

import com.xy.xsql.tsql.builder.chain.elements.variables.DeclareVariableBuilder;
import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_SELECT;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_DECLARE extends DeclareVariableBuilder<b_DECLARE> {

    public b_DECLARE() {
        this.in(this);
    }

    /*
    Item
     */

    public b_DECLARE $$(
            String local_variable,
            b_SELECT.b_AS as,
            DataType data_type,
            Expression value){
        return withItem()
                .withLocalVariable(e_variable(local_variable))
                .withAs()
                .withDateType(data_type)
                .withValue(value)
                .and()
            .and();
    }
    public b_DECLARE $$(
            String local_variable,
            b_SELECT.b_AS as,
            DataType data_type){
        return withItem()
                .withLocalVariable(e_variable(local_variable))
                .withAs()
                .withDateType(data_type)
                .and()
            .and();
    }
    public b_DECLARE $$(
            String local_variable,
            DataType data_type,
            Expression value){
        return withItem()
                .withLocalVariable(e_variable(local_variable))
                .withDateType(data_type)
                .withValue(value)
                .and()
            .and();
    }
    public b_DECLARE $$(
            String local_variable,
            DataType data_type){
        return withItem()
                .withLocalVariable(e_variable(local_variable))
                .withDateType(data_type)
                .and()
            .and();
    }

    //

    public b_DECLARE $$(
            String cursor_variable_name,
            b_SET.k_CURSOR cursor){
        return withItem()
                .withLocalVariable(e_variable(cursor_variable_name))
                .withCursor(true)
                .and()
            .and();
    }


    public static class k_AS {}
}
