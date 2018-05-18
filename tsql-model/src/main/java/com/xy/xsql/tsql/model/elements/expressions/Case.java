package com.xy.xsql.tsql.model.elements.expressions;

import java.util.ArrayList;
import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms181765.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 Simple CASE expression:
 CASE input_expression
 WHEN when_expression THEN result_expression [ ...n ]
 [ ELSE else_result_expression ]
 END
 Searched CASE expression:
 CASE
 WHEN Boolean_expression THEN result_expression [ ...n ]
 [ ELSE else_result_expression ]
 END

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 CASE
 WHEN when_expression THEN result_expression [ ...n ]
 [ ELSE else_result_expression ]
 END

 *
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class Case implements Expression {
    //
    private Expression inputExpression;
    //WHEN when_expression THEN result_expression [ ...n ]
    private List<WhenThenExpression> whenThenExpressionList;
    //[ ELSE else_result_expression ]
    private Expression elseResultExpression;

    public Expression getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(Expression inputExpression) {
        this.inputExpression = inputExpression;
    }

    public List<WhenThenExpression> getWhenThenExpressionList() {
        return whenThenExpressionList;
    }

    public void setWhenThenExpressionList(List<WhenThenExpression> whenThenExpressionList) {
        this.whenThenExpressionList = whenThenExpressionList;
    }

    public Expression getElseResultExpression() {
        return elseResultExpression;
    }

    public void setElseResultExpression(Expression elseResultExpression) {
        this.elseResultExpression = elseResultExpression;
    }


    public Case withInputExpression(Expression inputExpression) {
        this.inputExpression = inputExpression;
        return this;
    }

    public Case withWhenThenExpressionList(WhenThenExpression whenThenExpressionList) {
        if(this.whenThenExpressionList == null){
            this.whenThenExpressionList = new ArrayList<>();
        }
        this.whenThenExpressionList.add(whenThenExpressionList);
        return this;
    }

    public Case withElseResultExpression(Expression elseExpression) {
        this.elseResultExpression = elseExpression;
        return this;
    }


    /**
     * WHEN when_expression THEN result_expression
     *
     */
    public static class WhenThenExpression implements Expression  {
        private Expression whenExpression;
        private Expression resultExpression;

        public Expression getWhenExpression() {
            return whenExpression;
        }

        public void setWhenExpression(Expression whenExpression) {
            this.whenExpression = whenExpression;
        }

        public Expression getResultExpression() {
            return resultExpression;
        }

        public void setResultExpression(Expression resultExpression) {
            this.resultExpression = resultExpression;
        }


        public WhenThenExpression withWhenExpression(Expression whenExpression) {
            this.whenExpression = whenExpression;
            return this;
        }

        public WhenThenExpression withThenExpression(Expression resultExpression) {
            this.resultExpression = resultExpression;
            return this;
        }

    }

}
