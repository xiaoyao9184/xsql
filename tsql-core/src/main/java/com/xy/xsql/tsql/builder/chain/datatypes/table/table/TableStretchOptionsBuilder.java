package com.xy.xsql.tsql.builder.chain.datatypes.table.table;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.table.TableStretchOptions;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * TableStretchOptionsBuilder
 * Created by xiaoyao9184 on 2017/9/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class TableStretchOptionsBuilder<ParentBuilder>
        extends ParentHoldBuilder<TableStretchOptionsBuilder<ParentBuilder>,ParentBuilder,TableStretchOptions> {

    public TableStretchOptionsBuilder() {
        super(new TableStretchOptions());
    }

    public TableStretchOptionsBuilder(TableStretchOptions target) {
        super(target);
    }

    /**
     * set
     * @param useFilterPredicate filter predicate
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> withUseFilterPredicate(boolean useFilterPredicate){
        target.setUseFilterPredicate(useFilterPredicate);
        return this;
    }

    /**
     * set
     * @param tablePredicateFunction table predicate function
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> withTablePredicateFunction(String tablePredicateFunction){
        target.setTablePredicateFunction(tablePredicateFunction);
        return this;
    }

    /**
     * set
     * @param migrationState MigrationState
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> withMigrationState(TableStretchOptions.MigrationState migrationState){
        target.setMigrationState(migrationState);
        return this;
    }




    /*
    Quick
     */

    /**
     * Quick in
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> $FilterPredicateNull(){
        return withUseFilterPredicate(false);
    }

    /**
     * Quick in
     * @param tablePredicateFunction  table predicate function
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> $FilterPredicate(String tablePredicateFunction){
        return withUseFilterPredicate(true)
                .withTablePredicateFunction(tablePredicateFunction);
    }

    /**
     * Quick in
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> $MigrationState$Outbound(){
        return withMigrationState(TableStretchOptions.MigrationState.OUTBOUND);
    }

    /**
     * Quick in
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> $MigrationState$Inbound(){
        return withMigrationState(TableStretchOptions.MigrationState.INBOUND);
    }

    /**
     * Quick in
     * @return THIS
     */
    public TableStretchOptionsBuilder<ParentBuilder> $MigrationState$Paused(){
        return withMigrationState(TableStretchOptions.MigrationState.PAUSED);
    }


    /**
     * TableStretchOptionsListBuilder
     * @param <ParentBuilder>
     */
    public static class TableStretchOptionsListBuilder<ParentBuilder>
            extends ParentHoldBuilder<TableStretchOptionsListBuilder<ParentBuilder>,ParentBuilder,List<TableStretchOptions>> {

        public TableStretchOptionsListBuilder() {
            super(new ArrayList<>());
        }

        public TableStretchOptionsListBuilder(List<TableStretchOptions> target) {
            super(target);
        }

        /**
         * in
         * @return TableStretchOptionsBuilder
         */
        public TableStretchOptionsBuilder<TableStretchOptionsListBuilder<ParentBuilder>> withItem(){
            return new TableStretchOptionsBuilder<TableStretchOptionsListBuilder<ParentBuilder>>
                    (object(TableStretchOptions::new).set(target::add))
                    .in(this);
        }

    }
}
