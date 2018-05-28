package com.xy.xsql.tsql.style.constructor.introducer.elements.variables;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_SELECT;
import com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_SET;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2018/5/14.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class set_$local_variable {

    static b_SET SET(
            String local_variable,
            Expression expression){
        return new b_SET(){
            {
                withLocalVariable(local_variable);
                withExpression(expression);
            }
        };
    }
    static b_SET SET(
            String local_variable){
        return new b_SET(){
            {
                withLocalVariable(local_variable);
            }
        };
    }


    //

    static b_SET SET(
            String cursor_variable,
            String cursor_name){
        return new b_SET(){
            {
                withLocalVariable(cursor_variable);
            }
        };
    }
    static b_SET SET(
            String cursor_variable,
            b_SET.k_CURSOR cursor,
            b_SET.k_FORWARD_ONLY forward_only,
            b_SET.k_STATIC static_,
            b_SET.k_READ_ONLY read_only,
            b_SET.k_TYPE_WARNING type_warning,
            b_SET.b_FOR_select_statement for_select_statement,
            b_SET.k_FOR for_){
        return new b_SET(){
            {
                withLocalVariable(cursor_variable);
            }
        };
    }

    //

    static b_SET.b_FOR_select_statement FOR(
            b_SELECT select_statement){
        return new b_SET.b_FOR_select_statement(){
            {
                this.target = select_statement.build();
            }
        };
    }

    //

    static b_SET.k_FOR FOR(
            b_SET.k_READ_ONLY read_only){
        return new b_SET.k_FOR(){
            {

            }
        };
    }
    static b_SET.k_FOR FOR(
            b_SET.k_UPDATE update){
        return new b_SET.k_FOR(){
            {

            }
        };
    }
    static b_SET.k_FOR FOR(
            b_SET.k_UPDATE update,
            b_SET.b_OF of){
        return new b_SET.k_FOR(){
            {
                    of.build();
            }
        };
    }

    //

    static b_SET.b_OF OF(
            String... column_name){
        return new b_SET.b_OF(){
            {
                this.target.addAll(
                        Arrays.stream(column_name)
                                .map(ColumnName::new)
                                .collect(Collectors.toList())
                );
            }
        };
    }

}
