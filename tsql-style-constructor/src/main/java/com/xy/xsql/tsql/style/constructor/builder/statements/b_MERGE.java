package com.xy.xsql.tsql.style.constructor.builder.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.FromBuilder;
import com.xy.xsql.tsql.builder.chain.queries.UpdateBuilder;
import com.xy.xsql.tsql.builder.chain.statements.MergeBuilder;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.statements.Merge;
import com.xy.xsql.tsql.style.constructor.builder.queries.b$search_condition;

import java.util.ArrayList;
import java.util.List;

import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_MERGE extends MergeBuilder {

    /**
     * same as
     * @see b_INSERT.k_INTO
     * @see b_MERGE.k_INTO
     */
    public static class k_INTO {}

    public static class b_WITH extends MergeHintBuilder<b_WITH> {

        public b_WITH() {
            this.in(this);
        }

    }

    public static class b_USING extends FromBuilder.TableSourceBuilder<b_USING> {

        public b_USING() {
            this.in(this);
        }

    }

    public static class b_WHEN_MATCHED extends CodeBuilder<List<Merge.MatchedWhenThen>> {

        public b_WHEN_MATCHED() {
            super(new ArrayList<>());
        }

        public WhenMatchedThenBuilder<b_WHEN_MATCHED> withItem(){
            return new WhenMatchedThenBuilder<b_WHEN_MATCHED>
                    (object(Merge.MatchedWhenThen::new).set(target::add))
                    .in(this);
        }


        /*
        Item
         */

        public b_WHEN_MATCHED WHEN_MATCHED(
                b$search_condition.b_AND and,
                b_THEN_UPDATE_DELETE then){
            return withItem()
                    .withClauseSearchCondition(and.build())
                    .withMergeMatched(then.build())
                    .and();
        }

        public b_WHEN_MATCHED WHEN_MATCHED(
                b_THEN_UPDATE_DELETE then){
            return withItem()
                    .withMergeMatched(then.build())
                    .and();
        }

    }

    public static class b_WHEN_NOT_MATCHED extends WhenNotMatchedTargetThenBuilder<b_WHEN_NOT_MATCHED> {

        public b_WHEN_NOT_MATCHED() {
            this.in(this);
        }

    }

    public static class b_WHEN_NOT_MATCHED_BY_SOURCE extends CodeBuilder<List<Merge.MatchedWhenThen>> {

        public b_WHEN_NOT_MATCHED_BY_SOURCE() {
            super(new ArrayList<>());
        }

        public WhenMatchedThenBuilder<b_WHEN_NOT_MATCHED_BY_SOURCE> withItem(){
            return new WhenMatchedThenBuilder<b_WHEN_NOT_MATCHED_BY_SOURCE>
                    (object(Merge.MatchedWhenThen::new).set(target::add))
                    .in(this);
        }


        /*
        Item
         */

        public b_WHEN_NOT_MATCHED_BY_SOURCE WHEN_NOT_MATCHED_BY_SOURCE(
                b$search_condition.b_AND and,
                b_THEN_UPDATE_DELETE then){
            return withItem()
                    .withClauseSearchCondition(and.build())
                    .withMergeMatched(then.build())
                    .and();
        }

        public b_WHEN_NOT_MATCHED_BY_SOURCE WHEN_NOT_MATCHED_BY_SOURCE(
                b_THEN_UPDATE_DELETE then){
            return withItem()
                    .withMergeMatched(then.build())
                    .and();
        }
    }

    public static class b_THEN_UPDATE_DELETE extends MergeMatchedBuilder<b_THEN_UPDATE_DELETE> {

        public b_THEN_UPDATE_DELETE() {
            this.in(this);
        }

        public static class b_UPDATE extends UpdateBuilder.SetListBuilder<com.xy.xsql.tsql.style.constructor.builder.queries.b_UPDATE.b_SET> {}

        public static class k_DELETE {}

    }

    public static class b_THEN_INSERT extends MergeNotMatchedBuilder<b_THEN_INSERT> {

        public b_THEN_INSERT() {
            this.in(this);
        }

    }

}
