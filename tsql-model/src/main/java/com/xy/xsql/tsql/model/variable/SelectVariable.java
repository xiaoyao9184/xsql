package com.xy.xsql.tsql.model.variable;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.statement.Statement;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2017/3/17.
 *
 *

-- Syntax for SQL Server and Azure SQL Database

 SELECT { @local_variable { = | += | -= | *= | /= | %= | &= | ^= | |= } expression } [ ,...n ] [ ; ]

 *
 */
public class SelectVariable implements Statement {


    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(Keywords.SELECT)
                .append(items)
                .build();
    }


    public static class Item implements Block {
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

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(localVariable)
                    .append(compound == null ? Assignment.ASSIGNMENT : compound)
                    .append(expression)
                    .build();
        }
    }
}
