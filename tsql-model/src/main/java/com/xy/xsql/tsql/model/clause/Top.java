package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms189463.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [
 TOP (expression) [PERCENT]
 [ WITH TIES ]
 ]

 *
 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 [
 TOP ( expression )
 [ WITH TIES ]
 ]
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class Top implements Clause {

    private Expression expression;
    private boolean usePercent;
    private boolean useTies;


    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUsePercent() {
        return usePercent;
    }

    public void setUsePercent(boolean usePercent) {
        this.usePercent = usePercent;
    }

    public boolean isUseTies() {
        return useTies;
    }

    public void setUseTies(boolean useTies) {
        this.useTies = useTies;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.TOP);
        b.append(usePercent ? Keywords.PERCENT : null);
        if(useTies){
            b.append(Keywords.WITH)
                    .append(Other.SPACE)
                    .append(Keywords.Key.TIES);
        }
        return b.build();
    }
}
