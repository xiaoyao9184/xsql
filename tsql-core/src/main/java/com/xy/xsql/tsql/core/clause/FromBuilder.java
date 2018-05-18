package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.CodeTreeLazyConfigBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.statement.dml.MergeBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
import com.xy.xsql.tsql.model.clause.hints.TableHint;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.element.Alias;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.statement.dml.Select;
import com.xy.xsql.tsql.model.variable.LocalVariable;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.SUB_QUERY;

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

    public FromBuilder<ParentBuilder> withItem(From.TableSource... tableSources) {
        initAdd(Arrays.asList(tableSources),
                target::getTableSourceList,
                target::setTableSourceList);
        return this;
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
//                .withAs()
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
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .withAs()
                .withTableAlias(tableAlias);
    }

    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QueryExpression subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(SUB_QUERY(subQuery))
                .withAs()
                .withTableAlias(tableAlias);
    }

    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(SUB_QUERY(subQuery))
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
                .withSubQuery(SUB_QUERY(subQuery))
                .and();
    }

    public FromBuilder<ParentBuilder> $(Select subQuery) {
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
         * Confirm type of TableSource
         * @return
         */
        public BaseWithTimeTableBuilder<ParentBuilder> _BaseTime() {
            return new BaseWithTimeTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Quick into sub builder

         Usually we don't into abstract builder,
         but MergeBuilder need in this,
         {@link MergeBuilder#$Using()}
         May be move these methods to MergeBuilder
         *
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
                    .withSubQuery(SUB_QUERY(subQuery));
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
                    .withSubQuery(SUB_QUERY(subQuery))
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
                    .withSubQuery(SUB_QUERY(subQuery));
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
                    .withSubQuery(SUB_QUERY(subQuery))
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
         * @param joinTypeKeyword
         * @param joinHint
         * @return
         */
        private TransformJoinedBuilder<ParentBuilder> transformJoinedTable(From.JoinTypeKeywords joinTypeKeyword, JoinHint joinHint){
            /*
            it will create JoinedTable,
            use target as JoinedTable's TableSource1
            then replace target
             */
            From.JoinType joinType = new From.JoinType();
            joinType.setKeyword(joinTypeKeyword);
            joinType.setJoinHint(joinHint);

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
            return transformJoinedTable(From.JoinTypeKeywords.JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Inner_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Inner_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Inner_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Inner_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REDUCE_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Reduce_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_REDUCE_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REPLICATE_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Replicate_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_REPLICATE_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REDISTRIBUTE_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Inner_Redistribute_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_REDISTRIBUTE_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Left_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Right_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.REMOTE);
        }


        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Full_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Left_Outer_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Outer_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Outer_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Outer_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Left_Outer_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return
         */
        public TransformJoinedBuilder<ParentBuilder> $Full_Outer_Join(){
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, null);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Outer_Loop_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.LOOP);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Outer_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.HASH);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Outer_Merge_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.MERGE);
        }

        public TransformJoinedBuilder<ParentBuilder> $Full_Outer_Remote_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.REMOTE);
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
                transformJoinedTable(From.JoinTypeKeywords.JOIN, null);
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
            target.setTableAlias(aliasName == null ? null : new Alias<>(aliasName));
            return this;
        }

        public TableSampleBuilder<BaseTableBuilder<ParentBuilder>> withTableSample(){
            return new TableSampleBuilder<BaseTableBuilder<ParentBuilder>>
                    (initSet(From.TableSample::new,
                            target::getTableSample,
                            target::setTableSample))
                    .in(this);
        }

        public BaseTableBuilder<ParentBuilder> withTableHint(TableHint... tableHints){
            initAdd(Arrays.asList(tableHints),
                    target::getTableHintList,
                    target::setTableHintList);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withTableSample(From.TableSample tableSample){
            this.target.setTableSample(tableSample);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withTableHint(List<TableHint> tableHints){
            initAdd(tableHints,
                    target::getTableHintList,
                    target::setTableHintList);
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

        public ParentBuilder $(String aliasName) {
            return withTableAlias(aliasName)
                    .and();
        }

        /**
         * Quick set tableHints
         * @param tableHints
         * @return
         */
        public BaseTableBuilder<ParentBuilder> $With(TableHint... tableHints){
            return withTableHint(tableHints);
        }

        /**
         * Quick in TableSample
         * @return
         */
        public TableSampleBuilder<BaseTableBuilder<ParentBuilder>> $TableSample(){
            return withTableSample();
        }

        /**
         * Quick set tableSample
         * @param system
         * @param sampleNumber
         * @param rows
         * @param repeatSeed
         * @return
         */
        public BaseTableBuilder<ParentBuilder> $TableSample(boolean system, Integer sampleNumber, boolean percent, boolean rows, Integer repeatSeed){
            TableSampleBuilder<BaseTableBuilder<ParentBuilder>> b = withTableSample();
            if(system){
                b.withSystem();
            }
            if(sampleNumber != null){
                b.withSampleNumber(sampleNumber);
            }
            if(percent){
                b.withPercent();
            }
            if(rows){
                b.withRows();
            }
            if(repeatSeed != null){
                b.withRepeatSeed(repeatSeed);
            }

            return b.and();
        }

        /**
         * Quick transform to BaseWithTimeTableBuilder
         * @return
         */
        public SystemTimeBuilder<ParentBuilder> $ForSystemTime() {
            From.BaseWithTimeTable timeTable = new From.BaseWithTimeTable();
            timeTable.setTableName(target.getTableName());

            return new SystemTimeBuilder<ParentBuilder>
                    (initSet(From.SystemTime::new,
                            timeTable::getSystemTime,
                            timeTable::setSystemTime))
                    .in(new BaseWithTimeTableBuilder<ParentBuilder>
                            (timeTable,setter)
                            .in(out())
                            .and());
        }
//        public SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>> $ForSystemTime() {
//            From.BaseWithTimeTable timeTable = new From.BaseWithTimeTable();
//            timeTable.setTableName(target.getTableName());
//            return new BaseWithTimeTableBuilder<ParentBuilder>
//                    (timeTable,setter)
//                    .in(and())
//                    .$ForSystemTime();
//        }
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


        public DerivedTableBuilder<ParentBuilder> withSubQuery(Select subQuery){
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
            if(CheckUtil.isNullOrEmpty(columnAliass)){
                return this;
            }
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

        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinTypeKeywords joinType){
            target.setUseJoinOn(true);
            target.setJoinType(new From.JoinType(joinType));
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinTypeKeywords joinType, JoinHint joinHint){
            target.setUseJoinOn(true);
            target.setJoinType(new From.JoinType(joinType,joinHint));
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

        public JoinedTableBuilder<ParentBuilder> withTableSource2(From.TableSource tableSource){
            this.target.setTableSource2(tableSource);
            return this;
        }

        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            target::setSearchCondition))
                    .in(this);
        }

        public JoinedTableBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
            this.target.setSearchCondition(searchCondition);
            return this;
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
                        .withSubQuery(SUB_QUERY(subQuery));
            }
            return withTableSource2()._Derived()
                    .withSubQuery(SUB_QUERY(subQuery));
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
                        .withSubQuery(SUB_QUERY(subQuery))
                        .withAs()
                        .withTableAlias(tableAlias)
                        .and();
            }
            return withTableSource2()._Derived()
                    .withSubQuery(SUB_QUERY(subQuery))
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

    /**
     * BaseWithTimeTableBuilder
     * @param <ParentBuilder>
     */
    public static class BaseWithTimeTableBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<BaseWithTimeTableBuilder<ParentBuilder>, ParentBuilder, From.BaseWithTimeTable, From.TableSource> {

        public BaseWithTimeTableBuilder(From.BaseWithTimeTable target) {
            super(target);
        }

        public BaseWithTimeTableBuilder(From.BaseWithTimeTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public BaseWithTimeTableBuilder(Setter<From.TableSource> setter) {
            super(new From.BaseWithTimeTable(),setter);
        }

        public BaseWithTimeTableBuilder<ParentBuilder> withTableName(TableName tableName){
            target.setTableName(tableName);
            return this;
        }

        public SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>> withSystemTime(){
            return new SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>>
                    (initSet(From.SystemTime::new,
                            target::getSystemTime,
                            target::setSystemTime))
                    .in(this);
        }

        public BaseWithTimeTableBuilder<ParentBuilder> withSystemTime(From.SystemTime systemTime){
            this.target.setSystemTime(systemTime);
            return this;
        }

        public SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>> $ForSystemTime() {
            return withSystemTime();
        }
    }

    /**
     * TableSampleBuilder
     * @param <ParentBuilder>
     */
    public static class TableSampleBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableSampleBuilder<ParentBuilder>, ParentBuilder, From.TableSample> {

        public TableSampleBuilder(From.TableSample tableSample) {
            super(tableSample);
        }

        public TableSampleBuilder<ParentBuilder> withSystem(){
            target.setUseSystem(true);
            return this;
        }

        public TableSampleBuilder<ParentBuilder> withSampleNumber(Integer sampleNumber){
            target.setSampleNumber(new NumberConstant(sampleNumber).withUnsigned().withInteger());
            return this;
        }

        public TableSampleBuilder<ParentBuilder> withPercent(){
            target.setUsePercent(true);
            return this;
        }

        public TableSampleBuilder<ParentBuilder> withRows(){
            target.setUseRows(true);
            return this;
        }

        public TableSampleBuilder<ParentBuilder> withRepeatSeed(Integer repeatSeed){
            target.setRepeatSeed(new NumberConstant(repeatSeed).withUnsigned().withInteger());
            return this;
        }

        /**
         * Quick set
         * @return
         */
        public TableSampleBuilder<ParentBuilder> $System(){
            return withSystem();
        }

        /**
         * Quick set
         * @return
         */
        public TableSampleBuilder<ParentBuilder> $(Integer sampleNumber){
            return withSampleNumber(sampleNumber);
        }

        /**
         * Quick set
         * @return
         */
        public TableSampleBuilder<ParentBuilder> $Percent(Integer sampleNumber){
            return withSampleNumber(sampleNumber).withPercent();
        }

        /**
         * Quick set
         * @return
         */
        public TableSampleBuilder<ParentBuilder> $Rows(Integer sampleNumber){
            return withSampleNumber(sampleNumber).withRows();
        }

        /**
         * Quick set
         * @return
         */
        public TableSampleBuilder<ParentBuilder> $RereaTable(Integer repeatSeed){
            return withRepeatSeed(repeatSeed);
        }
    }

    /**
     * SystemTimeBuilder
     * @param <ParentBuilder>
     */
    public static class SystemTimeBuilder<ParentBuilder>
            extends CodeTreeBuilder<SystemTimeBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

        public SystemTimeBuilder(From.SystemTime systemTime) {
            super(systemTime);
        }


        public ParentBuilder _AsOf(StringConstant dateTime) {
            target.setDateTime(new From.DateTime(dateTime));
            return and();
        }

        public ParentBuilder _AsOf(LocalVariable dateTime) {
            target.setDateTime(new From.DateTime(dateTime));
            return and();
        }

        public ParentBuilder _AsOf(From.DateTime dateTime) {
            target.setDateTime(dateTime);
            return and();
        }

        public FromToBuilder<ParentBuilder> _From() {
            return new FromToBuilder<ParentBuilder>
                    (target)
                    .in(and());
        }

        public BetweenAndBuilder<ParentBuilder> _Between() {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(and());
        }

        public ContainedInBuilder<ParentBuilder> _ContainedIn() {
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(and());
        }

        public ParentBuilder _All() {
            target.setUseAll(true);
            return and();
        }


        /**
         * Quick set
         * @param dateTime
         * @return
         */
        public ParentBuilder $AsOf(String dateTime) {
            return _AsOf(new StringConstant(dateTime).withQuote());
        }

        /**
         * Quick set
         * @param dateTime
         * @return
         */
        public ParentBuilder $AsOf(StringConstant dateTime) {
            return _AsOf(dateTime);
        }

        /**
         * Quick set
         * @param dateTime
         * @return
         */
        public ParentBuilder $AsOf(LocalVariable dateTime) {
            return _AsOf(dateTime);
        }

        /**
         * Quick set
         * @param startDateTime
         * @param endDateTime
         * @return
         */
        public ParentBuilder $FromTo(String startDateTime, String endDateTime) {
            return _From()
                    .withFrom(new StringConstant(startDateTime).withQuote())
                    .withTo(new StringConstant(endDateTime).withQuote())
                    .and();
        }
        /**
         * Quick set
         * @param startDateTime
         * @param endDateTime
         * @return
         */
        public ParentBuilder $BetweenAnd(String startDateTime, String endDateTime) {
            return _Between()
                    .withBetween(new StringConstant(startDateTime).withQuote())
                    .withAnd(new StringConstant(endDateTime).withQuote())
                    .and();
        }
        /**
         * Quick set
         * @param startDateTime
         * @param endDateTime
         * @return
         */
        public ParentBuilder $ContainedIn(String startDateTime, String endDateTime) {
            return _ContainedIn()
                    .withStart(new StringConstant(startDateTime).withQuote())
                    .withEnd(new StringConstant(endDateTime).withQuote())
                    .and();
        }

        /**
         * Quick set
         * @return
         */
        public ParentBuilder $All() {
            return _All();
        }


        /**
         * Quick in
         * @param startDateTime
         * @return
         */
        public FromToBuilder<ParentBuilder> $From(StringConstant startDateTime) {
            return new FromToBuilder<ParentBuilder>
                    (target)
                    .in(and())
                    .$From(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime
         * @return
         */
        public FromToBuilder<ParentBuilder> $From(LocalVariable startDateTime) {
            return new FromToBuilder<ParentBuilder>
                    (target)
                    .in(and())
                    .$From(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime
         * @return
         */
        public BetweenAndBuilder<ParentBuilder> $Between(StringConstant startDateTime) {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(and())
                    .$Between(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime
         * @return
         */
        public BetweenAndBuilder<ParentBuilder> $Between(LocalVariable startDateTime) {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(and())
                    .$Between(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime
         * @return
         */
        public ContainedInBuilder<ParentBuilder> $ContainedIn(StringConstant startDateTime) {
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(and())
                    .$In(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime
         * @return
         */
        public ContainedInBuilder<ParentBuilder> $ContainedIn(LocalVariable startDateTime) {
            target.setUseBetween(true);
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(and())
                    .$In(startDateTime);
        }



        public static class FromToBuilder<ParentBuilder>
                extends CodeTreeBuilder<FromToBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public FromToBuilder(From.SystemTime systemTime) {
                super(systemTime);
                target.setUseFrom(true);
            }

            public FromToBuilder<ParentBuilder> withFrom(StringConstant startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            public FromToBuilder<ParentBuilder> withFrom(LocalVariable startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            public FromToBuilder<ParentBuilder> withFrom(From.DateTime dateTime) {
                target.setStartDateTime(dateTime);
                return this;
            }

            public FromToBuilder<ParentBuilder> withTo(StringConstant endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            public FromToBuilder<ParentBuilder> withTo(LocalVariable endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            public FromToBuilder<ParentBuilder> withTo(From.DateTime endDateTime) {
                target.setEndDateTime(endDateTime);
                return this;
            }


            /**
             * Quick set
             * @param startDateTime
             * @return
             */
            public FromToBuilder<ParentBuilder> $From(StringConstant startDateTime){
                return withFrom(startDateTime);
            }

            /**
             * Quick set
             * @param startDateTime
             * @return
             */
            public FromToBuilder<ParentBuilder> $From(LocalVariable startDateTime){
                return withFrom(startDateTime);
            }

            /**
             * Quick set
             * @param endDateTime
             * @return
             */
            public ParentBuilder $To(LocalVariable endDateTime){
                return withTo(endDateTime)
                        .and();
            }

            /**
             * Quick set
             * @param endDateTime
             * @return
             */
            public ParentBuilder $To(StringConstant endDateTime){
                return withTo(endDateTime)
                        .and();
            }
        }

        public static class BetweenAndBuilder<ParentBuilder>
                extends CodeTreeBuilder<BetweenAndBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public BetweenAndBuilder(From.SystemTime systemTime) {
                super(systemTime);
                target.setUseBetween(true);
            }

            public BetweenAndBuilder<ParentBuilder> withBetween(StringConstant startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            public BetweenAndBuilder<ParentBuilder> withBetween(LocalVariable startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            public BetweenAndBuilder<ParentBuilder> withBetween(From.DateTime dateTime) {
                target.setStartDateTime(dateTime);
                return this;
            }

            public BetweenAndBuilder<ParentBuilder> withAnd(StringConstant endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            public BetweenAndBuilder<ParentBuilder> withAnd(LocalVariable endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            public BetweenAndBuilder<ParentBuilder> withAnd(From.DateTime endDateTime) {
                target.setEndDateTime(endDateTime);
                return this;
            }


            /**
             * Quick set
             * @param startDateTime
             * @return
             */
            public BetweenAndBuilder<ParentBuilder> $Between(LocalVariable startDateTime){
                return withBetween(startDateTime);
            }

            /**
             * Quick set
             * @param startDateTime
             * @return
             */
            public BetweenAndBuilder<ParentBuilder> $Between(StringConstant startDateTime){
                return withBetween(startDateTime);
            }

            /**
             *
             * @param endDateTime
             * @return
             */
            public ParentBuilder $And(LocalVariable endDateTime){
                return withAnd(endDateTime)
                        .and();
            }

            /**
             *
             * @param endDateTime
             * @return
             */
            public ParentBuilder $And(StringConstant endDateTime){
                return withAnd(endDateTime)
                        .and();
            }

        }

        public static class ContainedInBuilder<ParentBuilder>
                extends CodeTreeBuilder<ContainedInBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public ContainedInBuilder(From.SystemTime systemTime) {
                super(systemTime);
                target.setUseContained(true);
            }

            public ContainedInBuilder<ParentBuilder> withStart(StringConstant startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            public ContainedInBuilder<ParentBuilder> withStart(From.DateTime startDateTime) {
                target.setStartDateTime(startDateTime);
                return this;
            }

            public ContainedInBuilder<ParentBuilder> withStart(LocalVariable startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            public ContainedInBuilder<ParentBuilder> withEnd(StringConstant endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            public ContainedInBuilder<ParentBuilder> withEnd(LocalVariable endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            public ContainedInBuilder<ParentBuilder> withEnd(From.DateTime endDateTime) {
                target.setEndDateTime(endDateTime);
                return this;
            }


            /**
             * Quick set
             * @param startDateTime
             * @return
             */
            public ContainedInBuilder<ParentBuilder> $In(LocalVariable startDateTime){
                return withStart(startDateTime);
            }

            /**
             * Quick set
             * @param startDateTime
             * @return
             */
            public ContainedInBuilder<ParentBuilder> $In(StringConstant startDateTime){
                return withStart(startDateTime);
            }

            /**
             *
             * @param endDateTime
             * @return
             */
            public ParentBuilder $(LocalVariable endDateTime){
                return withEnd(endDateTime)
                        .and();
            }

            /**
             *
             * @param endDateTime
             * @return
             */
            public ParentBuilder $(StringConstant endDateTime){
                return withEnd(endDateTime)
                        .and();
            }

        }

    }

}
