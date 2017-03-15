package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.BaseBuilder;
import com.xy.xsql.core.SubBuilder;
import com.xy.xsql.tsql.core.clause.FromBuilder;
import com.xy.xsql.tsql.core.clause.SearchConditionBuilder;
import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;
import com.xy.xsql.tsql.core.clause.TopBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import com.xy.xsql.tsql.model.statement.dml.Update;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;

/**
 * Created by xiaoyao9184 on 2017/1/10.
 */
public class MergeBuilder implements BaseBuilder<Void,Merge> {

    private Merge merge;

    public MergeBuilder(){
        this.merge = new Merge();
    }


    @Override
    public Merge build(Void aVoid) {
        return merge;
    }


    /**
     *
     * @return
     */
    public TopBuilder<MergeBuilder> withTop(){
        Top top = new Top();
        merge.setTop(top);
        return new TopBuilder<MergeBuilder>(top)
                .in(this);
    }

    /**
     *
     * @param useInto
     * @return
     */
    public MergeBuilder withInto(boolean useInto){
        merge.setUseInto(useInto);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTableName(TableName tableName){
        merge.setTargetTable(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTableName(String tableName){
        merge.setTargetTable(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public MergeHintBuilder<MergeBuilder> withMergeHint(){
        Merge.MergeHint mergeHint = new Merge.MergeHint();
        merge.setMergeHint(mergeHint);
        return new MergeHintBuilder<MergeBuilder>(mergeHint)
                .in(this);
    }

    /**
     *
     * @param useAs
     * @return
     */
    public MergeBuilder withAs(boolean useAs){
        merge.setUseAs(useAs);
        return this;
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public MergeBuilder withTableAlias(String tableAlias){
        merge.setTableAlias(new Alias<Void>(tableAlias));
        return this;
    }

    /**
     *
     * @return
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> withTableSource() {
        From.TableSource tableSource = new From.TableSource();
        merge.setTableSource(tableSource);
        return new FromBuilder.TableSourceBuilder<MergeBuilder>(tableSource)
                .in(this);
    }

    /**
     *
     * @return
     */
    public SearchConditionBuilder<MergeBuilder> withMergeSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        merge.setMergeSearchCondition(searchCondition);
        return new SearchConditionBuilder<MergeBuilder>(searchCondition)
                .in(this);
    }

    /**
     *
     * @return
     */
    public MatchedWhenThenListBuilder<MergeBuilder> withMatchedWhenThenList() {
        List<Merge.MatchedWhenThen> matchedWhenThenList = new ArrayList<>();
        merge.setMatchedWhenThenList(matchedWhenThenList);
        return new MatchedWhenThenListBuilder<MergeBuilder>(matchedWhenThenList)
                .in(this);
    }

    /**
     *
     * @return
     */
    public MatchedNotWhenThenBuilder<MergeBuilder> withNotMatchedWhenThenTarget() {
        Merge.MatchedNotWhenThen matchedWhenThen = new Merge.MatchedNotWhenThen();
        merge.setNotMatchedWhenThenTarget(matchedWhenThen);
        return new MatchedNotWhenThenBuilder<MergeBuilder>(matchedWhenThen)
                .in(this);
    }

    /**
     *
     * @return
     */
    public MatchedWhenThenListBuilder<MergeBuilder> withNotMatchedWhenThenSourceList() {
        List<Merge.MatchedWhenThen> matchedWhenThenList = new ArrayList<>();
        merge.setNotMatchedWhenThenSourceList(matchedWhenThenList);
        return new MatchedWhenThenListBuilder<MergeBuilder>(matchedWhenThenList)
                .in(this);
    }


    /**
     *
     * @param <Done>
     */
    public class MergeHintBuilder<Done>
            extends SubBuilder<MergeHintBuilder<Done>,Void,Done> {

        private Merge.MergeHint mergeHint;

        public MergeHintBuilder(Merge.MergeHint mergeHint) {
            this.mergeHint = mergeHint;
        }

        public MergeHintBuilder<Done> withTableHintLimited(TableHintLimited tableHintLimited){
            if(this.mergeHint.getTableHintLimitedList() == null){
                this.mergeHint.setTableHintLimitedList(EnumSet.allOf(TableHintLimited.class));
            }
            this.mergeHint.getTableHintLimitedList().add(tableHintLimited);
            return this;
        }

        public MergeHintBuilder<Done> withNull(Boolean useDelimiter){
            this.mergeHint.setUseDelimiter(useDelimiter);
            return this;
        }

        public MergeHintBuilder<Done> withTableHintLimited(String valueVal){
            if(this.mergeHint.getIndexValList() == null){
                this.mergeHint.setIndexValList(new ArrayList());
            }
            this.mergeHint.getIndexValList().add(new Unknown(valueVal));
            return this;
        }
    }

    /**
     *
     * @param <Done>
     */
    public class MatchedWhenThenListBuilder<Done>
            extends SubBuilder<MatchedWhenThenListBuilder<Done>,Void,Done> {

        private List<Merge.MatchedWhenThen> matchedWhenThenList;

        public MatchedWhenThenListBuilder(List<Merge.MatchedWhenThen> matchedWhenThenList) {
            this.matchedWhenThenList = matchedWhenThenList;
        }

        public MatchedWhenThenBuilder<MatchedWhenThenListBuilder<Done>> withItem(){
            Merge.MatchedWhenThen matchedWhenThen = new Merge.MatchedWhenThen();
            if(this.matchedWhenThenList == null){
                this.matchedWhenThenList = new ArrayList<>();
            }
            this.matchedWhenThenList.add(matchedWhenThen);
            return new MatchedWhenThenBuilder<MatchedWhenThenListBuilder<Done>>(matchedWhenThen)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public class MatchedWhenThenBuilder<Done>
            extends SubBuilder<MatchedWhenThenBuilder<Done>,Void,Done> {

        private Merge.MatchedWhenThen matchedWhenThen;

        public MatchedWhenThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            this.matchedWhenThen = matchedWhenThen;
        }


        public SearchConditionBuilder<MatchedWhenThenBuilder<Done>> withClauseSearchCondition() {
            SearchCondition clauseSearchCondition = new SearchCondition();
            matchedWhenThen.setClauseSearchCondition(clauseSearchCondition);
            return new SearchConditionBuilder<MatchedWhenThenBuilder<Done>>(clauseSearchCondition)
                    .in(this);
        }

        public MergeMatchedBuilder<MatchedWhenThenBuilder<Done>> withMergeMatched() {
            Merge.MergeMatched mergeMatched = new Merge.MergeMatched();
            matchedWhenThen.setMergeMatched(mergeMatched);
            return new MergeMatchedBuilder<MatchedWhenThenBuilder<Done>>(mergeMatched)
                    .in(this);
        }

    }

    /**
     *
     * @param <Done>
     */
    public class MatchedNotWhenThenBuilder<Done>
            extends SubBuilder<MatchedNotWhenThenBuilder<Done>,Void,Done> {

        private Merge.MatchedNotWhenThen matchedNotWhenThen;

        public MatchedNotWhenThenBuilder(Merge.MatchedNotWhenThen matchedNotWhenThen) {
            this.matchedNotWhenThen = matchedNotWhenThen;
        }


        public MatchedNotWhenThenBuilder<Done> withByTarget(boolean useByTarget){
            this.matchedNotWhenThen.setUseByTarget(useByTarget);
            return this;
        }

        public SearchConditionBuilder<MatchedNotWhenThenBuilder<Done>> withClauseSearchCondition() {
            SearchCondition clauseSearchCondition = new SearchCondition();
            matchedNotWhenThen.setClauseSearchCondition(clauseSearchCondition);
            return new SearchConditionBuilder<MatchedNotWhenThenBuilder<Done>>(clauseSearchCondition)
                    .in(this);
        }

        public MergeNotMatchedBuilder<MatchedNotWhenThenBuilder<Done>> withMergeNotMatched() {
            Merge.MergeNotMatched mergeNotMatched = new Merge.MergeNotMatched();
            matchedNotWhenThen.setMergeNotMatched(mergeNotMatched);
            return new MergeNotMatchedBuilder<MatchedNotWhenThenBuilder<Done>>(mergeNotMatched)
                    .in(this);
        }

    }

    /**
     *
     * @param <Done>
     */
    public class MergeMatchedBuilder<Done>
            extends SubBuilder<MergeMatchedBuilder<Done>,Void,Done> {

        private Merge.MergeMatched mergeMatched;

        public MergeMatchedBuilder(Merge.MergeMatched mergeMatched) {
            this.mergeMatched = mergeMatched;
        }


        public MergeMatchedBuilder<Done> withSet(boolean useSet){
            this.mergeMatched.setUseSet(useSet);
            return this;
        }

        public UpdateBuilder.SetListBuilder<MergeMatchedBuilder<Done>> withSetList(){
            List<Update.Set> setList = new ArrayList<>();
            mergeMatched.setSets(setList);
            return new UpdateBuilder.SetListBuilder<MergeMatchedBuilder<Done>>(setList)
                    .in(this);
        }

    }

    /**
     *
     * @param <Done>
     */
    public class MergeNotMatchedBuilder<Done>
            extends SubBuilder<MergeNotMatchedBuilder<Done>,Void,Done> {

        private Merge.MergeNotMatched mergeNotMatched;

        public MergeNotMatchedBuilder(Merge.MergeNotMatched mergeNotMatched) {
            this.mergeNotMatched = mergeNotMatched;
        }



        /**
         *
         * @return
         */
        public MergeNotMatchedBuilder<Done> withColumn(ColumnName columnName){
            initAdd(columnName,
                    mergeNotMatched::getColumns,
                    mergeNotMatched::setColumns);
            return this;
        }

        /**
         *
         * @return
         */
        public TableValueConstructorBuilder<MergeNotMatchedBuilder<Done>> withValues(){
            return new TableValueConstructorBuilder<MergeNotMatchedBuilder<Done>>
                    (initSet(TableValueConstructor::new,
                            mergeNotMatched::getValues,
                            mergeNotMatched::setValues))
                    .in(this);
        }

    }
}
