package com.xy.xsql.tsql.style.constructor.introducer.queries.predicates;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_CONTAINS;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface contains {


    static b_CONTAINS CONTAINS(
            ColumnName column_name,
            String contains_search_condition){
        return new b_CONTAINS(){
            {
                withColumnName(column_name);
                withContainsSearchCondition(contains_search_condition);
            }
        };
    }

    static b_CONTAINS CONTAINS(
            b_CONTAINS.b$column_list column_list,
            String contains_search_condition){
        return new b_CONTAINS(){
            {
                withColumn(column_list.build());
                withContainsSearchCondition(contains_search_condition);
            }
        };
    }

    static b_CONTAINS CONTAINS(
            String contains_search_condition){
        return new b_CONTAINS(){
            {
                withAllColumn();
                withContainsSearchCondition(contains_search_condition);
            }
        };
    }

    static b_CONTAINS CONTAINS(
            b_CONTAINS.b_PROPERTY property,
            String contains_search_condition){
        return new b_CONTAINS(){
            {
                withProperty(property.build().getPropertyColumnName(),property.build().getPropertyName());
                withContainsSearchCondition(contains_search_condition);
            }
        };
    }

    static b_CONTAINS CONTAINS(
            String column_name,
            String contains_search_condition){
        return new b_CONTAINS(){
            {
                withColumnName(c(column_name));
                withContainsSearchCondition(contains_search_condition);
            }
        };
    }

    //
    static b_CONTAINS.b_PROPERTY PROPERTY(ColumnName column_name, String property_name){
        return new b_CONTAINS.b_PROPERTY(){
            {
                withProperty(column_name, property_name);
            }
        };
    }

    //

    static b_CONTAINS.b$column_list $(ColumnName... columnNames){
        return new b_CONTAINS.b$column_list(){
            {
                this.target.addAll(Arrays.asList(columnNames));
            }
        };
    }

    static b_CONTAINS.b$column_list $(String... column){
        return new b_CONTAINS.b$column_list(){
            {
                this.target.addAll(
                        Arrays.stream(column)
                                .map(ColumnName::new)
                                .collect(Collectors.toList()));
            }
        };
    }

}
