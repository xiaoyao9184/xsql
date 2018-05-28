package com.xy.xsql.tsql.builder.chain.statements;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.builder.chain.datatypes.Constants;
import com.xy.xsql.tsql.builder.chain.queries.*;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.*;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;
import com.xy.xsql.tsql.model.statements.Merge;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;

/**
 * MergeBuilder
 * Created by xiaoyao9184 on 2017/1/10.
 */
@SuppressWarnings({"unused","WeakerAccess"})
public class MergeBuilder extends CodeBuilder<Merge> {

    public MergeBuilder(Merge target) {
        super(target);
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
                (object(target::getWith, target::setWith)
                        .init(With::new))
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
                (object(target::getTop, target::setTop)
                        .init(Top::new))
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
                (object(target::getMergeHint, target::setMergeHint)
                        .init(Merge.MergeHint::new))
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
        return new FromBuilder.TableSourceBuilder<MergeBuilder>()
                .enter(this, Getter.empty(), target::setTableSource);
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
                (object(target::getMergeSearchCondition, target::setMergeSearchCondition)
                        .init(SearchCondition::new))
                .in(this);
    }

    /**
     * set
     * @param whenMatchedThens MatchedWhenThen
     * @return THIS
     */
    public MergeBuilder withWhenMatchedThen(List<Merge.MatchedWhenThen> whenMatchedThens) {
        list(target::getMatchedWhenThenList, target::setMatchedWhenThenList)
                .addAll(whenMatchedThens);
        return this;
    }

    /**
     * in
     * @return WhenMatchedThenBuilder
     */
    public WhenMatchedThenBuilder<MergeBuilder> withWhenMatchedThen() {
        return new WhenMatchedThenBuilder<MergeBuilder>
                (list(target::getMatchedWhenThenList, target::setMatchedWhenThenList)
                        .addNew(Merge.MatchedWhenThen::new))
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
                (object(target::getNotMatchedWhenThenTarget, target::setNotMatchedWhenThenTarget)
                        .init(Merge.NotMatchedWhenThen::new))
                .in(this);
    }

    /**
     * set
     * @param whenNotMatchedThens MatchedWhenThen
     * @return THIS
     */
    public MergeBuilder withWhenNotMatchedSourceThen(List<Merge.MatchedWhenThen> whenNotMatchedThens) {
        list(target::getNotMatchedWhenThenSourceList, target::setNotMatchedWhenThenSourceList)
                .addAll(whenNotMatchedThens);
        return this;
    }

    /**
     * in
     * @return WhenNotMatchedSourceThenBuilder
     */
    public WhenNotMatchedSourceThenBuilder<MergeBuilder> withWhenNotMatchedSourceThen() {
        return new WhenNotMatchedSourceThenBuilder<MergeBuilder>
                (list(target::getNotMatchedWhenThenSourceList, target::setNotMatchedWhenThenSourceList)
                        .addNew(Merge.MatchedWhenThen::new))
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
                (object(target::getOutput, target::setOutput)
                        .init(Output::new))
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
                (object(target::getOption, target::setOption)
                        .init(Option::new))
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
            extends ParentHoldBuilder<MergeHintBuilder<ParentBuilder>,ParentBuilder,Merge.MergeHint> {

        public MergeHintBuilder() {
            super(new Merge.MergeHint());
        }

        public MergeHintBuilder(Merge.MergeHint target) {
            super(target);
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
            list(target::getTableHintLimitedList, target::setTableHintLimitedList)
                    .addAll(tableHintLimited);
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
            list(target::getIndexValues, target::setIndexValues)
                    .addAll(Arrays.stream(indexValues).map(Constants::c_string));
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
            extends ParentHoldBuilder<WhenMatchedThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public WhenMatchedThenBuilder() {
            super(new Merge.MatchedWhenThen());
        }

        public WhenMatchedThenBuilder(Merge.MatchedWhenThen target) {
            super(target);
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
                    (object(target::getClauseSearchCondition, target::setClauseSearchCondition)
                            .init(SearchCondition::new))
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
                    (object(target::getMergeMatched, target::setMergeMatched)
                            .init(Merge.MergeMatched::new))
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
            extends ParentHoldBuilder<WhenNotMatchedTargetThenBuilder<ParentBuilder>,ParentBuilder,Merge.NotMatchedWhenThen> {

        public WhenNotMatchedTargetThenBuilder() {
            super(new Merge.NotMatchedWhenThen());
        }

        public WhenNotMatchedTargetThenBuilder(Merge.NotMatchedWhenThen target) {
            super(target);
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
                    (object(target::getClauseSearchCondition, target::setClauseSearchCondition)
                            .init(SearchCondition::new))
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
                    (object(target::getMergeNotMatched, target::setMergeNotMatched)
                            .init(Merge.MergeNotMatched::new))
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
            extends ParentHoldBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public WhenNotMatchedSourceThenBuilder() {
            super(new Merge.MatchedWhenThen());
        }

        public WhenNotMatchedSourceThenBuilder(Merge.MatchedWhenThen target) {
            super(target);
        }

        /**
         * in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>>
                    (object(target::getClauseSearchCondition, target::setClauseSearchCondition)
                            .init(SearchCondition::new))
                    .in(this);
        }

        /**
         * in
         * @return MergeNotMatchedBuilder
         */
        public MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<WhenNotMatchedSourceThenBuilder<ParentBuilder>>
                    (object(target::getMergeNotMatched, target::setMergeNotMatched)
                            .init(Merge.MergeNotMatched::new))
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
            extends ParentHoldBuilder<MergeMatchedBuilder<ParentBuilder>,ParentBuilder,Merge.MergeMatched> {

        public MergeMatchedBuilder() {
            super(new Merge.MergeMatched());
        }

        public MergeMatchedBuilder(Merge.MergeMatched target) {
            super(target);
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
            list(target::getSets, target::setSets).init();
            return new UpdateBuilder.SetItemBuilder<MergeMatchedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target.getSets()::add);
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
            list(target::getSets, target::setSets)
                    .addAll(items);
            return withSet(true)
                    .and();
        }
    }

    /**
     * MergeNotMatchedBuilder
     * @param <ParentBuilder>
     */
    public static class MergeNotMatchedBuilder<ParentBuilder>
            extends ParentHoldBuilder<MergeNotMatchedBuilder<ParentBuilder>,ParentBuilder,Merge.MergeNotMatched> {

        public MergeNotMatchedBuilder() {
            super(new Merge.MergeNotMatched());
        }

        public MergeNotMatchedBuilder(Merge.MergeNotMatched target) {
            super(target);
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
            list(target::getColumns, target::setColumns)
                    .addAll(columnNames);
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
            list(target::getColumns, target::setColumns)
                    .addAll(columnNames);
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
                    (object(target::getValues, target::setValues)
                            .init(TableValueConstructor::new))
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
