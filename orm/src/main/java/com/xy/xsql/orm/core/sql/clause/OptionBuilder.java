package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.orm.core.sql.clause.hints.QueryHintBuilder;
import com.xy.xsql.orm.data.sql.clause.Option;
import com.xy.xsql.orm.data.sql.clause.hints.QueryHint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class OptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<OptionBuilder<ParentBuilder>,ParentBuilder,Option> {

    public OptionBuilder() {
        super(new Option());
    }

    public OptionBuilder(Option option) {
        super(option);
    }


    public QueryOptionBuilder<OptionBuilder<ParentBuilder>> withItem(){
        return new QueryOptionBuilder<OptionBuilder<ParentBuilder>>
                (initNew(Option.QueryOption::new,
                        this.tar::getQueryOption,
                        this.tar::setQueryOption))
                .in(this);
    }

    public OptionBuilder<ParentBuilder> withItemLabelName(String labelName){
        Option.QueryOption queryOption = new Option.QueryOption();
        queryOption.setLabelName(labelName);
        initAdd(queryOption,
                this.tar::getQueryOption,
                this.tar::setQueryOption);
        return this;
    }

    public QueryHintBuilder<OptionBuilder<ParentBuilder>> withItemQueryHint(){
        QueryHint queryHint = new QueryHint();
        Option.QueryOption queryOption = new Option.QueryOption();
        queryOption.setQueryHint(queryHint);
        initAdd(queryOption,
                this.tar::getQueryOption,
                this.tar::setQueryOption);
        return new QueryHintBuilder<OptionBuilder<ParentBuilder>>
                (queryHint)
                .in(this);
    }

    public OptionBuilder<ParentBuilder> withItemQueryHint(QueryHint queryHint){
        Option.QueryOption queryOption = new Option.QueryOption();
        queryOption.setQueryHint(queryHint);
        initAdd(queryOption,
                this.tar::getQueryOption,
                this.tar::setQueryOption);
        return this;
    }

    public OptionBuilder<ParentBuilder> withItemQueryHint(QueryHint... queryHint){
        List<Option.QueryOption> list =
                Arrays.stream(queryHint)
                        .map((qh) -> {
                            Option.QueryOption queryOption = new Option.QueryOption();
                            queryOption.setQueryHint(qh);
                            return queryOption;
                        }).collect(Collectors.toList());
        initAdd(list,
                this.tar::getQueryOption,
                this.tar::setQueryOption);
        return this;
    }



    @Deprecated
    public static class QueryOptionBuilder<ParentBuilder>
            extends CodeTreeBuilder<QueryOptionBuilder<ParentBuilder>,ParentBuilder,Option.QueryOption> {

        public QueryOptionBuilder(Option.QueryOption tar) {
            super(tar);
        }

        public QueryOptionBuilder<ParentBuilder> withLabelName(String labelName){
            tar.setLabelName(labelName);
            return this;
        }

        public QueryHintBuilder<QueryOptionBuilder<ParentBuilder>> withQueryHint(){
            QueryHint queryHint = new QueryHint();
            tar.setQueryHint(queryHint);
            return new QueryHintBuilder<QueryOptionBuilder<ParentBuilder>>
                    (queryHint)
                    .in(this);
        }

    }


}
