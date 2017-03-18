package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.CodeTreeBuilder;
import com.xy.xsql.core.Setter;
import com.xy.xsql.tsql.model.clause.Option;
import com.xy.xsql.tsql.model.clause.hints.QueryHint;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

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
        initList(this.tar::getQueryOption,
                this.tar::setQueryOption);
        return new QueryOptionBuilder<OptionBuilder<ParentBuilder>>
                (tar.getQueryOption()::add)
                .in(this);
    }


    /**
     * Quick inout set QueryOptionBuilder' queryHint
     * @param queryHint
     * @return
     */
    public OptionBuilder<ParentBuilder> $(QueryHint... queryHint){
        return withItem()
                ._QueryHint(queryHint)
                .and();
    }

    /**
     * Quick inout set QueryOptionBuilder' labelName
     * @param labelName
     * @return
     */
    public OptionBuilder<ParentBuilder> $(String labelName){
        return withItem()
                ._LabelName(labelName)
                .and();
    }


//    /**
//     * Abstract <query_option> Builder
//     * @param <ParentBuilder>
//     */
//    public static class QueryOptionBuilder<ParentBuilder>
//            extends CodeTreeBuilder<QueryOptionBuilder<ParentBuilder>,ParentBuilder,Option.QueryOption> {
//
//        private Setter<Option.QueryOption> setter;
//
//        public QueryOptionBuilder(Option.QueryOption tar) {
//            super(tar);
//        }
//
//        public QueryOptionBuilder(Setter<Option.QueryOption> setter) {
//            super(null);
//            this.setter = setter;
//        }
//
//        public QueryOptionBuilder<ParentBuilder> _LabelName(String labelName){
//            tar = new Option.LabelQueryOption(labelName);
//            this.setter.set(tar);
//            return this;
//        }
//
//        public QueryOptionBuilder<ParentBuilder> _QueryHint(QueryHint... queryHints){
//            Arrays.asList(queryHints)
//                    .forEach((queryHint)-> this.setter.set(queryHint));
//            return this;
//        }
//    }

    /**
     * Abstract <query_option> Builder
     * @param <ParentBuilder>
     */
    public static class QueryOptionBuilder<ParentBuilder>
            extends CodeTreeBuilder<QueryOptionBuilder<ParentBuilder>,ParentBuilder,Setter<Option.QueryOption>> {

        public QueryOptionBuilder(Setter<Option.QueryOption> tar) {
            super(tar);
        }

        public QueryOptionBuilder<ParentBuilder> _LabelName(String labelName){
            this.tar.set(new Option.LabelQueryOption(labelName));
            return this;
        }

        public QueryOptionBuilder<ParentBuilder> _QueryHint(QueryHint... queryHints){
            Arrays.asList(queryHints)
                    .forEach((queryHint)-> this.tar.set(queryHint));
            return this;
        }
    }

}
