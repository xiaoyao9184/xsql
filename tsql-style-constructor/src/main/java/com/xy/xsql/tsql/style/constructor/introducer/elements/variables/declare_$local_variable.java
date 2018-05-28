package com.xy.xsql.tsql.style.constructor.introducer.elements.variables;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE;
import com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_SET;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;

/**
 * Created by xiaoyao9184 on 2018/5/14.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class declare_$local_variable {

    static com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE DECLARE(
            String local_variable,
            b_DECLARE.k_AS as,
            DataType data_type,
            Expression value){
        return new com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE(){
            {
                withItem()
                        .withLocalVariable(e_variable(local_variable))
                        .withAs()
                        .withDateType(data_type)
                        .withValue(value);
            }
        };
    }
    static com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE DECLARE(
            String local_variable,
           b_DECLARE.k_AS as,
            DataType data_type){
        return new com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE(){
            {
                withItem()
                        .withLocalVariable(e_variable(local_variable))
                        .withAs()
                        .withDateType(data_type);
            }
        };
    }
    static com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE DECLARE(
            String local_variable,
            DataType data_type,
            Expression value){
        return new com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE(){
            {
                withItem()
                        .withLocalVariable(e_variable(local_variable))
                        .withDateType(data_type)
                        .withValue(value);
            }
        };
    }
    static com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE DECLARE(
            String local_variable,
            DataType data_type){
        return new com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE(){
            {
                withItem()
                        .withLocalVariable(e_variable(local_variable))
                        .withDateType(data_type);
            }
        };
    }

    //

    static com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE DECLARE(
            String cursor_variable_name,
            b_SET.k_CURSOR cursor){
        return new com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE(){
            {
                withItem()
                        .withLocalVariable(e_variable(cursor_variable_name))
                        .withCursor(true);
            }
        };
    }

    //TODO table_type_definition

//    static com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE DECLARE(
//            String table_variable_name,
//            AS as,
//            b_Table cursor){
//        return new com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_DECLARE(){
//            {
//                withItem()
//                        .withLocalVariable(e_variable(cursor_variable_name))
//                        .withCursor(true);
//            }
//        };
//    }

    //


    static b_DECLARE.k_AS AS(){
        return null;
    }
}
