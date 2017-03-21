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


    /**
     * Quick in TableSourceBuilder
     *
     * @return
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> $() {
        return withItem();
    }




    /*
    If the TableSource is NOT single,
    Then the TableSource alias is very meaningful,
    you can quickly confirm TableSource type use these method
     */

    /**
     * Quick in BaseTableBuilder
     *
     * @param tableName
     * @return
     */
    public BaseTableBuilder<FromBuilder<ParentBuilder>> $(TableName tableName, String tableAlias) {
        return withItem()._Base()
                .withTableName(tableName)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in DerivedTableBuilder
     *
     * @param values
     * @return
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(TableValueConstructor values, String tableAlias) {
        return withItem()._Derived()
                .withValues(values)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in DerivedTableBuilder
     *
     * @param subQuery
     * @return
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in VariableTableBuilder
     *
     * @param variable
     * @return
     */
    public VariableTableBuilder<FromBuilder<ParentBuilder>> $(LocalVariable variable, String tableAlias) {
        return withItem()._Variable()
                .withVariable(variable)
                .withAs()
                .withTableAlias(tableAlias);
    }




    /*
    If the TableSource is single,
    then the TableSource alias is of little significance,
    you can quickly build use these method
     */

    /**
     * Quick inout BaseTableBuilder build BaseTable TableSource
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


        /**
         * Quick inout set BaseTableBuilder
         *
         * @param tableName
         * @return
         */
        public BaseTableBuilder<ParentBuilder> $(TableName tableName) {
            return _Base()
                    .withTableName(tableName);
        }

        /**
         * Quick inout set DerivedTableBuilder subQuery
         *
         * @param subQuery
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery) {
            return _Derived()
                    .withSubQuery(subQuery);
        }

        /**
         * Quick inout set DerivedTableBuilder values
         *
         * @param values
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(TableValueConstructor values) {
            return _Derived()
                    .withValues(values);
        }

        /**
         * Quick inout set VariableTableBuilder
         *
         * @param variable
         * @return
         */
        public VariableTableBuilder<ParentBuilder> $(LocalVariable variable) {
            return _Variable()
                    .withVariable(variable);
        }


        /**
         * Quick in set BaseTableBuilder
         *
         * @param tableName
         * @return
         */
        public BaseTableBuilder<ParentBuilder> $(TableName tableName, String tableAlias) {
            return _Base()
                    .withTableName(tableName)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick in set DerivedTableBuilder
         *
         * @param values
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(TableValueConstructor values, String tableAlias) {
            return _Derived()
                    .withValues(values)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick in set DerivedTableBuilder
         *
         * @param subQuery
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias) {
            return _Derived()
                    .withSubQuery(subQuery)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick in set VariableTableBuilder
         *
         * @param variable
         * @return
         */
        public VariableTableBuilder<ParentBuilder> $(LocalVariable variable, String tableAlias) {
            return _Variable()
                    .withVariable(variable)
                    .withAs()
                    .withTableAlias(tableAlias);
        }


    }


    public static abstract class TransformJoinedBuilder<This, ParentBuilder, Target extends From.TableSource>
            extends CodeTreeLazyConfigBuilder<This, ParentBuilder, Target, From.TableSource> {

        public TransformJoinedBuilder(Target target) {
            super(target);
        }

        public TransformJoinedBuilder(Target target, Setter<From.TableSource> setter) {
            super(target, setter);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Cross_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withCrossJoin()
                    .in(this.out());
        }


        public JoinedTableBuilder<ParentBuilder> $Cross_Apply(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    //TODO
                    .withCrossJoin()
                    .in(this.out());
        }


        public JoinedTableBuilder<ParentBuilder> $Outer_Apply(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    //TODO
                    .withCrossJoin()
                    .in(this.out());
        }


        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Inner_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.INNER_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Inner_Reduce_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.INNER_REDUCE_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Inner_Replicate_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.INNER_REPLICATE_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Inner_Redistribute_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.INNER_REDISTRIBUTE_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Left_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.LEFT_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Right_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.RIGHT_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Full_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.FULL_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Left_Outer_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.LEFT_OUTER_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Right_Outer_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.RIGHT_OUTER_JOIN)
                    .in(this.out());
        }

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Full_Outer_Join(){
            return new JoinedTableBuilder<ParentBuilder>
                    (new From.JoinedTable(),setter)
                    .withTableSource(this.build())
                    .withJoinType(From.JoinType.FULL_OUTER_JOIN)
                    .in(this.out());
        }
    }



    /**
     * BaseTableBuilder
     * @param <ParentBuilder>
     */
    public static class BaseTableBuilder<ParentBuilder>
            extends TransformJoinedBuilder<BaseTableBuilder<ParentBuilder>,ParentBuilder,From.BaseTable> {

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
        public BaseTableBuilder<ParentBuilder> $As(String aliasName){
            return withAs()
                    .withTableAlias(aliasName);
        }

    }

    /**
     * VariableTableBuilder
     * @param <ParentBuilder>
     */
    public static class VariableTableBuilder<ParentBuilder>
            extends TransformJoinedBuilder<VariableTableBuilder<ParentBuilder>,ParentBuilder,From.VariableTable> {

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
            extends TransformJoinedBuilder<DerivedTableBuilder<ParentBuilder>,ParentBuilder,From.DerivedTable> {

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


        /**
         * Quick set tableAlias
         * @param aliasName
         * @return
         */
        public DerivedTableBuilder<ParentBuilder> $As(String aliasName){
            return withAs()
                    .withTableAlias(aliasName);
        }

        /**
         * Quick set out columnAliass
         * @param columnAliass
         * @return
         */
        public ParentBuilder $_(String... columnAliass){
            return withColumnAlias(columnAliass)
                    .and();
        }

    }



    /**
     * JoinedTableBuilder
     * @param <ParentBuilder>
     */
    public static class JoinedTableBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<JoinedTableBuilder<ParentBuilder>,ParentBuilder,From.JoinedTable,From.TableSource> {

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
            target.setJoinType(joinType);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withCrossJoin(){
            target.setUseCrossJoin(true);
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


        /**
         * Quick in TableSourceBuilder
         * @return
         */
        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> $(){
            if(target.getTableSource() == null) {
                return withTableSource();
            }
            return withTableSource2();
        }

        /**
         * Quick in BaseTableBuilder
         *
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
         * Quick in DerivedTableBuilder
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
         * Quick in set DerivedTableBuilder
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
         * Quick in VariableTableBuilder
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
         * Quick in SearchConditionBuilder
         * @return
         */
        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> $On(){
            return withSearchCondition();
        }






        /**
         * Quick inout BaseTableBuilder set TableSource or TableSource2
         * @param tableName
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
         * Quick inout DerivedTableBuilder set TableSource or TableSource2
         * @param values
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
         * Quick inout DerivedTableBuilder set TableSource or TableSource2
         * @param subQuery
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
         * Quick inout VariableTableBuilder set TableSource or TableSource2
         * @param variable
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
