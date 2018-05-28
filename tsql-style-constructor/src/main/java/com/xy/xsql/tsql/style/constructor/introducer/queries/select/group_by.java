package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.style.constructor.builder.queries.select.b_GROUP_BY;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface group_by {

    static b_GROUP_BY GROUP_BY(){
        return new b_GROUP_BY(){
            {
                withItem()._Total();
            }
        };
    }

    static b_GROUP_BY GROUP_BY(Expression columnExpression){
        return new b_GROUP_BY(){
            {
                withItem()._Base().withColumnExpression(columnExpression);
            }
        };
    }

    static b_GROUP_BY GROUP_BY(b_GROUP_BY.b_ROLLUP rollup){
        return new b_GROUP_BY(){
            {
                withItem(rollup.build());
            }
        };
    }

    static b_GROUP_BY GROUP_BY(b_GROUP_BY.b_CUBE cube){
        return new b_GROUP_BY(){
            {
                withItem(cube.build());
            }
        };
    }

    static b_GROUP_BY GROUP_BY(b_GROUP_BY.b_GROUPING_SETS grouping_sets){
        return new b_GROUP_BY(){
            {
                withItem(grouping_sets.build());
            }
        };
    }

//    static b_GROUP_BY GROUP_BY(ColumnName columnName){
//        return new b_GROUP_BY(){
//            {
//                withItem()._ColumnName(columnName,false);
//            }
//        };
//    }

    static b_GROUP_BY GROUP_BY(ColumnName columnName, b_GROUP_BY.k_WITH_DISTRIBUTED_AGG with_distributed_agg){
        return new b_GROUP_BY(){
            {
                withItem()._ColumnName(columnName,true);
            }
        };
    }


    static b_GROUP_BY.b_ROLLUP ROLLUP(Expression... columnExpression){
        return new b_GROUP_BY.b_ROLLUP(){
            {
                withItem().$(columnExpression);
            }
        };
    }

    static b_GROUP_BY.b_CUBE CUBE(Expression... columnExpression){
        return new b_GROUP_BY.b_CUBE(){
            {
                withItem().$(columnExpression);
            }
        };
    }

    static b_GROUP_BY.b_GROUPING_SETS GROUPING_SETS(){
        return new b_GROUP_BY.b_GROUPING_SETS(){
            {
                withItem().$_();
            }
        };
    }

    static b_GROUP_BY.b_GROUPING_SETS GROUPING_SETS(Expression... columnExpression){
        return new b_GROUP_BY.b_GROUPING_SETS(){
            {
                withItem().$(columnExpression);
            }
        };
    }

    static b_GROUP_BY.b_GROUPING_SETS GROUPING_SETS(b_GROUP_BY.b_ROLLUP rollup){
        return new b_GROUP_BY.b_GROUPING_SETS(){
            {
                withItem().withItem(rollup.build());
            }
        };
    }

    static b_GROUP_BY.b_GROUPING_SETS GROUPING_SETS(b_GROUP_BY.b_CUBE cube){
        return new b_GROUP_BY.b_GROUPING_SETS(){
            {
                withItem().withItem(cube.build());
            }
        };
    }

    static b_GROUP_BY.b_GROUPING_SETS GROUPING_SETS(b_GROUP_BY.b$grouping_set grouping_set){
        return new b_GROUP_BY.b_GROUPING_SETS(){
            {
                withItem(grouping_set.build());
            }
        };
    }


    static b_GROUP_BY.b$grouping_set $(){
        return new b_GROUP_BY.b$grouping_set(){
            {
                withTotal();
            }
        };
    }

    static b_GROUP_BY.b$grouping_set $(Expression... columnExpression){
        return new b_GROUP_BY.b$grouping_set(){
            {
                withItem()._Base()
                        .withColumnExpression(columnExpression);
            }
        };
    }

    static b_GROUP_BY.b$grouping_set $(b_GROUP_BY.b_ROLLUP rollup){
        return new b_GROUP_BY.b$grouping_set(){
            {
                withItem(rollup.build());
            }
        };
    }

    static b_GROUP_BY.b$grouping_set $(b_GROUP_BY.b_CUBE cube){
        return new b_GROUP_BY.b$grouping_set(){
            {
                withItem(cube.build());
            }
        };
    }


    static b_GROUP_BY.k_WITH_DISTRIBUTED_AGG WITH_DISTRIBUTED_AGG(){
        return null;
    }
}
