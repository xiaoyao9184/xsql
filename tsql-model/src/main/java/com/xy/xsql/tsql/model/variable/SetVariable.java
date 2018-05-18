package com.xy.xsql.tsql.model.variable;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;

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
public class SetVariable {

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
