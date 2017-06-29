package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.datatype.StringConstant;

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


    /**
     * <query_option>
     */
    public interface QueryOption {

    }

    /**
     * <query_option>
     *      LABEL = label_name
     */
    public static class LabelQueryOption implements QueryOption {

        //label_name
        private StringConstant labelName;

        public LabelQueryOption(String labelName) {
            this.labelName = new StringConstant(labelName).withQuote();
        }

        public StringConstant getLabelName() {
            return labelName;
        }

        public void setLabelName(StringConstant labelName) {
            this.labelName = labelName;
        }

    }
}
