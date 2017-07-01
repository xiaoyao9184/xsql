package com.xy.xsql.tsql.core.statement.dml;

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
import com.xy.xsql.util.CheckUtil;

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
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    /**
     *
     * @return
     */
    public TopBuilder<MergeBuilder> withTop(){
        return new TopBuilder<MergeBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    /**
     *
     * @param useInto
     * @return
     */
    public MergeBuilder withInto(boolean useInto){
        target.setUseInto(useInto);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTargetTable(TableName tableName){
        target.setTargetTable(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTargetTable(String tableName){
        target.setTargetTable(new TableName(tableName));
        return this;
    }

    /**
     *
     * @return
     */
    public MergeHintBuilder<MergeBuilder> withMergeHint(){
        return new MergeHintBuilder<MergeBuilder>
                (initSet(Merge.MergeHint::new,
                        target::getMergeHint,
                        target::setMergeHint))
                .in(this);
    }

    /**
     *
     * @param useAs
     * @return
     */
    public MergeBuilder withAs(boolean useAs){
        target.setUseAs(useAs);
        return this;
    }

    /**
     *
     * @param tableAlias
     * @return
     */
    public MergeBuilder withTableAlias(String tableAlias){
        target.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     *
     * @return
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> withTableSource() {
        return new FromBuilder.TableSourceBuilder<MergeBuilder>
                (target::setTableSource)
                .in(this);
    }

    /**
     *
     * @return
     */
    public SearchConditionBuilder<MergeBuilder> withMergeSearchCondition() {
        return new SearchConditionBuilder<MergeBuilder>
                (initSet(SearchCondition::new,
                        target::getMergeSearchCondition,
                        target::setMergeSearchCondition))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhenMatchedThenBuilder<MergeBuilder> withWhenMatchedThen() {
        return new WhenMatchedThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        target::getMatchedWhenThenList,
                        target::setMatchedWhenThenList))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> withWhenNotMatchedTargetThen() {
        return new WhenNotMatchedTargetThenBuilder<MergeBuilder>
                (initSet(Merge.NotMatchedWhenThen::new,
                        target::getNotMatchedWhenThenTarget,
                        target::setNotMatchedWhenThenTarget))
                .in(this);
    }

    /**
     *
     * @return
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> withWhenNotMatchedSourceThen() {
        return new WhenNotMatchedSourceThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        target::getNotMatchedWhenThenSourceList,
                        target::setNotMatchedWhenThenSourceList))
                .in(this);
    }



    /**
     *
     * @return
     */
    public OutputBuilder<MergeBuilder> withOutput() {
        return new OutputBuilder<MergeBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    /**
     *
     * @return
     */
    public OptionBuilder<MergeBuilder> withOption() {
        return new OptionBuilder<MergeBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
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
    public MergeBuilder $(TableName tableName){
        return withTargetTable(tableName);
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
     * TODO CARETE MergeBuilder.TableSourceBuilder
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
            if(CheckUtil.isNullOrEmpty(tableHintLimited)){
                return this;
            }
            initAdd(Arrays.asList(tableHintLimited),
                    target::getTableHintLimitedList,
                    target::setTableHintLimitedList);
            return this;
        }

        public MergeHintBuilder<ParentBuilder> withNull(Boolean useDelimiter){
            target.setUseDelimiter(useDelimiter);
            return this;
        }

        public MergeHintBuilder<ParentBuilder> withTableHintLimited(String... valueVals){
            if(CheckUtil.isNullOrEmpty(valueVals)){
                return this;
            }
            initAdd(Arrays.stream(valueVals)
                            .map(Unknown::new)
                            .collect(Collectors.toList()),
                    target::getIndexValList,
                    target::setIndexValList);
            return this;
        }

        /*
        TODO Quick
         */
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
                            target::getClauseSearchCondition,
                            target::setClauseSearchCondition))
                    .in(this);
        }

        public MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>> withMergeMatched() {
            return new MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeMatched::new,
                            target::getMergeMatched,
                            target::setMergeMatched))
                    .in(this);
        }



        /*
        Quick
         */

        /**
         * Quick into
         * @return
         */
        public SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>> $() {
            return withClauseSearchCondition();
        }

        /**
         * Quick into
         * @return
         */
        public MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>> $Then() {
            return withMergeMatched();
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
            target.setUseByTarget(useByTarget);
            return this;
        }

        public SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            target::getClauseSearchCondition,
                            target::setClauseSearchCondition))
                    .in(this);
        }

        public MergeNotMatchedBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeNotMatched::new,
                            target::getMergeNotMatched,
                            target::setMergeNotMatched))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick into
         * @return
         */
        public SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> $() {
            return withClauseSearchCondition();
        }

        /**
         * Quick into
         * @return
         */
        public MergeNotMatchedBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> $Then() {
            return withMergeNotMatched();
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
                            target::getClauseSearchCondition,
                            target::setClauseSearchCondition))
                    .in(this);
        }

        public MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeNotMatched::new,
                            target::getMergeNotMatched,
                            target::setMergeNotMatched))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick into
         * @return
         */
        public SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> $() {
            return withClauseSearchCondition();
        }

        /**
         * Quick into
         * @return
         */
        public MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> $Then() {
            return withMergeNotMatched();
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
            target.setUseSet(useSet);
            return this;
        }

        public UpdateBuilder.SetItemBuilder<MergeMatchedBuilder<ParentBuilder>> withSetItem(){
            withSet(true);
            initList(target::getSets,
                    target::setSets);
            return new UpdateBuilder.SetItemBuilder<MergeMatchedBuilder<ParentBuilder>>
                    (target.getSets()::add)
                    .in(this);
        }

        
        /*
        Quick
         */

        /**
         * Quick set
         * @return
         */
        public ParentBuilder $Delete() {
            return withSet(false)
                    .and();
        }

        /**
         * Quick set
         * @param items
         * @return
         */
        public ParentBuilder $Update_Set(Update.SetItem... items) {
            if(CheckUtil.isNullOrEmpty(items)){
                return and();
            }
            initAdd(Arrays.asList(items),
                    target::getSets,
                    target::setSets);
            return withSet(true)
                    .and();
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


        public MergeNotMatchedBuilder<ParentBuilder> withColumn(ColumnName... columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(Arrays.asList(columnNames),
                    target::getColumns,
                    target::setColumns);
            return this;
        }

        public TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>> withValues(){
            return new TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>>
                    (initSet(TableValueConstructor::new,
                            target::getValues,
                            target::setValues))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick set columnNames
         * @param columnNames
         * @return
         */
        public MergeNotMatchedBuilder<ParentBuilder> $Insert(ColumnName... columnNames){
            return withColumn(columnNames);
        }

        /**
         * Quick set
         * @return
         */
        public TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>> $Values() {
            return withValues();
        }
    }
}
