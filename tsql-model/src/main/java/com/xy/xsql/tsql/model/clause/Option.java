package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;
import com.xy.xsql.tsql.model.datatype.StringConstant;
import com.xy.xsql.tsql.model.element.Other;
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
        ListBlockBuilder b = new ListBlockBuilder();

        if(!CheckUtil.isNullOrEmpty(this.queryOption)){
            b.append(Keywords.OPTION);
            for (QueryOption queryOption : this.queryOption) {
                if(!CheckUtil.isNullOrEmpty(queryOption.getLabelName())){
                    b.append(Keywords.Key.LABEL)
                            .append(Other.SPACE)
                            .append(Operators.EQUAL)
                            .append(Other.SPACE)
                            .append(new StringConstant(queryOption.getLabelName()));
                }else {
                    b.append(queryOption.getQueryHint().toBlockList(),null);
                }
            }
        }
        return b.build(null);
    }


    /**
     * <query_option>
     */
    public static class QueryOption {
        //label_name
        private String labelName;
        //<query_hint>
        private QueryHint queryHint;


        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public QueryHint getQueryHint() {
            return queryHint;
        }

        public void setQueryHint(QueryHint queryHint) {
            this.queryHint = queryHint;
        }
    }
}
