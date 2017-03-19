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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
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
    public MergeBuilder withTableName(TableName tableName){
        tar.setTargetTable(tableName);
        return this;
    }

    /**
     *
     * @param tableName
     * @return
     */
    public MergeBuilder withTableName(String tableName){
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
    public MatchedWhenThenBuilder<MergeBuilder> withMatchedWhenThen() {
        return new MatchedWhenThenBuilder<MergeBuilder>
                (initNew(Merge.MatchedWhenThen::new,
                        tar::getMatchedWhenThenList,
                        tar::setMatchedWhenThenList))
                .in(this);
    }

    /**
     *
     * @return
     */
    public NotMatchedTargetWhenThenBuilder<MergeBuilder> withNotMatchedWhenThenTarget() {
        return new NotMatchedTargetWhenThenBuilder<MergeBuilder>
                (initSet(Merge.NotMatchedWhenThen::new,
                        tar::getNotMatchedWhenThenTarget,
                        tar::setNotMatchedWhenThenTarget))
                .in(this);
    }

    /**
     *
     * @return
     */
    public NotMatchedSourceWhenThenBuilder<MergeBuilder> withNotMatchedWhenThenSource() {
        return new NotMatchedSourceWhenThenBuilder<MergeBuilder>
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


    public static MergeBuilder MERGE(){
        return new MergeBuilder();
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

        public MergeHintBuilder<ParentBuilder> withTableHintLimited(TableHintLimited tableHintLimited){
            if(this.tar.getTableHintLimitedList() == null){
                this.tar.setTableHintLimitedList(EnumSet.allOf(TableHintLimited.class));
            }
            this.tar.getTableHintLimitedList().add(tableHintLimited);
            return this;
        }

        public MergeHintBuilder<ParentBuilder> withNull(Boolean useDelimiter){
            tar.setUseDelimiter(useDelimiter);
            return this;
        }

        public MergeHintBuilder<ParentBuilder> withTableHintLimited(String valueVal){
            if(this.tar.getIndexValList() == null){
                this.tar.setIndexValList(new ArrayList());
            }
            tar.getIndexValList().add(new Unknown(valueVal));
            return this;
        }
    }

    /**
     *
     * @param <ParentBuilder>
     */
    public class MatchedWhenThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<MatchedWhenThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public MatchedWhenThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            super(matchedWhenThen);
        }


        public SearchConditionBuilder<MatchedWhenThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<MatchedWhenThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            tar::getClauseSearchCondition,
                            tar::setClauseSearchCondition))
                    .in(this);
        }

        public MergeMatchedBuilder<MatchedWhenThenBuilder<ParentBuilder>> withMergeMatched() {
            return new MergeMatchedBuilder<MatchedWhenThenBuilder<ParentBuilder>>
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
    public class NotMatchedTargetWhenThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<NotMatchedTargetWhenThenBuilder<ParentBuilder>,ParentBuilder,Merge.NotMatchedWhenThen> {

        public NotMatchedTargetWhenThenBuilder(Merge.NotMatchedWhenThen notMatchedWhenThen) {
            super(notMatchedWhenThen);
        }


        public NotMatchedTargetWhenThenBuilder<ParentBuilder> withByTarget(boolean useByTarget){
            tar.setUseByTarget(useByTarget);
            return this;
        }

        public SearchConditionBuilder<NotMatchedTargetWhenThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<NotMatchedTargetWhenThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            tar::getClauseSearchCondition,
                            tar::setClauseSearchCondition))
                    .in(this);
        }

        public MergeNotMatchedBuilder<NotMatchedTargetWhenThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<NotMatchedTargetWhenThenBuilder<ParentBuilder>>
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
    public class NotMatchedSourceWhenThenBuilder<ParentBuilder>
            extends CodeTreeBuilder<NotMatchedSourceWhenThenBuilder<ParentBuilder>,ParentBuilder,Merge.MatchedWhenThen> {

        public NotMatchedSourceWhenThenBuilder(Merge.MatchedWhenThen matchedWhenThen) {
            super(matchedWhenThen);
        }

        public SearchConditionBuilder<NotMatchedSourceWhenThenBuilder<ParentBuilder>> withClauseSearchCondition() {
            return new SearchConditionBuilder<NotMatchedSourceWhenThenBuilder<ParentBuilder>>
                    (initSet(SearchCondition::new,
                            tar::getClauseSearchCondition,
                            tar::setClauseSearchCondition))
                    .in(this);
        }

        public MergeNotMatchedBuilder<NotMatchedSourceWhenThenBuilder<ParentBuilder>> withMergeNotMatched() {
            return new MergeNotMatchedBuilder<NotMatchedSourceWhenThenBuilder<ParentBuilder>>
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

        public UpdateBuilder.SetBuilder<MergeMatchedBuilder<ParentBuilder>> withSetItem(){
            return new UpdateBuilder.SetBuilder<MergeMatchedBuilder<ParentBuilder>>
                    (initNew(Update.Set::new,
                            tar::getSets,
                            tar::setSets))
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
