package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Operator;
import com.xy.xsql.tsql.model.elements.operators.Comparison;
import com.xy.xsql.tsql.model.elements.operators.Logical;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * TODO maybe use Comparison.Logical named
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
 { ALL | SOME | ANY} ( subquery )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class ComparisonSubQuery implements Predicate, Expression {
    //expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
    //{ ALL | SOME | ANY} ( subquery )
    private Expression expression;
    private Comparison operator;
    private ALL_SOME_ANY all_some_any;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Comparison getOperator() {
        return operator;
    }

    public void setOperator(Comparison operator) {
        this.operator = operator;
    }

    public ALL_SOME_ANY getAll_some_any() {
        return all_some_any;
    }

    public void setAll_some_any(ALL_SOME_ANY all_some_any) {
        this.all_some_any = all_some_any;
    }

    public Select getSubquery() {
        return subquery;
    }

    public void setSubquery(Select subquery) {
        this.subquery = subquery;
    }


    public enum ALL_SOME_ANY {
        ALL(Logical.ALL),
        SOME(Logical.SOME),
        ANY(Logical.ANY);

        private Operator operator;

        ALL_SOME_ANY(Operator operator){
            this.operator = operator;
        }

        @Override
        public String toString(){
            return this.operator.toString();
        }

        public Operator toOperator(){
            return this.operator;
        }
    }

}
