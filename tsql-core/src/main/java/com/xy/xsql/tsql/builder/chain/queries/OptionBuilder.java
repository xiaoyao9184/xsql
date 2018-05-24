package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.queries.Option;
import com.xy.xsql.tsql.model.queries.hints.QueryHint;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * OptionBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings("WeakerAccess")
public class OptionBuilder<ParentBuilder>
        extends CodeTreeBuilder<OptionBuilder<ParentBuilder>,ParentBuilder,Option> {

    public OptionBuilder() {
        super(new Option());
    }

    public OptionBuilder(Option option) {
        super(option);
    }


    /**
     * set
     * @param queryOption QueryOption
     * @return THIS
     */
    public OptionBuilder<ParentBuilder> withItem(Option.QueryOption queryOption){
        initAdd(queryOption,
                this.target::getQueryOption,
                this.target::setQueryOption);
        return this;
    }

    /**
     * in
     * @return QueryOptionBuilder
     */
    public QueryOptionBuilder<OptionBuilder<ParentBuilder>> withItem(){
        initList(this.target::getQueryOption,
                this.target::setQueryOption);
        return new QueryOptionBuilder<OptionBuilder<ParentBuilder>>
                (target.getQueryOption()::add)
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick set QueryOption:QueryHint
     * into QueryOptionBuilder and get-out
     * @param queryHint QueryHint
     * @return THIS
     */
    public OptionBuilder<ParentBuilder> $(QueryHint... queryHint){
        return withItem()
                ._QueryHint(queryHint)
                .and();
    }

    /**
     * Quick set QueryOption:LabelQueryOption
     * into QueryOptionBuilder and get-out
     * @param labelName label name
     * @return THIS
     */
    public OptionBuilder<ParentBuilder> $(String labelName){
        return withItem()
                ._LabelName(labelName)
                .and();
    }

    /**
     * uick set QueryOption:LabelQueryOption
     * into QueryOptionBuilder and get-out
     * @param labelName label name
     * @return THIS
     */
    public OptionBuilder $(StringConstant labelName) {
        return withItem()
                ._LabelName(labelName)
                .and();
    }


    /**
     * Abstract QueryOption Builder
     * @param <ParentBuilder>
     */
    public static class QueryOptionBuilder<ParentBuilder>
            extends CodeTreeBuilder<QueryOptionBuilder<ParentBuilder>,ParentBuilder,Setter<Option.QueryOption>> {

        public QueryOptionBuilder(Setter<Option.QueryOption> tar) {
            super(tar);
        }

        /**
         * Confirm type of QueryOption
         * And build LabelQueryOption
         * @param labelName label name
         * @return THIS
         */
        public QueryOptionBuilder<ParentBuilder> _LabelName(String labelName){
            target.set(new Option.LabelQueryOption(c_string(labelName)));
            return this;
        }

        /**
         * set
         * @param labelName label name
         * @return THIS
         */
        public QueryOptionBuilder<ParentBuilder> _LabelName(StringConstant labelName) {
            target.set(new Option.LabelQueryOption(labelName));
            return this;
        }

        /**
         * Confirm type of QueryOption
         * And build multiple QueryHint
         * @param queryHints QueryHint
         * @return THIS
         */
        public QueryOptionBuilder<ParentBuilder> _QueryHint(QueryHint... queryHints){
            Arrays.asList(queryHints)
                    .forEach((queryHint)-> target.set(queryHint));
            return this;
        }
    }

}
