package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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


    public Case withCaseExpression(Expression caseExpression) {
        this.inputExpression = caseExpression;
        return this;
    }

    public Case withWhenThenExpressionList(WhenThenExpression whenThenExpressionList) {
        if(this.whenThenExpressionList == null){
            this.whenThenExpressionList = new ArrayList<>();
        }
        this.whenThenExpressionList.add(whenThenExpressionList);
        return this;
    }

    public Case withElseExpression(Expression elseExpression) {
        this.elseResultExpression = elseExpression;
        return this;
    }



    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.CASE)
                .append(inputExpression);
        for (WhenThenExpression whenThenExpression: whenThenExpressionList) {
            b.append(whenThenExpression.toBlockList());
        }
        if(elseResultExpression != null){
            b.append(Keywords.ELSE);
            b.append(elseResultExpression);
        }
        b.append(Keywords.END);
        return b.build();
    }


    /**
     * WHEN when_expression THEN result_expression
     *
     */
    public static class WhenThenExpression implements Expression  {
        private Expression whenExpression;
        private Expression resultExpression;


        public WhenThenExpression withWhenElment(Expression whenElment) {
            this.whenExpression = whenElment;
            return this;
        }

        public WhenThenExpression withThenElment(Expression thenElment) {
            this.resultExpression = thenElment;
            return this;
        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(Keywords.WHEN)
                    .append(whenExpression)
                    .append(Keywords.THEN)
                    .append(resultExpression);
            return b.build();
        }
    }

}
