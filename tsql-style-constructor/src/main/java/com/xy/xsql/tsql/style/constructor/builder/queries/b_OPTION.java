package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.OptionBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.queries.Option;
import com.xy.xsql.tsql.model.queries.hints.QueryHint;

import java.util.Arrays;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_OPTION extends OptionBuilder<b_OPTION> {

    public b_OPTION() {
        this.in(this);
    }


    /*
    Item
     */

    public b_OPTION $$(b$query_option... query_options) {
        Arrays.asList(query_options)
                .forEach(query_option ->  withItem(query_option.build()));
        return this;
    }


    public static class b$query_option extends CodeBuilder<Option.QueryOption> {

        public b$query_option(QueryHint queryHint) {
            super(queryHint);
        }
        public b$query_option(String labelName) {
            super(new Option.LabelQueryOption(c_string(labelName)));
        }
        public b$query_option(StringConstant labelName) {
            super(new Option.LabelQueryOption(labelName));
        }

    }

}
