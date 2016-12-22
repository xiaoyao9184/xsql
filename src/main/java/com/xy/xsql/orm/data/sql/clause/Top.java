package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

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
public class Top implements Expression {

    private Expression countExpression;
    private boolean usePercent;
    private boolean useParenthesis;
    private boolean useTies;

    public Top withCountExpression(Expression countExpression){
        this.countExpression = countExpression;
        return this;
    }

    public Top withPercent(boolean usePercent){
        this.usePercent = usePercent;
        return this;
    }

    public boolean isUseParenthesis() {
        return useParenthesis;
    }

    public void setUseParenthesis(boolean useParenthesis) {
        this.useParenthesis = useParenthesis;
    }

    public boolean isUseTies() {
        return useTies;
    }

    public void setUseTies(boolean useTies) {
        this.useTies = useTies;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .append(GrammarEnum.TOP);
        if(useParenthesis){
            b.append(OtherEnum.GROUP_START)
                    .append(countExpression)
                    .append(OtherEnum.GROUP_END);
        }
        b.append(usePercent ? GrammarEnum.PERCENT : null);
        if(useTies){
            b.append(GrammarEnum.WITH)
                    .append(OtherEnum.SPACE)
                    .append(GrammarEnum.TIES);
        }
        return b.build();
    }
}
