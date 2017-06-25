package com.xy.xsql.tsql.model.variable;

import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Compound;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 *
 *

-- Syntax for SQL Server and Azure SQL Database

 SELECT { @local_variable { = | += | -= | *= | /= | %= | &= | ^= | |= } expression } [ ,...n ] [ ; ]

 *
 */
public class SelectVariable {


    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public static class Item {
        //@local_variable
        private LocalVariable localVariable;
        //{+= | -= | *= | /= | %= | &= | ^= | |= }
        private Compound compound;
        //expression
        private Expression expression;

        public LocalVariable getLocalVariable() {
            return localVariable;
        }

        public void setLocalVariable(LocalVariable localVariable) {
            this.localVariable = localVariable;
        }

        public Compound getCompound() {
            return compound;
        }

        public void setCompound(Compound compound) {
            this.compound = compound;
        }

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

    }
}
