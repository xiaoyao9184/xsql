package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.tsql.model.operator.Logical;
import com.xy.xsql.orm.data.sql.statements.dml.Select;

import java.util.List;

/**
 * expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
 { ALL | SOME | ANY} ( subquery )
 * Created by xiaoyao9184 on 2017/3/12.
 */
public class OperatorSubQuery implements Predicate {
    //expression { = | < > | ! = | > | > = | ! > | < | < = | ! < }
    //{ ALL | SOME | ANY} ( subquery )
    private Expression expression;
    private com.xy.xsql.orm.data.sql.element.operator.Operator operator;
    private ALL_SOME_ANY all_some_any;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public com.xy.xsql.orm.data.sql.element.operator.Operator getOperator() {
        return operator;
    }

    public void setOperator(com.xy.xsql.orm.data.sql.element.operator.Operator operatorEnum) {
        this.operator = operatorEnum;
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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(expression)
                .append(operator)
                .append(all_some_any.toOperator())
                .append(OtherEnum.GROUP_START)
                .append(subquery)
                .append(OtherEnum.GROUP_END);
        return b.build();
    }


    public enum ALL_SOME_ANY implements Element {
        ALL(Logical.ALL),
        SOME(Logical.SOME),
        ANY(Logical.ANY);

        private com.xy.xsql.orm.data.sql.element.operator.Operator operator;

        ALL_SOME_ANY(com.xy.xsql.orm.data.sql.element.operator.Operator operator){
            this.operator = operator;
        }

        @Override
        public String toString(){
            return this.operator.toString();
        }

        public com.xy.xsql.orm.data.sql.element.operator.Operator toOperator(){
            return this.operator;
        }
    }

}
