package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.datatypes.table.table.TableStretchOptions;

/**
 * Created by xiaoyao9184 on 2017/9/16.
 */
public class TableStretchOptionsBuilder<ParentBuilder>
        extends CodeTreeBuilder<TableStretchOptionsBuilder<ParentBuilder>,ParentBuilder,TableStretchOptions> {

    public TableStretchOptionsBuilder(TableStretchOptions target) {
        super(target);
    }

    public TableStretchOptionsBuilder<ParentBuilder> withUseFilterPredicate(boolean useFilterPredicate){
        target.setUseFilterPredicate(useFilterPredicate);
        return this;
    }

    public TableStretchOptionsBuilder<ParentBuilder> withTablePredicateFunction(String tablePredicateFunction){
        target.setTablePredicateFunction(tablePredicateFunction);
        return this;
    }

    public TableStretchOptionsBuilder<ParentBuilder> withMigrationState(TableStretchOptions.MigrationState migrationState){
        target.setMigrationState(migrationState);
        return this;
    }

    /*
    Quick
     */

    public TableStretchOptionsBuilder<ParentBuilder> $FILTER_PREDICATE_null(){
        return withUseFilterPredicate(false);
    }

    public TableStretchOptionsBuilder<ParentBuilder> $FILTER_PREDICATE(String tablePredicateFunction){
        return withUseFilterPredicate(true)
                .withTablePredicateFunction(tablePredicateFunction);
    }

    public TableStretchOptionsBuilder<ParentBuilder> $MIGRATION_STATE_$OUTBOUND(){
        return withMigrationState(TableStretchOptions.MigrationState.OUTBOUND);
    }

    public TableStretchOptionsBuilder<ParentBuilder> $MIGRATION_STATE_$INBOUND(){
        return withMigrationState(TableStretchOptions.MigrationState.INBOUND);
    }

    public TableStretchOptionsBuilder<ParentBuilder> $MIGRATION_STATE_$PAUSED(){
        return withMigrationState(TableStretchOptions.MigrationState.PAUSED);
    }

}
