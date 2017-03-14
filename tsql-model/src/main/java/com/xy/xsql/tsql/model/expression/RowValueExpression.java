package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class RowValueExpression implements Expression {

    private boolean useNull = false;
    private boolean useDefault = false;
    private Expression expression;
    //TODO or Select
    private Select select;


    public boolean isUseNull() {
        return useNull;
    }

    public void setUseNull(boolean useNull) {
        this.useNull = useNull;
    }

    public boolean isUseDefault() {
        return useDefault;
    }

    public void setUseDefault(boolean useDefault) {
        this.useDefault = useDefault;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        if(isUseNull()){
            b.append(Keywords.NULL);
        }else if(isUseDefault()) {
            b.append(Keywords.DEFAULT);
        }else {
            b.append(getExpression());
        }
        return b.build();
    }

    public void setSelect(Select select) {
        this.select = select;
    }
}
