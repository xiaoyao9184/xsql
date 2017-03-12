package com.xy.xsql.orm.data.sql.element.predicate;


import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.clause.SearchCondition;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
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
    private OperatorEnum operatorEnum;
    private ALL_SOME_ANY all_some_any;
    private Select subquery;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public OperatorEnum getOperatorEnum() {
        return operatorEnum;
    }

    public void setOperatorEnum(OperatorEnum operatorEnum) {
        this.operatorEnum = operatorEnum;
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
                .append(operatorEnum)
                .append(all_some_any.toOperatorEnum())
                .append(OtherEnum.GROUP_START)
                .append(subquery)
                .append(OtherEnum.GROUP_END);
        return b.build();
    }


    public enum ALL_SOME_ANY implements Element {
        ALL(OperatorEnum.ALL),
        SOME(OperatorEnum.SOME),
        ANY(OperatorEnum.ANY);

        private OperatorEnum operatorEnum;

        ALL_SOME_ANY(OperatorEnum operatorEnum){
            this.operatorEnum = operatorEnum;
        }

        @Override
        public String toString(){
            return this.operatorEnum.toString();
        }

        public OperatorEnum toOperatorEnum(){
            return this.operatorEnum;
        }
    }

}
