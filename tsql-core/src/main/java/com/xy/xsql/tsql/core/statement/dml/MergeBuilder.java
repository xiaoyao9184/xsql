package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
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

    public MergeBuilder withWith(With with){
        this.target.setWith(with);
        return this;
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

    public MergeBuilder withTop(Top top){
        this.target.setTop(top);
        return this;
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

    public MergeBuilder withInto(){
        target.setUseInto(true);
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

    public MergeBuilder withMergeHint(Merge.MergeHint mergeHint){
        this.target.setMergeHint(mergeHint);
        return this;
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

    public MergeBuilder withAs(){
        target.setUseAs(true);
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

    public MergeBuilder withTableSource(From.TableSource tableSource) {
        this.target.setTableSource(tableSource);
        return this;
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

    public MergeBuilder withMergeSearchCondition(SearchCondition searchCondition) {
        this.target.setMergeSearchCondition(searchCondition);
        return this;
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

    public MergeBuilder withWhenMatchedThen(List<Merge.MatchedWhenThen> whenMatchedThens) {
        initAdd(whenMatchedThens,
                target::getMatchedWhenThenList,
                target::setMatchedWhenThenList);
        return this;
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

    public MergeBuilder withWhenNotMatchedTargetThen(Merge.NotMatchedWhenThen whenNotMatchedThen) {
        this.target.setNotMatchedWhenThenTarget(whenNotMatchedThen);
        return this;
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

    public MergeBuilder withWhenNotMatchedSourceThen(List<Merge.MatchedWhenThen> whenNotMatchedThens) {
        initAdd(whenNotMatchedThens,
                target::getNotMatchedWhenThenSourceList,
                target::setNotMatchedWhenThenSourceList);
        return this;
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

    public MergeBuilder withOutput(Output output) {
        this.target.setOutput(output);
        return this;
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

    public MergeBuilder withOption(Option option) {
        this.target.setOption(option);
        return this;
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
    public MergeBuilder $With(With with){
        target.setWith(with);
        return this;
    }

    /**
     * Quick in
     * @return
     */
    public WithBuilder<MergeBuilder> $With(){
        return withWith();
    }

    /**
     * Quick in
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
    public MergeBuilder $With(TableHintLimited... mergeHints){
        return withMergeHint()
                .withTableHintLimited(mergeHints)
                .and();
    }

    /**
     * Quick set
     * merge_hint
     * @param indexValues
     * @return
     */
    public MergeBuilder $With(String... indexValues){
        return withMergeHint()
                .withIndexValues(indexValues)
                .and();
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
     * Quick in
     * @return
     */
    public FromBuilder.TableSourceBuilder<MergeBuilder> $Using(){
        return withTableSource();
    }

    /**
     * Quick in
     * @return
     */
    public SearchConditionBuilder<MergeBuilder> $On(){
        return withMergeSearchCondition();
    }

    /**
     * Quick in
     * @return
     */
    public WhenMatchedThenBuilder<MergeBuilder> $When_Matched(){
        return withWhenMatchedThen();
    }

    /**
     * Quick in
     * @return
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> $When_Not_Matched(){
        return withWhenNotMatchedTargetThen();
    }

    /**
     * Quick in
     * @return
     */
    public WhenNotMatchedTargetThenBuilder<MergeBuilder> $When_Not_Matched_By_Target(){
        return withWhenNotMatchedTargetThen()
                .withByTarget(true);
    }

    /**
     * Quick in
     * @return
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> $When_Not_Matched_By_Source(){
        return withWhenNotMatchedSourceThen();
    }

    /**
     * Quick in
     * @return
     */
    public OutputBuilder<MergeBuilder> $OutPut(){
        return withOutput();
    }

    /**
     * Quick in
     * @return
     */
    public OptionBuilder<MergeBuilder> $Option(){
        return withOption();
    }




    /**
     *
     * @param <ParentBuilder>
     */
    public static class MergeHintBuilder<ParentBuilder>
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

        public MergeHintBuilder<ParentBuilder> withIndexValues(String... indexValues){
            if(CheckUtil.isNullOrEmpty(indexValues)){
                return this;
            }
            initAdd(Arrays.stream(indexValues)
                            .map(StringConstant::new)
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
     *
     * @param <ParentBuilder>
     */
    public static class WhenMatchedThenBuilder<ParentBuilder>
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

        public WhenMatchedThenBuilder<ParentBuilder> withClauseSearchCondition(SearchCondition searchCondition) {
            this.target.setClauseSearchCondition(searchCondition);
            return this;
        }

        public MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>> withMergeMatched() {
            return new MergeMatchedBuilder<WhenMatchedThenBuilder<ParentBuilder>>
                    (initSet(Merge.MergeMatched::new,
                            target::getMergeMatched,
                            target::setMergeMatched))
                    .in(this);
        }

        public WhenMatchedThenBuilder<ParentBuilder> withMergeMatched(Merge.MergeMatched mergeMatched) {
            this.target.setMergeMatched(mergeMatched);
            return this;
        }



        /*
        Quick
         */

        /**
         * Quick into
         * @return
         */
        public SearchConditionBuilder<WhenMatchedThenBuilder<ParentBuilder>> $And() {
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
    public static class WhenNotMatchedTargetThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>,ParentBuilder,Merge.NotMatchedWhenThen> {

        public WhenNotMatchedTargetThenBuilder(Merge.NotMatchedWhenThen notMatchedWhenThen) {
            super(notMatchedWhenThen);
        }


        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withByTarget(boolean useByTarget){
            target.setUseByTarget(useByTarget);
            return this;
        }

        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withClauseSearchCondition(SearchCondition searchCondition) {
            this.target.setClauseSearchCondition(searchCondition);
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

        public WhenNotMatchedTargetThenBuilder<ParentBuilder> withMergeNotMatched(Merge.MergeNotMatched mergeNotMatched) {
            this.target.setMergeNotMatched(mergeNotMatched);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick into
         * @return
         */
        public SearchConditionBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>> $And() {
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
    public static class WhenNotMatchedSourceThenBuilder<ParentBuilder>
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
        public SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> $And() {
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
    public static class MergeMatchedBuilder<ParentBuilder>
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

        public MergeMatchedBuilder<ParentBuilder> withSetItem(List<Update.SetItem> setItems){
            this.target.setSets(setItems);
            return this;
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
    public static class MergeNotMatchedBuilder<ParentBuilder>
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

        public MergeNotMatchedBuilder<ParentBuilder> withColumn(List<ColumnName> columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(columnNames,
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

        public MergeNotMatchedBuilder<ParentBuilder> withValues(TableValueConstructor values){
            this.target.setValues(values);
            return this;
        }

        public MergeNotMatchedBuilder<ParentBuilder> withDefaultValues(){
            this.target.setUseDefaultValues(true);
            return this;
        }

        public MergeNotMatchedBuilder<ParentBuilder> withDefaultValues(boolean useDefaultValues){
            this.target.setUseDefaultValues(useDefaultValues);
            return this;
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
