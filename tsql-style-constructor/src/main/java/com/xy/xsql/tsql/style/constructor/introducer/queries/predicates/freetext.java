package com.xy.xsql.tsql.style.constructor.introducer.queries.predicates;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_FREETEXT;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface freetext {


    static b_FREETEXT FREETEXT(
            ColumnName column_name,
            String freetext_string){
        return new b_FREETEXT(){
            {
                withColumnName(column_name);
                withFreeText(freetext_string);
            }
        };
    }

    static b_FREETEXT FREETEXT(
            b_FREETEXT.b$column_list column_list,
            String freetext_string){
        return new b_FREETEXT(){
            {
                withColumn(column_list.build());
                withFreeText(freetext_string);
            }
        };
    }

    static b_FREETEXT FREETEXT(
            String freetext_string){
        return new b_FREETEXT(){
            {
                withAllColumn();
                withFreeText(freetext_string);
            }
        };
    }

    static b_FREETEXT FREETEXT(
            String column_name,
            String freetext_string){
        return new b_FREETEXT(){
            {
                withColumnName(column_name);
                withFreeText(freetext_string);
            }
        };
    }

    //

    static b_FREETEXT.b$column_list $(ColumnName... columnNames){
        return new b_FREETEXT.b$column_list(){
            {
                this.target.addAll(Arrays.asList(columnNames));
            }
        };
    }

    static b_FREETEXT.b$column_list $(String... column){
        return new b_FREETEXT.b$column_list(){
            {
                this.target.addAll(
                        Arrays.stream(column)
                                .map(ColumnName::new)
                                .collect(Collectors.toList()));
            }
        };
    }

}
