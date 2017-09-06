package com.xy.xsql.tsql.model.element.table;

/**
 * https://docs.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql
 * Created by xiaoyao9184 on 2017/8/16.
 */
public class TableStretchOptions {
    //[ FILTER_PREDICATE = { null | table_predicate_function } , ]
    private boolean useFilterPredicate;
    private String tablePredicateFunction;
    //MIGRATION_STATE = { OUTBOUND | INBOUND | PAUSED }
    private MigrationState MigrationState;

    public boolean isUseFilterPredicate() {
        return useFilterPredicate;
    }

    public void setUseFilterPredicate(boolean useFilterPredicate) {
        this.useFilterPredicate = useFilterPredicate;
    }

    public String getTablePredicateFunction() {
        return tablePredicateFunction;
    }

    public void setTablePredicateFunction(String tablePredicateFunction) {
        this.tablePredicateFunction = tablePredicateFunction;
    }

    public TableStretchOptions.MigrationState getMigrationState() {
        return MigrationState;
    }

    public void setMigrationState(TableStretchOptions.MigrationState migrationState) {
        MigrationState = migrationState;
    }

    public enum MigrationState {
        OUTBOUND,
        INBOUND,
        PAUSED
    }
}
