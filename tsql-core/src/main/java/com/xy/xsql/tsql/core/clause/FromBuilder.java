package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.CodeTreeLazyConfigBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.LocalVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;

/**
 * FromBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class FromBuilder<ParentBuilder>
        extends CodeTreeBuilder<FromBuilder<ParentBuilder>,ParentBuilder,From> {

    public FromBuilder() {
        super(new From());
    }

    public FromBuilder(From from) {
        super(from);
    }

    /**
     * @return
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> withItem() {
        initList(
                target::getTableSourceList,
                target::setTableSourceList);
        return new TableSourceBuilder<FromBuilder<ParentBuilder>>
                (target.getTableSourceList()::add)
                .in(this);
    }




    /*
    Quick into
     */

    /**
     * Quick into TableSourceBuilder
     * @return
     */
    public TransformJoinedBuilder<FromBuilder<ParentBuilder>> $() {
        initList(
                target::getTableSourceList,
                target::setTableSourceList);
        return new TransformJoinedBuilder<FromBuilder<ParentBuilder>>
                (target.getTableSourceList()::add)
                .in(this);
    }


    /*
    If the TableSource is NOT single,
    Then the TableSource alias is very meaningful,
    you can quickly confirm TableSource type use these method
     */

    /**
     * Quick into BaseTableBuilder
     * And set tableName,tableAlias
     * @param tableName
     * @param tableAlias
     * @return
     */
    public BaseTableBuilder<FromBuilder<ParentBuilder>> $(TableName tableName, String tableAlias) {
        return withItem()._Base()
                .withTableName(tableName)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick into DerivedTableBuilder
     * And set values,tableAlias
     * @param values
     * @param tableAlias
     * @return
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(TableValueConstructor values, String tableAlias) {
        return withItem()._Derived()
                .withValues(values)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick into DerivedTableBuilder
     * And set subQuery,tableAlias
     * @param subQuery
     * @param tableAlias
     * @return
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick into VariableTableBuilder
     * And set variable,tableAlias
     * @param variable
     * @param tableAlias
     * @return
     */
    public VariableTableBuilder<FromBuilder<ParentBuilder>> $(LocalVariable variable, String tableAlias) {
        return withItem()._Variable()
                .withVariable(variable)
                .withAs()
                .withTableAlias(tableAlias);
    }




    /*
    Quick set
    If the TableSource is single,
    then the TableSource alias is of little significance,
    you can quickly build use these method
     */

    /**
     * Quick set BaseTableBuilder build BaseTable TableSource
     *
     * @param tableName
     * @return
     */
    public FromBuilder<ParentBuilder> $(TableName tableName) {
        return withItem()._Base()
                .withTableName(tableName)
                .and();
    }

    /**
     * Quick inout DerivedTableBuilder build DerivedTable(subQuery) TableSource
     *
     * @param subQuery
     * @return
     */
    public FromBuilder<ParentBuilder> $(Select.QuerySpecification subQuery) {
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick inout DerivedTableBuilder build DerivedTable(values) TableSource
     *
     * @param values
     * @return
     */
    public FromBuilder<ParentBuilder> $(TableValueConstructor values) {
        return withItem()._Derived()
                .withValues(values)
                .and();
    }

    /**
     * Quick inout VariableTableBuilder build VariableTable TableSource
     *
     * @param variable
     * @return
     */
    public FromBuilder<ParentBuilder> $(LocalVariable variable) {
        return withItem()._Variable()
                .withVariable(variable)
                .and();
    }



    /**
     * Abstract TableSource Builder
     *
     * @param <ParentBuilder>
     */
    public static class TableSourceBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableSourceBuilder<ParentBuilder>, ParentBuilder, Setter<From.TableSource>> {

        public TableSourceBuilder(Setter<From.TableSource> setter) {
            super(setter);
        }

        /**
         * Confirm type of TableSource
         * @return
         */
        public BaseTableBuilder<ParentBuilder> _Base() {
            return new BaseTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> _Derived() {
            return new DerivedTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> _Joined() {
            return new JoinedTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return
         */
        public VariableTableBuilder<ParentBuilder> _Variable() {
            return new VariableTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        //
        //
        /*
        Quick into sub builder

        TODO
        we dont into abstract builder,
        but MergeBuilder need in this,
        move this method to MergeBuilder
         */

        /**
         * Quick into BaseTableBuilder
         * And set tableName
         * @param tableName
         * @return
         */
        public BaseTableBuilder<ParentBuilder> $(TableName tableName) {
            return _Base()
                    .withTableName(tableName);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set subQuery
         * @param subQuery
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery) {
            return _Derived()
                    .withSubQuery(subQuery);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set values
         * @param values
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(TableValueConstructor values) {
            return _Derived()
                    .withValues(values);
        }

        /**
         * Quick into VariableTableBuilder
         * And set variable
         * @param variable
         * @return
         */
        public VariableTableBuilder<ParentBuilder> $(LocalVariable variable) {
            return _Variable()
                    .withVariable(variable);
        }

        /**
         * Quick into BaseTableBuilder
         * And set tableName,tableAlias
         * @param tableName
         * @param tableAlias
         * @return
         */
        public BaseTableBuilder<ParentBuilder> $(TableName tableName, String tableAlias) {
            return _Base()
                    .withTableName(tableName)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set values,tableAlias
         * @param values
         * @param tableAlias
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(TableValueConstructor values, String tableAlias) {
            return _Derived()
                    .withValues(values)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set subQuery,tableAlias
         * @param subQuery
         * @param tableAlias
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias) {
            return _Derived()
                    .withSubQuery(subQuery)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick into VariableTableBuilder
         * And set variable,tableAlias
         * @param variable
         * @param tableAlias
         * @return
         */
        public VariableTableBuilder<ParentBuilder> $(LocalVariable variable, String tableAlias) {
            return _Variable()
                    .withVariable(variable)
                    .withAs()
                    .withTableAlias(tableAlias);
        }


    }


    /**
     * TransformJoinedBuilder
     * Allows any TableSource to transform to JoinedTable TableSource
     *
     * 1.use setter to set TableSource
     * 2.setter default set to target
     * 3.sub builder build TableSource then call setter to set
     * 4.avoid the JoinTableBuilder, provide the join method
     * 5.use join method transform target to TableSource:JoinedTable
     * 6.change setter to set TableSource:JoinedTable's TableSource
     * @param <ParentBuilder>
     */
    public static class TransformJoinedBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<TransformJoinedBuilder<ParentBuilder>, ParentBuilder, From.TableSource, From.TableSource> {

        public TransformJoinedBuilder(From.TableSource target) {
            super(target);
        }

        public TransformJoinedBuilder(From.TableSource target, Setter<From.TableSource> setter) {
            super(target, setter);
        }

        public TransformJoinedBuilder(Setter<From.TableSource> setter) {
            super(null, setter);
        }

        public void setTarget(From.TableSource target){
            this.target = target;
        }

        //use this setter to set target
        private Setter<From.TableSource> tableSourceSetter = this::setTarget;


        /*
        Quick into sub builder
         */

        /**
         * Quick into BaseTableBuilder
         * And set tableName
         * @param tableName
         * @return
         */
        public BaseTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(TableName tableName) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Base()
                    .withTableName(tableName);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set subQuery
         * @param subQuery
         * @return
         */
        public DerivedTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Derived()
                    .withSubQuery(subQuery);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set values
         * @param values
         * @return
         */
        public DerivedTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(TableValueConstructor values) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Derived()
                    .withValues(values);
        }

        /**
         * Quick into VariableTableBuilder
         * And set variable
         * @param variable
         * @return
         */
        public VariableTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(LocalVariable variable) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Variable()
                    .withVariable(variable);
        }




        /*
        Quick set
         */

        /**
         * Quick set TableSource:BaseTable
         * into BaseTableBuilder and get-out
         * @param tableName
         * @param tableAlias
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $(TableName tableName, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Base()
                    .withTableName(tableName)
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set tableSource:DerivedTable
         * into DerivedTableBuilder and get-out
         * @param values
         * @param tableAlias
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $(TableValueConstructor values, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Derived()
                    .withValues(values)
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set TableSource:DerivedTable
         * into DerivedTableBuilder and get-out
         * @param subQuery
         * @param tableAlias
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Derived()
                    .withSubQuery(subQuery)
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set TableSource:VariableTable
         * into VariableTableBuilder and get-out
         * @param variable
         * @param tableAlias
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $(LocalVariable variable, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
                    ._Variable()
                    .withVariable(variable)
                    .withTableAlias(tableAlias)
                    .and();
        }




        /*
        Quick transform
         */

        /**
         * Quick transform to JoinedTableBuilder
         * And set flag CrossJoin
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Cross_Join() {
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b = new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (this::setTarget)
                    .in(this)
                    ._Joined()
                    .withTableSource(this.target)
                    .withCrossJoin();

            this.tableSourceSetter = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * And set flag CrossApply
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Cross_Apply() {
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b = new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (this::setTarget)
                    .in(this)
                    ._Joined()
                    .withTableSource(this.target)
                    .withCrossApply();

            this.tableSourceSetter = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * And set flag Outer_Apply
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Outer_Apply() {
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b = new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (this::setTarget)
                    .in(this)
                    ._Joined()
                    .withTableSource(this.target)
                    .withOuterApply();

            this.tableSourceSetter = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * And set joinType
         * @param joinType
         * @return
         */
        private TransformJoinedBuilder<ParentBuilder> transformJoinedTable(From.JoinType joinType){
            /*
            it will create JoinedTable,
            use target as JoinedTable's TableSource1
            then replace target
             */
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b = new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (this::setTarget)
                    .in(this)
                    ._Joined()
                    .withTableSource(this.target)
                    .withJoinType(joinType);
            //replace setter
            this.tableSourceSetter = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Join(){
            return transformJoinedTable(From.JoinType.JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Join(){
            return transformJoinedTable(From.JoinType.INNER_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REDUCE_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Reduce_Join(){
            return transformJoinedTable(From.JoinType.INNER_REDUCE_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REPLICATE_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Replicate_Join(){
            return transformJoinedTable(From.JoinType.INNER_REPLICATE_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REDISTRIBUTE_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Redistribute_Join(){
            return transformJoinedTable(From.JoinType.INNER_REDISTRIBUTE_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Left_Join(){
            return transformJoinedTable(From.JoinType.LEFT_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Right_Join(){
            return transformJoinedTable(From.JoinType.RIGHT_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Full_Join(){
            return transformJoinedTable(From.JoinType.FULL_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Left_Outer_Join(){
            return transformJoinedTable(From.JoinType.LEFT_OUTER_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Join(){
            return transformJoinedTable(From.JoinType.RIGHT_OUTER_JOIN);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Full_Outer_Join(){
            return transformJoinedTable(From.JoinType.FULL_OUTER_JOIN);
        }


        /*
        Quick into if already transform to JoinedTable
         */

        /**
         * Quick into SearchConditionBuilder
         * And set TableSource:JoinedTable' searchCondition
         * @return
         */
        public SearchConditionBuilder<TransformJoinedBuilder<ParentBuilder>> $On() {
            if(!(this.target instanceof From.JoinedTable)){
                transformJoinedTable(From.JoinType.JOIN);
            }
            SearchCondition searchCondition = new SearchCondition();
            From.JoinedTable joinedTable = (From.JoinedTable) this.target;
            joinedTable.setSearchCondition(searchCondition);
            return new SearchConditionBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (searchCondition)
                    .in(this);
        }
    }

    /**
     * BaseTableBuilder
     * @param <ParentBuilder>
     */
    public static class BaseTableBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<BaseTableBuilder<ParentBuilder>, ParentBuilder, From.BaseTable, From.TableSource> {

        public BaseTableBuilder(From.BaseTable target) {
            super(target);
        }

        public BaseTableBuilder(From.BaseTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public BaseTableBuilder(Setter<From.TableSource> setter) {
            super(new From.BaseTable(),setter);
        }

        public BaseTableBuilder<ParentBuilder> withTableName(TableName tableName){
            target.setTableName(tableName);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            target.setTableAlias(new Alias<>(aliasName));
            return this;
        }


        /**
         * Quick set tableAlias
         * @param aliasName
         * @return
         */
        public ParentBuilder $As(String aliasName){
            return withAs()
                    .withTableAlias(aliasName)
                    .and();
        }

    }

    /**
     * VariableTableBuilder
     * @param <ParentBuilder>
     */
    public static class VariableTableBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<VariableTableBuilder<ParentBuilder>, ParentBuilder, From.VariableTable, From.TableSource> {

        public VariableTableBuilder(From.VariableTable target) {
            super(target);
        }

        public VariableTableBuilder(From.VariableTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public VariableTableBuilder(Setter<From.TableSource> setter) {
            super(new From.VariableTable(),setter);
        }


        public VariableTableBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        public VariableTableBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        public VariableTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            target.setTableAlias(new Alias<>(aliasName));
            return this;
        }


        /**
         * Quick set tableAlias
         * @param aliasName
         * @return
         */
        public ParentBuilder $As(String aliasName){
            return withAs()
                    .withTableAlias(aliasName)
                    .and();
        }

    }

    /**
     * DerivedTableBuilder
     * @param <ParentBuilder>
     */
    public static class DerivedTableBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<DerivedTableBuilder<ParentBuilder>, ParentBuilder, From.DerivedTable, From.TableSource> {

        public DerivedTableBuilder(From.DerivedTable target) {
            super(target);
        }

        public DerivedTableBuilder(From.DerivedTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public DerivedTableBuilder(Setter<From.TableSource> setter) {
            super(new From.DerivedTable(), setter);
        }


        public DerivedTableBuilder<ParentBuilder> withSubQuery(Select.QuerySpecification subQuery){
            target.setSubQuery(subQuery);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withValues(TableValueConstructor values){
            target.setValues(values);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            target.setTableAlias(new Alias<>(aliasName));
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withColumnAlias(String... columnAliass){
            List<Alias<Void>> list = Arrays.stream(columnAliass)
                    .map(Alias<Void>::new)
                    .collect(Collectors.toList());
            initAdd(list,
                    target::getColumnAliass,
                    target::setColumnAliass);
            return this;
        }


//        /**
//         * Quick set tableAlias
//         * @param aliasName
//         * @return
//         */
//        public DerivedTableBuilder<ParentBuilder> $As(String aliasName){
//            return withAs()
//                    .withTableAlias(aliasName);
//        }
//
//        /**
//         * Quick set out columnAliass
//         * @param columnAliass
//         * @return
//         */
//        public ParentBuilder $_(String... columnAliass){
//            return withColumnAlias(columnAliass)
//                    .and();
//        }

        /**
         * Quick set tableAlias,columnAlias
         * @param aliasName
         * @param columnAliass
         * @return
         */
        public ParentBuilder $As(String aliasName, String... columnAliass){
            return withAs()
                    .withTableAlias(aliasName)
                    .withColumnAlias(columnAliass)
                    .and();
        }

    }

    /**
     * JoinedTableBuilder
     * @param <ParentBuilder>
     */
    public static class JoinedTableBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<JoinedTableBuilder<ParentBuilder>, ParentBuilder, From.JoinedTable, From.TableSource> {

        public JoinedTableBuilder(From.JoinedTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public JoinedTableBuilder(Setter<From.TableSource> setter) {
            super(new From.JoinedTable(),setter);
        }


        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (target::setTableSource)
                    .in(this);
        }

        public JoinedTableBuilder<ParentBuilder> withTableSource(From.TableSource tableSource) {
            target.setTableSource(tableSource);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinType joinType){
            target.setUseJoinOn(true);
            target.setJoinType(joinType);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withCrossJoin(){
            target.setUseCrossJoin(true);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withCrossApply() {
            target.setUseCrossApply(true);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withOuterApply() {
            target.setUseOuterApply(true);
            return this;
        }

        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource2(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (target::setTableSource2)
                    .in(this);
        }

        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            target::setSearchCondition))
                    .in(this);
        }


        /*
        Quick into
         */

        /**
         * Quick into TableSourceBuilder
         * @return
         */
        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> $(){
            if(target.getTableSource() == null) {
                return withTableSource();
            }
            return withTableSource2();
        }

        /**
         * Quick into BaseTableBuilder
         * And set tableName
         * @param tableName
         * @return
         */
        public BaseTableBuilder<JoinedTableBuilder<ParentBuilder>> $(TableName tableName) {
            if(target.getTableSource() == null){
                return withTableSource()._Base()
                        .withTableName(tableName);
            }
            return withTableSource2()._Base()
                    .withTableName(tableName);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set values
         * @param values
         * @return
         */
        public DerivedTableBuilder<JoinedTableBuilder<ParentBuilder>> $(TableValueConstructor values) {
            if(target.getTableSource() == null){
                return withTableSource()._Derived()
                        .withValues(values);
            }
            return withTableSource2()._Derived()
                    .withValues(values);
        }

        /**
         * Quick into DerivedTableBuilder
         * And set subQuery
         * @param subQuery
         * @return
         */
        public DerivedTableBuilder<JoinedTableBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery) {
            if(target.getTableSource() == null){
                return withTableSource()._Derived()
                        .withSubQuery(subQuery);
            }
            return withTableSource2()._Derived()
                    .withSubQuery(subQuery);
        }

        /**
         * Quick into VariableTableBuilder
         * And set variable
         * @param variable
         * @return
         */
        public VariableTableBuilder<JoinedTableBuilder<ParentBuilder>> $(LocalVariable variable) {
            if(target.getTableSource() == null){
                return withTableSource()._Variable()
                        .withVariable(variable);
            }
            return withTableSource2()._Variable()
                    .withVariable(variable);
        }

        /**
         * Quick into SearchConditionBuilder
         * @return
         */
        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> $On(){
            return withSearchCondition();
        }



        /*
        Quick set
         */

        /**
         * Quick set tableSource/tableSource2:BaseTable
         * into BaseTableBuilder and get-out
         * @param tableName
         * @param tableAlias
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $(TableName tableName, String tableAlias){
            if(target.getTableSource() == null) {
                return withTableSource()._Base()
                        .withTableName(tableName)
                        .withAs()
                        .withTableAlias(tableAlias)
                        .and();
            }
            return withTableSource2()._Base()
                    .withTableName(tableName)
                    .withAs()
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set tableSource/tableSource2::DerivedTable
         * into DerivedTableBuilder and get-out
         * @param values
         * @param tableAlias
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $(TableValueConstructor values, String tableAlias){
            if(target.getTableSource() == null){
                return withTableSource()._Derived()
                        .withValues(values)
                        .withAs()
                        .withTableAlias(tableAlias)
                        .and();
            }
            return withTableSource2()._Derived()
                    .withValues(values)
                    .withAs()
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set tableSource/tableSource2::DerivedTable
         * into DerivedTableBuilder and get-out
         * @param subQuery
         * @param tableAlias
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias){
            if(target.getTableSource() == null){
                return withTableSource()._Derived()
                        .withSubQuery(subQuery)
                        .withAs()
                        .withTableAlias(tableAlias)
                        .and();
            }
            return withTableSource2()._Derived()
                    .withSubQuery(subQuery)
                    .withAs()
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set tableSource/tableSource2:VariableTable
         * into VariableTableBuilder and get-out
         * @param variable
         * @param tableAlias
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $(LocalVariable variable, String tableAlias){
            if(target.getTableSource() == null){
                return withTableSource()._Variable()
                        .withVariable(variable)
                        .withAs()
                        .withTableAlias(tableAlias)
                        .and();
            }
            return withTableSource2()._Variable()
                    .withVariable(variable)
                    .withAs()
                    .withTableAlias(tableAlias)
                    .and();
        }

    }
}
