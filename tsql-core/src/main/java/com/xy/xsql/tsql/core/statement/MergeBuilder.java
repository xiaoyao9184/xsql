package com.xy.xsql.tsql.core.statement;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.hints.TableHintLimited;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.statement.dml.Merge;
import com.xy.xsql.tsql.model.statement.dml.Update;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2017/1/10.
 */
public class MergeBuilder extends CodeBuilder<Merge> {

    public MergeBuilder(Merge tar) {
        super(tar);
    }

    public MergeBuilder(){
        super(new Merge());
    }


    /**
     *
     * @return
     */
    public WithBuilder<MergeBuilder> withWith(){
        return new WithBuilder<MergeBuilder>
                (initSet(With::new,
                        tar::getWith,
                        tar::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<MergeBuilder> withTop(){
        return new TopBuilder<MergeBuilder>
                (initSet(Top::new,
                        tar::getTop,
                        tar::setTop))
                .in(this);
    }

    /**
     *
     * @param useInto
     * @return
     */
    public MergeBuilder withInto(boolean useInto){
        tar.setUseInto(useInto);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTargetTable(TableName tableName){
        tar.setTargetTable(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTargetTable(String tableName){
        tar.setTargetTable(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public MergeHintBuilder<MergeBuilder> withMergeHint(){
        return new MergeHintBuilder<MergeBuilder>
                (initSet(Merge.MergeHint::new,
                        tar::getMergeHint,
                        tar::setMergeHint))
                .in(this);
    }

    /**
     *
     * @param useAs
     * @return
     */
    public MergeBuilder withAs(boolean useAs){
        tar.setUseAs(useAs);
        return this;
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public MergeBuilder withTableAlias(String tableAlias){
        tar.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     *
     * @return
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> withTableSource() {
        return new FromBuilder.TableSourceBuilder<MergeBuilder>
                (initSet(From.TableSource::new,
                        tar::getTableSource,
                        tar::setTableSource))
                .in(this);
    }

    /**
     *
     * @return
     */
    public SearchConditionBuilder<MergeBuilder> withMergeSearchCondition() {
        return new SearchConditionBuilder<MergeBuilder>
                (initSet(SearchCondition::new,
                        tar::getMergeSearchCondition,
                        tar::setMergeSearchCondition))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhenMatchedThenBuilder<MergeBuilder> withWhenMatchedThen() {
        return new WhenMatchedThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        tar::getMatchedWhenThenList,
                        tar::setMatchedWhenThenList))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> withWhenNotMatchedTargetThen() {
        return new WhenNotMatchedTargetThenBuilder<MergeBuilder>
                (initSet(Merge.NotMatchedWhenThen::new,
                        tar::getNotMatchedWhenThenTarget,
                        tar::setNotMatchedWhenThenTarget))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> withWhenNotMatchedSourceThen() {
        return new WhenNotMatchedSourceThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        tar::getNotMatchedWhenThenSourceList,
                        tar::setNotMatchedWhenThenSourceList))
                .in(this);
    }



    /**
     *
     * @return
     */
    public OutputBuilder<MergeBuilder> withOutput() {
        return new OutputBuilder<MergeBuilder>
                (initSet(Output::new,
                        tar::getOutput,
                        tar::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OptionBuilder<MergeBuilder> withOption() {
        return new OptionBuilder<MergeBuilder>
                (initSet(Option::new,
                        tar::getOption,
                        tar::setOption))
                .in(this);
    }


    /**
     *
     * @return
     */
    public static MergeBuilder MERGE(){
        return new MergeBuilder();
    }

    /**
     * Quick set
     * @return
     */
    public WithBuilder<MergeBuilder> With(){
        return withWith();
    }

    /**
     * Quick set
     * @return
     */
    public TopBuilder<MergeBuilder> $Top(){
        return withTop();
    }

    /**
     * Quick set
     * @param tableName
     * @return
     */
    public MergeBuilder $Into(TableName tableName){
        return withInto(true).withTargetTable(tableName);
    }

    /**
     * Quick set
     * @param mergeHints
     * @return
     */
    @Deprecated
    public MergeBuilder $With(TableHintLimited... mergeHints){
        return withMergeHint()
                .withTableHintLimited(mergeHints)
                .and();
    }

    /**
     * Quick set
     * @return
     */
    public MergeHintBuilder<MergeBuilder> $With(){
        return withMergeHint();
    }

    /**
     * Quick set
     * @param tableAlias
     * @return
     */
    public MergeBuilder $As(String tableAlias){
        return withAs(true).withTableAlias(tableAlias);
    }

    /**
     * Quick set
     * @return
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> $Using(){
        return withTableSource();
    }

    /**
     * Quick set
     * @return
     */
    public SearchConditionBuilder<MergeBuilder> $On(){
        return withMergeSearchCondition();
    }

    /**
     * Quick set
     * @return
     */
    public WhenMatchedThenBuilder<MergeBuilder> $When_Matched(){
        return withWhenMatchedThen();
    }

    /**
     * Quick set
     * @return
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> $When_Not_Matched(){
        return withWhenNotMatchedTargetThen();
    }

    /**
     * Quick set
     * @return
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> $When_Not_Matched_Source(){
        return withWhenNotMatchedSourceThen();
    }

    /**
     * Quick set
     * @return
     */
    public OutputBuilder<MergeBuilder> $OutPut(){
        return withOutput();
    }

    /**
     * Quick set
     * @return
     */
    public OptionBuilder<MergeBuilder> $Option(){
        return withOption();
    }


    /**
     *
     * @param <ParentBuilder>
     */
    public class MergeHintBuilder<ParentBuilder>
            extends CodeTreeBuilder<MergeHintBuilder<ParentBuilder>,ParentBuilder,Merge.MergeHint> {

        public MergeHintBuilder(Merge.MergeHint mergeHint) {
            super(mergeHint);
        }

        public MergeHintBuilder<ParentBuilder> withTableHintLimited(TableHintLimited... tableHintLimited){
            initAdd(Arrays.asList(tableHintLimited),
                    tar::getTableHintLimitedList,
                    tar::setTableHintLimitedList);
            return this;
        }

        public MergeHintBuilder<ParentBuilder> withNull(Boolean useDelimiter){
            tar.setUseDelimiter(useDelimiter);
            return this;
        }

        public MergeHintBuilder<ParentBuilder> withTableHintLimited(String... valueVals){
            initAdd(Arrays.stream(valueVals)
                            .map(Unknown::new)
                            .collect(Collectors.toList()),
                    tar::getIndexValList,
                    tar::setIndexValList);
            return this;
        }
    }

    /**
     *
     * @param <ParentBuilder>
     */
    public class WhenMatchedThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenMatchedThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public WhenMatchedThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            super(matchedWhenThen);
        }


        public SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            tar::getClauseSearchCondition,
                            tar::setClauseSearchCondition))
                    .in(this);
        }

        public MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>> withMergeMatched() {
            return new MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeMatched::new,
                            tar::getMergeMatched,
                            tar::setMergeMatched))
                    .in(this);
        }

    }

    /**
     *
     * @param <ParentBuilder>
     */
    public class WhenNotMatchedTargetThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>,ParentBuilder,Merge.NotMatchedWhenThen> {

        public WhenNotMatchedTargetThenBuilder(Merge.NotMatchedWhenThen notMatchedWhenThen) {
            super(notMatchedWhenThen);
        }


        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withByTarget(boolean useByTarget){
            tar.setUseByTarget(useByTarget);
            return this;
        }

        public SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            tar::getClauseSearchCondition,
                            tar::setClauseSearchCondition))
                    .in(this);
        }

        public MergeNotMatchedBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeNotMatched::new,
                            tar::getMergeNotMatched,
                            tar::setMergeNotMatched))
                    .in(this);
        }

    }

    /**
     *
     * @param <ParentBuilder>
     */
    public class WhenNotMatchedSourceThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public WhenNotMatchedSourceThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            super(matchedWhenThen);
        }

        public SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            tar::getClauseSearchCondition,
                            tar::setClauseSearchCondition))
                    .in(this);
        }

        public MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeNotMatched::new,
                            tar::getMergeNotMatched,
                            tar::setMergeNotMatched))
                    .in(this);
        }

    }

    /**
     *
     * @param <ParentBuilder>
     */
    public class MergeMatchedBuilder<ParentBuilder>
            extends CodeTreeBuilder<MergeMatchedBuilder<ParentBuilder>,ParentBuilder,Merge.MergeMatched> {


        public MergeMatchedBuilder(Merge.MergeMatched mergeMatched) {
            super(mergeMatched);
        }


        public MergeMatchedBuilder<ParentBuilder> withSet(boolean useSet){
            tar.setUseSet(useSet);
            return this;
        }

        public UpdateBuilder.SetItemBuilder<MergeMatchedBuilder<ParentBuilder>> withSetItem(){
            initList(tar::getSets,
                    tar::setSets);
            return new UpdateBuilder.SetItemBuilder<MergeMatchedBuilder<ParentBuilder>>
                    (tar.getSets()::add)
                    .in(this);
        }

    }

    /**
     *
     * @param <ParentBuilder>
     */
    public class MergeNotMatchedBuilder<ParentBuilder>
            extends CodeTreeBuilder<MergeNotMatchedBuilder<ParentBuilder>,ParentBuilder,Merge.MergeNotMatched> {


        public MergeNotMatchedBuilder(Merge.MergeNotMatched mergeNotMatched) {
            super(mergeNotMatched);
        }


        /**
         *
         * @return
         */
        public MergeNotMatchedBuilder<ParentBuilder> withColumn(ColumnName... columnName){
            initAdd(Arrays.asList(columnName),
                    tar::getColumns,
                    tar::setColumns);
            return this;
        }

        /**
         *
         * @return
         */
        public TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>> withValues(){
            return new TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>>
                    (initSet(TableValueConstructor::new,
                            tar::getValues,
                            tar::setValues))
                    .in(this);
        }

    }
}
