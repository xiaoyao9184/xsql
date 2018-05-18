package com.xy.xsql.tsql.model.datatypes.table.column;

import com.xy.xsql.tsql.model.datatypes.table.constraint.Constraint;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/data-types/table-transact-sql
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 *
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class ColumnConstraint implements Constraint {
    //CONSTRAINT constraint_name
    private String constraintName;
    //{ [ NULL | NOT NULL ]
    //| [ PRIMARY KEY | UNIQUE ]
    //| CHECK ( logical_expression )
    //}
    private Constraint constraint;

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }
}
