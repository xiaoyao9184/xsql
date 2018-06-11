package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.datatype.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface DataTypeFunctions {


    static DataLength f_datalength(
            Expression expression){
        DataLength f = new DataLength();
        f.setExpression(expression);
        return f;
    }
    static Ident_Current f_ident_current(
            Expression tableName){
        Ident_Current f = new Ident_Current();
        f.setTableName(tableName);
        return f;
    }
    static Ident_Incr f_ident_incr(
            Expression tableOrView){
        Ident_Incr f = new Ident_Incr();
        f.setTableOrView(tableOrView);
        return f;
    }
    static Ident_Seed f_ident_seed(
            StringConstant tableOrView){
        Ident_Seed f = new Ident_Seed();
        f.setTableOrView(tableOrView);
        return f;
    }
    static Identity f_identity(
            DataType dataType,
            Expression seed,
            Expression increment,
            Expression column_name){
        Identity f = new Identity();
        f.setDataType(dataType);
        f.setSeed(seed);
        f.setIncrement(increment);
        f.setColumn_name(column_name);
        return f;
    }
    static Identity f_identity(
            DataType dataType,
            Expression seed,
            Expression increment){
        Identity f = new Identity();
        f.setDataType(dataType);
        f.setSeed(seed);
        f.setIncrement(increment);
        return f;
    }
    static Identity f_identity(
            DataType dataType,
            Expression column_name){
        Identity f = new Identity();
        f.setDataType(dataType);
        f.setColumn_name(column_name);
        return f;
    }
    static Identity f_identity(
            DataType dataType){
        Identity f = new Identity();
        f.setDataType(dataType);
        return f;
    }
    static Sql_Variant_Property f_sql_variant_property(
            Expression expression,
            StringConstant property){
        Sql_Variant_Property f = new Sql_Variant_Property();
        f.setExpression(expression);
        f.setProperty(property);
        return f;
    }

}
