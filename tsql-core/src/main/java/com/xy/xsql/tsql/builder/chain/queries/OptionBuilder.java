package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.queries.Option;
import com.xy.xsql.tsql.model.queries.hints.QueryHint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * OptionBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess","unused"})
public class OptionBuilder<ParentBuilder>
        extends ParentHoldBuilder<OptionBuilder<ParentBuilder>,ParentBuilder,Option> {

    public OptionBuilder() {
        super(new Option());
    }

    public OptionBuilder(Option target) {
        super(target);
    }


    /**
     * set
     * @param queryOption QueryOption
     * @return THIS
     */
    public OptionBuilder<ParentBuilder> withItem(Option.QueryOption queryOption){
        list(target::getQueryOption, target::setQueryOption)
                .add(queryOption);
        return this;
    }

    /**
     * in
     * @return QueryOptionBuilder
     */
    public QueryOptionBuilder<OptionBuilder<ParentBuilder>> withItem(){
        list(this.target::getQueryOption, this.target::setQueryOption).init();
        return new QueryOptionBuilder<OptionBuilder<ParentBuilder>>
                (target.getQueryOption())
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
            extends ParentHoldBuilder<QueryOptionBuilder<ParentBuilder>,ParentBuilder,List<Option.QueryOption>> {

        public QueryOptionBuilder() {
            super(new ArrayList<>());
        }

        public QueryOptionBuilder(List<Option.QueryOption> target) {
            super(target);
        }

        /**
         * Confirm type of QueryOption
         * And build LabelQueryOption
         * @param labelName label name
         * @return THIS
         */
        public QueryOptionBuilder<ParentBuilder> _LabelName(String labelName){
            target.add(new Option.LabelQueryOption(c_string(labelName)));
            return this;
        }

        /**
         * set
         * @param labelName label name
         * @return THIS
         */
        public QueryOptionBuilder<ParentBuilder> _LabelName(StringConstant labelName) {
            target.add(new Option.LabelQueryOption(labelName));
            return this;
        }

        /**
         * Confirm type of QueryOption
         * And build multiple QueryHint
         * @param queryHints QueryHint
         * @return THIS
         */
        public QueryOptionBuilder<ParentBuilder> _QueryHint(QueryHint... queryHints){
            target.addAll(Arrays.asList(queryHints));
            return this;
        }
    }

}
