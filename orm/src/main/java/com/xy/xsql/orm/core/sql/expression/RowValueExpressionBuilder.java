package com.xy.xsql.orm.core.sql.expression;

import com.xy.xsql.tsql.model.expression.RowValueExpression;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e;

/**
 * Created by xiaoyao9184 on 2017/3/10.
 */
public class RowValueExpressionBuilder {

    public static RowValueExpression rve(String stringExpression){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setExpression(e(stringExpression));
        return rowValueExpression;
    }

    public static RowValueExpression rve(Number numberExpression){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setExpression(e(numberExpression));
        return rowValueExpression;
    }

    public static RowValueExpression rve(Select select){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setSelect(select);
        return rowValueExpression;
    }


    public static RowValueExpression rve_null(){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setUseNull(true);
        return rowValueExpression;
    }

    public static RowValueExpression rve_default(){
        RowValueExpression rowValueExpression = new RowValueExpression();
        rowValueExpression.setUseDefault(true);
        return rowValueExpression;
    }

}
