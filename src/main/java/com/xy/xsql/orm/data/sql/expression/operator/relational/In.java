package com.xy.xsql.orm.data.sql.expression.operator.relational;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class In implements Expression {

    private Expression leftExpression;
    private List<Element> leftItemsList;
    private List<Element> rightItemsList;
    private boolean not = false;

    @Override
    public List<Element> toElementList() {
        if(this.leftExpression == null){
            return new ListElementBuilder()
                    .append(leftItemsList,OtherEnum.DELIMITER)
                    .append(OtherEnum.SPACE)
                    .append(not ? GrammarEnum.NOT : null)
                    .append(OtherEnum.SPACE)
                    .append(GrammarEnum.IN)
                    .append(OtherEnum.GROUP_START)
                    .append(rightItemsList,OtherEnum.DELIMITER)
                    .append(OtherEnum.GROUP_END)
                    .build();
        }else{
            return new ListElementBuilder()
                    .append(leftExpression)
                    .append(OtherEnum.SPACE)
                    .append(not ? GrammarEnum.NOT : null)
                    .append(OtherEnum.SPACE)
                    .append(GrammarEnum.IN)
                    .append(OtherEnum.GROUP_START)
                    .append(rightItemsList,OtherEnum.DELIMITER)
                    .append(OtherEnum.GROUP_END)
                    .build();
        }


    }
}
