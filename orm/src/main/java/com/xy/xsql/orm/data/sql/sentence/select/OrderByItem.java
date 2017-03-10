package com.xy.xsql.orm.data.sql.sentence.select;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/19.
 */
public class OrderByItem implements Expression {
    private Expression expression;
    private boolean useAsc = true;
    private boolean useDesc = false;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUseAsc() {
        return useAsc;
    }

    public void setUseAsc(boolean useAsc) {
        this.useAsc = useAsc;
    }

    public boolean isUseDesc() {
        return useDesc;
    }

    public void setUseDesc(boolean useDesc) {
        this.useDesc = useDesc;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder builder = new ListElementBuilder()
                .append(expression);
        if (useDesc) {
            builder.append(" DESC");
        }else if(useAsc){
            builder.append(" ASC");
        }

        return builder.build(null);
    }

}
