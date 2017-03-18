package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Assignment;
import com.xy.xsql.tsql.model.operator.Operators;
import com.xy.xsql.tsql.util.CheckUtil;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms190322.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [ OPTION ( <query_hint> [ ,...n ] ) ]

 *

 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 OPTION ( <query_option> [ ,...n ] )

 <query_option> ::=
 LABEL = label_name |
 <query_hint>

 <query_hint> ::=
 HASH JOIN
 | LOOP JOIN
 | MERGE JOIN
 | FORCE ORDER
 | { FORCE | DISABLE } EXTERNALPUSHDOWN
 *
 * Created by xiaoyao9184 on 2016/12/20.
 */
public class Option implements Clause {

    private List<QueryOption> queryOption;


    public List<QueryOption> getQueryOption() {
        return queryOption;
    }

    public void setQueryOption(List<QueryOption> queryOption) {
        this.queryOption = queryOption;
    }


    @Override
    public List<Block> toBlockList() {
        return new ListBlockBuilder()
                .append(Keywords.OPTION)
                .append(queryOption)
                .build();
    }


    /**
     * <query_option>
     */
    public interface QueryOption extends Block {

        /**
         * must override
         * @return
         */
        List<Block> toBlockList();
    }

    /**
     * <query_option>
     *      LABEL = label_name
     */
    public static class LabelQueryOption implements QueryOption {

        //label_name
        private String labelName;

        public LabelQueryOption(String labelName) {
            this.labelName = labelName;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        @Override
        public List<Block> toBlockList() {
            return new ListBlockBuilder()
                    .append(Keywords.Key.LABEL)
                    .append(Assignment.ASSIGNMENT)
                    .append(labelName)
                    .build();
        }
    }
}
