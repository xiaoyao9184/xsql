package com.xy.xsql.tsql.model.element.table;

import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;
import com.xy.xsql.tsql.model.element.constraint.Constraint;
import com.xy.xsql.tsql.model.statement.ddl.alter.table.Add;
import com.xy.xsql.tsql.model.statement.ddl.create.table.DiskBasedCreateTable;
import com.xy.xsql.tsql.model.statement.ddl.create.table.MemoryOptimizedCreateTable;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/data-types/table-transact-sql
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 *
 * Created by xiaoyao9184 on 2017/8/7.
 */
public class TableConstraint
        implements Constraint, TableTypeDefinition.Item, DiskBasedCreateTable.Item, MemoryOptimizedCreateTable.Item, Add.AddItem {

    //CONSTRAINT constraint_name
    private String constraintName;

    //{ { PRIMARY KEY | UNIQUE } ( column_name [ ,...n ] )
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
