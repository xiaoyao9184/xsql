package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.expression.RowValueExpression;
import com.xy.xsql.tsql.model.statement.dml.Select;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_n_string;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_string;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class RowValueExpressionBuilder {

    public static RowValueExpression e_rv(String stringExpression, boolean nchar){
        RowValueExpression rowValueExpression = new RowValueExpression();
        if(nchar){
            rowValueExpression.setExpression(e_n_string(stringExpression));
        }else{
            rowValueExpression.setExpression(e_string(stringExpression));
        }
        return rowValueExpression;
    }

    public static RowValueExpression e_rv(String stringExpression){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setExpression(e_string(stringExpression));
        return rowValueExpression;
    }

    public static RowValueExpression e_rv(Number numberExpression){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setExpression(e_number(numberExpression));
        return rowValueExpression;
    }

    public static RowValueExpression e_rv(Select select){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setSelect(select);
        return rowValueExpression;
    }


    public static RowValueExpression e_rv_null(){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setUseNull(true);
        return rowValueExpression;
    }

    public static RowValueExpression e_rv_default(){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setUseDefault(true);
        return rowValueExpression;
    }

}
