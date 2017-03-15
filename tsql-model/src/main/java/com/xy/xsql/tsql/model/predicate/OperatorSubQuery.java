package com.xy.xsql.tsql.model.predicate;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Logical;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * TODO maybe use Operator.Logical named
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
 { ALL | SOME | ANY} ( subquery )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class OperatorSubQuery implements Predicate, Expression {
    //expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
    //{ ALL | SOME | ANY} ( subquery )
    private Expression expression;
    private com.xy.xsql.tsql.model.operator.Operator operator;
    private ALL_SOME_ANY all_some_any;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public com.xy.xsql.tsql.model.operator.Operator getOperator() {
        return operator;
    }

    public void setOperator(com.xy.xsql.tsql.model.operator.Operator Operators) {
        this.operator = Operators;
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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(expression)
                .append(operator)
                .append(all_some_any.toOperator())
                .append(Other.GROUP_START)
                .append(subquery)
                .append(Other.GROUP_END);
        return b.build();
    }


    public enum ALL_SOME_ANY implements Block {
        ALL(Logical.ALL),
        SOME(Logical.SOME),
        ANY(Logical.ANY);

        private com.xy.xsql.tsql.model.operator.Operator operator;

        ALL_SOME_ANY(com.xy.xsql.tsql.model.operator.Operator operator){
            this.operator = operator;
        }

        @Override
        public String toString(){
            return this.operator.toString();
        }

        public com.xy.xsql.tsql.model.operator.Operator toOperator(){
            return this.operator;
        }
    }

}
