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

        SET
        { @local_variable
    [ . { property_name | field_name } ] = { expression | udt_name { . | :: } method_name }
            }
            |
            { @SQLCLR_local_variable.mutator_method
}
        |
        { @local_variable
    {+= | -= | *= | /= | %= | &= | ^= | |= } expression
            }
            |
            { @cursor_variable =
        { @cursor_variable | cursor_name
        | { CURSOR [ FORWARD_ONLY | SCROLL ]
        [ STATIC | KEYSET | DYNAMIC | FAST_FORWARD ]
        [ READ_ONLY | SCROLL_LOCKS | OPTIMISTIC ]
        [ TYPE_WARNING ]
        FOR select_statement
        [ FOR { READ ONLY | UPDATE [ OF column_name [ ,...n ] ] } ]
        }
        }
        }

 *
 */
public class SetVariable implements Statement {

    //@local_variable
    private VariableString localVariable;
    //{+= | -= | *= | /= | %= | &= | ^= | |= }
    private Compound compound;
    //expression
    private Expression expression;

    public VariableString getLocalVariable() {
        return localVariable;
    }

    public void setLocalVariable(VariableString localVariable) {
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
                .append(Keywords.SET)
                .append(localVariable)
                .append(compound == null ? Assignment.ASSIGNMENT : compound)
                .append(expression)
                .build();
    }
}
