package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.datatypes.Constants;
import com.xy.xsql.tsql.builder.chain.queries.*;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.*;
import com.xy.xsql.tsql.model.statements.Merge;
import com.xy.xsql.tsql.model.queries.Update;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * MergeBuilder
 * Created by xiaoyao9184 on 2017/1/10.
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class MergeBuilder extends CodeBuilder<Merge> {

    public MergeBuilder(Merge tar) {
        super(tar);
    }

    public MergeBuilder(){
        super(new Merge());
    }


    /**
     * set
     * @param with With
     * @return THIS
     */
    public MergeBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<MergeBuilder> withWith(){
        return new WithBuilder<MergeBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    /**
     * set
     * @param top Top
     * @return THIS
     */
    public MergeBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
    }

    /**
     * in
     * @return TopBuilder
     */
    public TopBuilder<MergeBuilder> withTop(){
        return new TopBuilder<MergeBuilder>
                (initSet(Top::new,
                        target::getTop,
                        target::setTop))
                .in(this);
    }

    /**
     * set
     * @param useInto into
     * @return THIS
     */
    public MergeBuilder withInto(boolean useInto){
        target.setUseInto(useInto);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public MergeBuilder withInto(){
        target.setUseInto(true);
        return this;
    }

    /**
     * set
     * @param tableName table name
     * @return THIS
     */
    public MergeBuilder withTargetTable(TableName tableName){
        target.setTargetTable(tableName);
        return this;
    }

    /**
     * set
     * @param tableName table name
     * @return THIS
     */
    public MergeBuilder withTargetTable(String tableName){
        target.setTargetTable(new TableName(tableName));
        return this;
    }

    /**
     * set
     * @param mergeHint MergeHint
     * @return THIS
     */
    public MergeBuilder withMergeHint(Merge.MergeHint mergeHint){
        this.target.setMergeHint(mergeHint);
        return this;
    }

    /**
     * in
     * @return MergeHintBuilder
     */
    public MergeHintBuilder<MergeBuilder> withMergeHint(){
        return new MergeHintBuilder<MergeBuilder>
                (initSet(Merge.MergeHint::new,
                        target::getMergeHint,
                        target::setMergeHint))
                .in(this);
    }

    /**
     * set
     * @param useAs as
     * @return THIS
     */
    public MergeBuilder withAs(boolean useAs){
        target.setUseAs(useAs);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public MergeBuilder withAs(){
        target.setUseAs(true);
        return this;
    }

    /**
     * set
     * @param tableAlias table alias
     * @return THIS
     */
    public MergeBuilder withTableAlias(String tableAlias){
        target.setTableAlias(new Alias<>(tableAlias));
        return this;
    }

    /**
     * set
     * @param tableSource TableSource
     * @return THIS
     */
    public MergeBuilder withTableSource(From.TableSource tableSource) {
        this.target.setTableSource(tableSource);
        return this;
    }

    /**
     * in
     * @return TableSourceBuilder
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> withTableSource() {
        return new FromBuilder.TableSourceBuilder<MergeBuilder>
                (target::setTableSource)
                .in(this);
    }

    /**
     * set
     * @param searchCondition SearchCondition
     * @return THIS
     */
    public MergeBuilder withMergeSearchCondition(SearchCondition searchCondition) {
        this.target.setMergeSearchCondition(searchCondition);
        return this;
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<MergeBuilder> withMergeSearchCondition() {
        return new SearchConditionBuilder<MergeBuilder>
                (initSet(SearchCondition::new,
                        target::getMergeSearchCondition,
                        target::setMergeSearchCondition))
                .in(this);
    }

    /**
     * set
     * @param whenMatchedThens MatchedWhenThen
     * @return THIS
     */
    public MergeBuilder withWhenMatchedThen(List<Merge.MatchedWhenThen> whenMatchedThens) {
        initAdd(whenMatchedThens,
                target::getMatchedWhenThenList,
                target::setMatchedWhenThenList);
        return this;
    }

    /**
     * in
     * @return WhenMatchedThenBuilder
     */
    public WhenMatchedThenBuilder<MergeBuilder> withWhenMatchedThen() {
        return new WhenMatchedThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        target::getMatchedWhenThenList,
                        target::setMatchedWhenThenList))
                .in(this);
    }

    /**
     * set
     * @param whenNotMatchedThen NotMatchedWhenThen
     * @return THIS
     */
    public MergeBuilder withWhenNotMatchedTargetThen(Merge.NotMatchedWhenThen whenNotMatchedThen) {
        this.target.setNotMatchedWhenThenTarget(whenNotMatchedThen);
        return this;
    }

    /**
     * in
     * @return WhenNotMatchedTargetThenBuilder
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> withWhenNotMatchedTargetThen() {
        return new WhenNotMatchedTargetThenBuilder<MergeBuilder>
                (initSet(Merge.NotMatchedWhenThen::new,
                        target::getNotMatchedWhenThenTarget,
                        target::setNotMatchedWhenThenTarget))
                .in(this);
    }

    /**
     * set
     * @param whenNotMatchedThens MatchedWhenThen
     * @return THIS
     */
    public MergeBuilder withWhenNotMatchedSourceThen(List<Merge.MatchedWhenThen> whenNotMatchedThens) {
        initAdd(whenNotMatchedThens,
                target::getNotMatchedWhenThenSourceList,
                target::setNotMatchedWhenThenSourceList);
        return this;
    }

    /**
     * in
     * @return WhenNotMatchedSourceThenBuilder
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> withWhenNotMatchedSourceThen() {
        return new WhenNotMatchedSourceThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        target::getNotMatchedWhenThenSourceList,
                        target::setNotMatchedWhenThenSourceList))
                .in(this);
    }

    /**
     * set
     * @param output Output
     * @return THIS
     */
    public MergeBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
    }

    /**
     * in
     * @return OutputBuilder
     */
    public OutputBuilder<MergeBuilder> withOutput() {
        return new OutputBuilder<MergeBuilder>
                (initSet(Output::new,
                        target::getOutput,
                        target::setOutput))
                .in(this);
    }

    /**
     * in
     * @param option OptionBuilder
     * @return THIS
     */
    public MergeBuilder withOption(Option option) {
        this.target.setOption(option);
        return this;
    }

    /**
     * set
     * @return OptionBuilder
     */
    public OptionBuilder<MergeBuilder> withOption() {
        return new OptionBuilder<MergeBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param with With
     * @return THIS
     */
    public MergeBuilder $With(With with){
        target.setWith(with);
        return this;
    }

    /**
     * Quick in
     * @return WithBuilder
     */
    public WithBuilder<MergeBuilder> $With(){
        return withWith();
    }

    /**
     * Quick in
     * @return TopBuilder
     */
    public TopBuilder<MergeBuilder> $Top(){
        return withTop();
    }


    /**
     * Quick set
     * @param tableName table name
     * @return THIS
     */
    public MergeBuilder $(TableName tableName){
        return withTargetTable(tableName);
    }

    /**
     * Quick set
     * @param tableName TableName
     * @return THIS
     */
    public MergeBuilder $Into(TableName tableName){
        return withInto(true).withTargetTable(tableName);
    }

    /**
     * Quick set
     * @param mergeHints TableHintLimited
     * @return THIS
     */
    public MergeBuilder $With(TableHintLimited... mergeHints){
        return withMergeHint()
                .withTableHintLimited(mergeHints)
                .and();
    }

    /**
     * Quick set
     * merge_hint
     * @param indexValues index value
     * @return THIS
     */
    public MergeBuilder $With(String... indexValues){
        return withMergeHint()
                .withIndexValues(indexValues)
                .and();
    }

    /**
     * Quick set
     * @param tableAlias table alias
     * @return THIS
     */
    public MergeBuilder $As(String tableAlias){
        return withAs(true).withTableAlias(tableAlias);
    }

    /**
     * Quick in
     * @return TableSourceBuilder
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> $Using(){
        return withTableSource();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<MergeBuilder> $On(){
        return withMergeSearchCondition();
    }

    /**
     * Quick in
     * @return WhenMatchedThenBuilder
     */
    public WhenMatchedThenBuilder<MergeBuilder> $WhenMatched(){
        return withWhenMatchedThen();
    }

    /**
     * Quick in
     * @return WhenNotMatchedTargetThenBuilder
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> $WhenNotMatched(){
        return withWhenNotMatchedTargetThen();
    }

    /**
     * Quick in
     * @return WhenNotMatchedTargetThenBuilder
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> $WhenNotMatchedByTarget(){
        return withWhenNotMatchedTargetThen()
                .withByTarget(true);
    }

    /**
     * Quick in
     * @return WhenNotMatchedSourceThenBuilder
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> $WhenNotMatchedBySource(){
        return withWhenNotMatchedSourceThen();
    }

    /**
     * Quick in
     * @return OutputBuilder
     */
    public OutputBuilder<MergeBuilder> $OutPut(){
        return withOutput();
    }

    /**
     * Quick in
     * @return OptionBuilder
     */
    public OptionBuilder<MergeBuilder> $Option(){
        return withOption();
    }


    /**
     * MergeHintBuilder
     * @param <ParentBuilder>
     */
    public static class MergeHintBuilder<ParentBuilder>
            extends CodeTreeBuilder<MergeHintBuilder<ParentBuilder>,ParentBuilder,Merge.MergeHint> {

        public MergeHintBuilder() {
            super(new Merge.MergeHint());
        }

        public MergeHintBuilder(Merge.MergeHint mergeHint) {
            super(mergeHint);
        }

        /**
         * set
         * @param tableHintLimited TableHintLimited
         * @return THIS
         */
        public MergeHintBuilder<ParentBuilder> withTableHintLimited(TableHintLimited... tableHintLimited){
            if(CheckUtil.isNullOrEmpty(tableHintLimited)){
                return this;
            }
            initAdd(Arrays.asList(tableHintLimited),
                    target::getTableHintLimitedList,
                    target::setTableHintLimitedList);
            return this;
        }

        /**
         * set
         * @param indexValues index value
         * @return THIS
         */
        public MergeHintBuilder<ParentBuilder> withIndexValues(String... indexValues){
            if(CheckUtil.isNullOrEmpty(indexValues)){
                return this;
            }
            initAdd(Arrays.stream(indexValues)
                            .map(Constants::c_string)
                            .collect(Collectors.toList()),
                    target::getIndexValues,
                    target::setIndexValues);
            return this;
        }

        /*
        TODO Quick
         */
    }

    /**
     * WhenMatchedThenBuilder
     * @param <ParentBuilder>
     */
    public static class WhenMatchedThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenMatchedThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public WhenMatchedThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            super(matchedWhenThen);
        }

        /**
         * set
         * @param searchCondition SearchCondition
         * @return THIS
         */
        public WhenMatchedThenBuilder<ParentBuilder> withClauseSearchCondition(SearchCondition searchCondition) {
            this.target.setClauseSearchCondition(searchCondition);
            return this;
        }

        /**
         * in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            target::getClauseSearchCondition,
                            target::setClauseSearchCondition))
                    .in(this);
        }

        /**
         * set
         * @param mergeMatched MergeMatched
         * @return THIS
         */
        public WhenMatchedThenBuilder<ParentBuilder> withMergeMatched(Merge.MergeMatched mergeMatched) {
            this.target.setMergeMatched(mergeMatched);
            return this;
        }

        /**
         * in
         * @return MergeMatchedBuilder
         */
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
         * Quick in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>> $And() {
            return withClauseSearchCondition();
        }

        /**
         * Quick in
         * @return MergeMatchedBuilder
         */
        public MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>> $Then() {
            return withMergeMatched();
        }

    }

    /**
     * WhenNotMatchedTargetThenBuilder
     * @param <ParentBuilder>
     */
    public static class WhenNotMatchedTargetThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>,ParentBuilder,Merge.NotMatchedWhenThen> {

        public WhenNotMatchedTargetThenBuilder() {
            super(new Merge.NotMatchedWhenThen());
        }

        public WhenNotMatchedTargetThenBuilder(Merge.NotMatchedWhenThen notMatchedWhenThen) {
            super(notMatchedWhenThen);
        }

        /**
         * set
         * @param useByTarget by target
         * @return THIS
         */
        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withByTarget(boolean useByTarget){
            target.setUseByTarget(useByTarget);
            return this;
        }

        /**
         * set
         * @param searchCondition SearchCondition
         * @return THIS
         */
        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withClauseSearchCondition(SearchCondition searchCondition) {
            this.target.setClauseSearchCondition(searchCondition);
            return this;
        }

        /**
         * in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            target::getClauseSearchCondition,
                            target::setClauseSearchCondition))
                    .in(this);
        }

        /**
         * set
         * @param mergeNotMatched MergeNotMatched
         * @return THIS
         */
        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withMergeNotMatched(Merge.MergeNotMatched mergeNotMatched) {
            this.target.setMergeNotMatched(mergeNotMatched);
            return this;
        }

        /**
         * in
         * @return MergeNotMatchedBuilder
         */
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
         * Quick in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> $And() {
            return withClauseSearchCondition();
        }

        /**
         * Quick in
         * @return MergeNotMatchedBuilder
         */
        public MergeNotMatchedBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> $Then() {
            return withMergeNotMatched();
        }

    }

    /**
     * WhenNotMatchedSourceThenBuilder
     * @param <ParentBuilder>
     */
    public static class WhenNotMatchedSourceThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public WhenNotMatchedSourceThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            super(matchedWhenThen);
        }

        /**
         * in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            target::getClauseSearchCondition,
                            target::setClauseSearchCondition))
                    .in(this);
        }

        /**
         * in
         * @return MergeNotMatchedBuilder
         */
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
         * Quick in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> $And() {
            return withClauseSearchCondition();
        }

        /**
         * Quick in
         * @return MergeNotMatchedBuilder
         */
        public MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> $Then() {
            return withMergeNotMatched();
        }

    }

    /**
     * MergeMatchedBuilder
     * @param <ParentBuilder>
     */
    public static class MergeMatchedBuilder<ParentBuilder>
            extends CodeTreeBuilder<MergeMatchedBuilder<ParentBuilder>,ParentBuilder,Merge.MergeMatched> {

        public MergeMatchedBuilder() {
            super(new Merge.MergeMatched());
        }

        public MergeMatchedBuilder(Merge.MergeMatched mergeMatched) {
            super(mergeMatched);
        }

        /**
         * set
         * @param useSet set
         * @return THIS
         */
        public MergeMatchedBuilder<ParentBuilder> withSet(boolean useSet){
            target.setUseSet(useSet);
            return this;
        }

        /**
         * set
         * @param setItems SetItem
         * @return THIS
         */
        public MergeMatchedBuilder<ParentBuilder> withSetItem(List<Update.SetItem> setItems){
            this.target.setSets(setItems);
            return this;
        }

        /**
         * in
         * @return SetItemBuilder
         */
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
         * @return PARENT
         */
        public ParentBuilder $Delete() {
            return withSet(false)
                    .and();
        }

        /**
         * Quick set
         * @param items SetItem
         * @return PARENT
         */
        public ParentBuilder $UpdateSet(Update.SetItem... items) {
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
     * MergeNotMatchedBuilder
     * @param <ParentBuilder>
     */
    public static class MergeNotMatchedBuilder<ParentBuilder>
            extends CodeTreeBuilder<MergeNotMatchedBuilder<ParentBuilder>,ParentBuilder,Merge.MergeNotMatched> {

        public MergeNotMatchedBuilder(Merge.MergeNotMatched mergeNotMatched) {
            super(mergeNotMatched);
        }

        /**
         * set
         * @param columnNames ColumnName
         * @return THIS
         */
        public MergeNotMatchedBuilder<ParentBuilder> withColumn(ColumnName... columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(Arrays.asList(columnNames),
                    target::getColumns,
                    target::setColumns);
            return this;
        }

        /**
         * set
         * @param columnNames ColumnName
         * @return THIS
         */
        public MergeNotMatchedBuilder<ParentBuilder> withColumn(List<ColumnName> columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(columnNames,
                    target::getColumns,
                    target::setColumns);
            return this;
        }

        /**
         * set
         * @param values TableValueConstructor
         * @return THIS
         */
        public MergeNotMatchedBuilder<ParentBuilder> withValues(TableValueConstructor values){
            this.target.setValues(values);
            return this;
        }

        /**
         * in
         * @return TableValueConstructorBuilder
         */
        public TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>> withValues(){
            return new TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>>
                    (initSet(TableValueConstructor::new,
                            target::getValues,
                            target::setValues))
                    .in(this);
        }

        /**
         * set
         * @return THIS
         */
        public MergeNotMatchedBuilder<ParentBuilder> withDefaultValues(){
            this.target.setUseDefaultValues(true);
            return this;
        }

        /**
         * set
         * @param useDefaultValues default values
         * @return THIS
         */
        public MergeNotMatchedBuilder<ParentBuilder> withDefaultValues(boolean useDefaultValues){
            this.target.setUseDefaultValues(useDefaultValues);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set columnNames
         * @param columnNames ColumnName
         * @return THIS
         */
        public MergeNotMatchedBuilder<ParentBuilder> $Insert(ColumnName... columnNames){
            return withColumn(columnNames);
        }

        /**
         * Quick in
         * @return TableValueConstructorBuilder
         */
        public TableValueConstructorBuilder<MergeNotMatchedBuilder<ParentBuilder>> $Values() {
            return withValues();
        }
    }
}
