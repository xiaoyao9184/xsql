package com.xy.xsql.tsql.style.constructor.builder.queries.select;

import com.xy.xsql.tsql.builder.chain.queries.select.GroupByBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_GROUP_BY extends GroupByBuilder<b_GROUP_BY> {

    public b_GROUP_BY() {
        this.in(this);
    }


    /*
    Item
     */

    public b_GROUP_BY $$(Expression columnExpressions) {
        return withItem()._Base()
                .withColumnExpression(columnExpressions)
                .and()
                .and();
    }

    public b_GROUP_BY $$(ColumnName columnName) {
        return withItem()._ColumnName(columnName,false)
                .and();
    }

    public b_GROUP_BY $$(ColumnName columnName, k_WITH_DISTRIBUTED_AGG with_distributed_agg) {
        return withItem()._ColumnName(columnName,true)
                .and();
    }

    public b_GROUP_BY $$() {
        return withItem()._Total()
                .and();
    }

    public b_GROUP_BY $$(b_ROLLUP rollup) {
        return withItem(rollup.build())
                .and();
    }

    public b_GROUP_BY $$(b_CUBE cube) {
        return withItem(cube.build())
                .and();
    }

    public b_GROUP_BY $$(b_GROUPING_SETS grouping_sets) {
        return withItem(grouping_sets.build())
                .and();
    }


    public static class b_ROLLUP extends RollupItemBuilder<b_ROLLUP> {

        public b_ROLLUP() {
            this.in(this);
        }

        /*
        Item
         */

        public b_ROLLUP $$(Expression... expressions) {
            return withItem()
                    .withColumnExpression(expressions)
                    .and()
                    .and();
        }

    }

    public static class b_CUBE extends CubeItemBuilder<b_CUBE> {

        public b_CUBE() {
            this.in(this);
        }

        /*
        Item
         */

        public b_CUBE $$(Expression... expressions) {
            return withItem()
                    .withColumnExpression(expressions)
                    .and()
                    .and();
        }

    }

    public static class b$total extends TotalItemBuilder<b$total> {

        public b$total() {
            this.in(this);
        }

    }

    public static class b_GROUPING_SETS extends GroupingSetsItemBuilder<b_GROUPING_SETS> {

        public b_GROUPING_SETS() {
            this.in(this);
        }

        /*
        Item
         */

        public b_GROUPING_SETS $$(){
            return withItem()
                    .$_()
                    .and()
                    .and();
        }

        public b_GROUPING_SETS $$(Expression... group_by_expression){
            return withItem()
                    .$(group_by_expression)
                    .and()
                    .and();
        }

        public b_GROUPING_SETS $$(b_ROLLUP rollup){
            return withItem()
                    .withItem(rollup.build())
                    .and()
                    .and();
        }

        public b_GROUPING_SETS $$(b_CUBE cube){
            return withItem()
                    .withItem(cube.build())
                    .and()
                    .and();
        }

        public b_GROUPING_SETS $$(b$grouping_set grouping_set_item){
            return withItem(grouping_set_item.build())
                    .and()
                    .and();
        }
    }

    public static class b$grouping_set extends GroupingSetBuilder<b$grouping_set> {

        public b$grouping_set() {
            this.in(this);
        }

        /*
        Item
         */

        public b$grouping_set $$(Expression... columnExpression){
            return withItem()._Base()
                    .$(columnExpression)
                    .and()
                    .and();
        }

        public b$grouping_set $$(b_ROLLUP rollup){
            return withItem(rollup.build())
                    .and();
        }

        public b$grouping_set $$(b_CUBE cube){
            return withItem(cube.build())
                    .and();
        }

    }

    public static class k_WITH_DISTRIBUTED_AGG {}
}
