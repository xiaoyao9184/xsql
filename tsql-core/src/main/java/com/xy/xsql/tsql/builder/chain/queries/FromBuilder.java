package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.builder.chain.statements.MergeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.GetterSetterObjectHandler.object;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_unsigned_integer;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;

/**
 * FromBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class FromBuilder<ParentBuilder>
        extends ParentHoldBuilder<FromBuilder<ParentBuilder>,ParentBuilder,From> {

    public FromBuilder() {
        super(new From());
    }

    public FromBuilder(From target) {
        super(target);
    }

    /**
     * set
     * @param tableSources TableSource
     * @return THIS
     */
    public FromBuilder<ParentBuilder> withItem(From.TableSource... tableSources) {
        list(target::getTableSourceList, target::setTableSourceList)
                .addAll(tableSources);
        return this;
    }

    /**
     * in
     * @return TableSourceBuilder
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> withItem() {
        list(target::getTableSourceList, target::setTableSourceList).init();
        return new TableSourceBuilder<FromBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), target.getTableSourceList()::add);
    }




    /*
    Quick into
     */

    /**
     * Quick in TableSourceBuilder
     * @return TransformJoinedBuilder
     */
    public TransformJoinedBuilder<FromBuilder<ParentBuilder>> $() {
        list(target::getTableSourceList, target::setTableSourceList).init();
        return new TransformJoinedBuilder<FromBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), target.getTableSourceList()::add);
    }


    /*
    If the TableSource is NOT single,
    Then the TableSource alias is very meaningful,
    you can quickly confirm TableSource type use these method
     */

    /**
     * Quick in BaseTableBuilder
     * And set tableName,tableAlias
     * @param tableName TableName
     * @param tableAlias table alias
     * @return BaseTableBuilder
     */
    public TransformBaseTimeBuilder<FromBuilder<ParentBuilder>> $(TableName tableName, String tableAlias) {
        list(target::getTableSourceList, target::setTableSourceList).init();
        return new TransformBaseTimeBuilder<FromBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), target.getTableSourceList()::add)
                .$(tableName,tableAlias);
    }
//    public BaseTableBuilder<FromBuilder<ParentBuilder>> $(TableName tableName, String tableAlias) {
//        return withItem()._Base()
//                .withTableName(tableName)
////                .withAs()
//                .withTableAlias(tableAlias);
//    }

    /**
     * Quick in DerivedTableBuilder
     * And set values,tableAlias
     * @param values TableValueConstructor
     * @param tableAlias table alias
     * @return DerivedTableBuilder
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(TableValueConstructor values, String tableAlias) {
        return withItem()._Derived()
                .withValues(values)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in DerivedTableBuilder
     * And set subQuery,tableAlias
     * @param subQuery Select
     * @param tableAlias table alias
     * @return DerivedTableBuilder
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * uick into DerivedTableBuilder
     * And set subQuery,tableAlias
     * @param subQuery QueryExpression
     * @param tableAlias table alias
     * @return DerivedTableBuilder
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QueryExpression subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery(Queries.$SubQuery(subQuery))
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * uick into DerivedTableBuilder
     * And set subQuery,tableAlias
     * @param subQuery QuerySpecification
     * @param tableAlias table alias
     * @return DerivedTableBuilder
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery, String tableAlias) {
        return withItem()._Derived()
                .withSubQuery($SubQuery(subQuery))
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in VariableTableBuilder
     * And set variable,tableAlias
     * @param variable LocalVariable
     * @param tableAlias table alias
     * @return VariableTableBuilder
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
     * @param tableName TableName
     * @return THIS
     */
    public FromBuilder<ParentBuilder> $(TableName tableName) {
        return withItem()._Base()
                .withTableName(tableName)
                .and();
    }

    /**
     * Quick inout DerivedTableBuilder build DerivedTable(subQuery) TableSource
     * @param subQuery QuerySpecification
     * @return THIS
     */
    public FromBuilder<ParentBuilder> $(Select.QuerySpecification subQuery) {
        return withItem()._Derived()
                .withSubQuery($SubQuery(subQuery))
                .and();
    }

    /**
     * Quick set
     * @param subQuery Select
     * @return THIS
     */
    public FromBuilder<ParentBuilder> $(Select subQuery) {
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick inout DerivedTableBuilder build DerivedTable(values) TableSource
     * @param values TableValueConstructor
     * @return THIS
     */
    public FromBuilder<ParentBuilder> $(TableValueConstructor values) {
        return withItem()._Derived()
                .withValues(values)
                .and();
    }

    /**
     * Quick inout VariableTableBuilder build VariableTable TableSource
     * @param variable LocalVariable
     * @return THIS
     */
    public FromBuilder<ParentBuilder> $(LocalVariable variable) {
        return withItem()._Variable()
                .withVariable(variable)
                .and();
    }



    /**
     * Abstract TableSource Builder
     * @param <ParentBuilder>
     */
    @SuppressWarnings("DanglingJavadoc")
    public static class TableSourceBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<TableSourceBuilder<ParentBuilder>, ParentBuilder, From.TableSource> {

        public TableSourceBuilder() {}


        /**
         * Confirm type of TableSource
         * @return BaseTableBuilder
         */
        public BaseTableBuilder<ParentBuilder> _Base() {
            return new BaseTableBuilder<ParentBuilder>
                    (object(From.BaseTable::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of TableSource
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<ParentBuilder> _Derived() {
            return new DerivedTableBuilder<ParentBuilder>
                    (object(From.DerivedTable::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of TableSource
         * @return JoinedTableBuilder
         */
        public JoinedTableBuilder<ParentBuilder> _Joined() {
            return new JoinedTableBuilder<ParentBuilder>
                    (object(From.JoinedTable::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of TableSource
         * @return VariableTableBuilder
         */
        public VariableTableBuilder<ParentBuilder> _Variable() {
            return new VariableTableBuilder<ParentBuilder>
                    (object(From.VariableTable::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of TableSource
         * @return BaseWithTimeTableBuilder
         */
        public BaseWithTimeTableBuilder<ParentBuilder> _BaseTime() {
            return new BaseWithTimeTableBuilder<ParentBuilder>
                    (object(From.BaseWithTimeTable::new).set(this::init))
                    .in(this.and());
        }




        /**
         * Quick in sub builder

         Usually we don't into abstract builder,
         but MergeBuilder need in this,
         {@link MergeBuilder#$Using()}
         May be move these methods to MergeBuilder
         *
         */

        /**
         * Quick in BaseTableBuilder
         * And set tableName
         * @param tableName TableName
         * @return BaseTableBuilder
         */
        public BaseTableBuilder<ParentBuilder> $(TableName tableName) {
            return _Base()
                    .withTableName(tableName);
        }

        /**
         * Quick in DerivedTableBuilder
         * And set subQuery
         * @param subQuery QuerySpecification
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery) {
            return _Derived()
                    .withSubQuery($SubQuery(subQuery));
        }

        /**
         * Quick in DerivedTableBuilder
         * And set values
         * @param values TableValueConstructor
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<ParentBuilder> $(TableValueConstructor values) {
            return _Derived()
                    .withValues(values);
        }

        /**
         * Quick in VariableTableBuilder
         * And set variable
         * @param variable LocalVariable
         * @return VariableTableBuilder
         */
        public VariableTableBuilder<ParentBuilder> $(LocalVariable variable) {
            return _Variable()
                    .withVariable(variable);
        }

        /**
         * Quick in BaseTableBuilder
         * And set tableName,tableAlias
         * @param tableName TableName
         * @param tableAlias table alias
         * @return BaseTableBuilder
         */
        public BaseTableBuilder<ParentBuilder> $(TableName tableName, String tableAlias) {
            return _Base()
                    .withTableName(tableName)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick in DerivedTableBuilder
         * And set values,tableAlias
         * @param values TableValueConstructor
         * @param tableAlias table alias
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<ParentBuilder> $(TableValueConstructor values, String tableAlias) {
            return _Derived()
                    .withValues(values)
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick in DerivedTableBuilder
         * And set subQuery,tableAlias
         * @param subQuery QuerySpecification
         * @param tableAlias table alias
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias) {
            return _Derived()
                    .withSubQuery($SubQuery(subQuery))
                    .withAs()
                    .withTableAlias(tableAlias);
        }

        /**
         * Quick in VariableTableBuilder
         * And set variable,tableAlias
         * @param variable LocalVariable
         * @param tableAlias table alias
         * @return VariableTableBuilder
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
            extends ParentHoldLazyConfigBuilder<TransformJoinedBuilder<ParentBuilder>, ParentBuilder, From.TableSource> {

        public TransformJoinedBuilder() {}

        //use this setter to set target
        private Setter<From.TableSource> current = this::init;


        /*
        Quick into sub builder
         */

        /**
         * Quick in BaseTableBuilder
         * And set tableName
         * @param tableName TableName
         * @return BaseTableBuilder
         */
        public BaseTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(TableName tableName) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Base()
                    .withTableName(tableName);
        }

        /**
         * Quick in DerivedTableBuilder
         * And set subQuery
         * @param subQuery QuerySpecification
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Derived()
                    .withSubQuery($SubQuery(subQuery));
        }

        /**
         * Quick in DerivedTableBuilder
         * And set values
         * @param values TableValueConstructor
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(TableValueConstructor values) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Derived()
                    .withValues(values);
        }

        /**
         * Quick in VariableTableBuilder
         * And set variable
         * @param variable LocalVariable
         * @return VariableTableBuilder
         */
        public VariableTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(LocalVariable variable) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Variable()
                    .withVariable(variable);
        }




        /*
        Quick set
         */

        /**
         * Quick set TableSource:BaseTable
         * into BaseTableBuilder and get-out
         * @param tableName TableName
         * @param tableAlias table alias
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $(TableName tableName, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Base()
                    .withTableName(tableName)
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set tableSource:DerivedTable
         * into DerivedTableBuilder and get-out
         * @param values TableValueConstructor
         * @param tableAlias table alias
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $(TableValueConstructor values, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Derived()
                    .withValues(values)
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set TableSource:DerivedTable
         * into DerivedTableBuilder and get-out
         * @param subQuery QuerySpecification
         * @param tableAlias table alias
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Derived()
                    .withSubQuery($SubQuery(subQuery))
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set TableSource:VariableTable
         * into VariableTableBuilder and get-out
         * @param variable LocalVariable
         * @param tableAlias table alias
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $(LocalVariable variable, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::switchTarget2Default)
                    ._Variable()
                    .withVariable(variable)
                    .withTableAlias(tableAlias)
                    .and();
        }

        private void switchTarget2Default(From.TableSource tableSource){
            this.current.set(tableSource);
            this.current = this::init;
        }




        /*
        Quick transform
         */

        /**
         * Quick transform to JoinedTableBuilder
         * And set flag CrossJoin
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $Cross_Join() {
            From.TableSource now = this.target;
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b =
                    new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this.current)
                    ._Joined()
                    .withTableSource(now)
                    .withCrossJoin();
            //switch target setter
            this.current = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * And set flag CrossApply
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $Cross_Apply() {
            From.TableSource now = this.target;
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b =
                    new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this.current)
                    ._Joined()
                    .withTableSource(now)
                    .withCrossApply();
            //switch target setter
            this.current = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * And set flag Outer_Apply
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $Outer_Apply() {
            From.TableSource now = this.target;
            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b =
                    new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this.current)
                    ._Joined()
                    .withTableSource(now)
                    .withOuterApply();
            //switch target setter
            this.current = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * And set joinType
         * @param joinTypeKeyword JoinTypeKeywords
         * @param joinHint JoinHint
         * @return THIS
         */
        private TransformJoinedBuilder<ParentBuilder> transformJoinedTable(From.JoinTypeKeywords joinTypeKeyword, JoinHint joinHint){
            /*
            it will create JoinedTable,
            use target as JoinedTable's TableSource1
            then replace target
             */
            From.TableSource now = this.target;
            From.JoinType joinType = new From.JoinType();
            joinType.setKeyword(joinTypeKeyword);
            joinType.setJoinHint(joinHint);

            JoinedTableBuilder<TransformJoinedBuilder<ParentBuilder>> b =
                    new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this.current)
                    ._Joined()
                    .withTableSource(now)
                    .withJoinType(joinType);
            //switch target setter
            this.current = b.build()::setTableSource2;
            return b.and();
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:JOIN
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $Join(){
            return transformJoinedTable(From.JoinTypeKeywords.JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerHashJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_JOIN
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.INNER_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REDUCE_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerReduceJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_REDUCE_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REPLICATE_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerReplicateJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_REPLICATE_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:INNER_REDISTRIBUTE_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $InnerRedistributeJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.INNER_REDISTRIBUTE_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftHashJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightHashJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullHashJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftOuterJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftOuterLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftOuterHashJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftOuterMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:LEFT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $LeftOuterRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.LEFT_OUTER_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightOuterJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightOuterLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $Right_Outer_Hash_Join() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightOuterMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:RIGHT_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $RightOuterRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.RIGHT_OUTER_JOIN, JoinHint.REMOTE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullOuterJoin(){
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, null);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullOuterLoopJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.LOOP);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullOuterHashJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.HASH);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullOuterMergeJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.MERGE);
        }

        /**
         * Quick transform to JoinedTableBuilder
         * Use JoinType:FULL_OUTER_JOIN
         * @return TransformJoinedBuilder
         */
        public TransformJoinedBuilder<ParentBuilder> $FullOuterRemoteJoin() {
            return transformJoinedTable(From.JoinTypeKeywords.FULL_OUTER_JOIN, JoinHint.REMOTE);
        }


        /*
        Quick into if already transform to JoinedTable
         */

        /**
         * Quick in SearchConditionBuilder
         * And set TableSource:JoinedTable' searchCondition
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<TransformJoinedBuilder<ParentBuilder>> $On() {
            if(!(this.target instanceof From.JoinedTable)){
                transformJoinedTable(From.JoinTypeKeywords.JOIN, null);
            }
            From.JoinedTable joinedTable = (From.JoinedTable) this.target;
            return new SearchConditionBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (object(joinedTable::getSearchCondition, joinedTable::setSearchCondition)
                            .init(SearchCondition::new))
                    .in(this);
        }
    }

    /**
     * TransformBaseTimeBuilder
     * @param <ParentBuilder>
     */
    public static class TransformBaseTimeBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<TransformBaseTimeBuilder<ParentBuilder>, ParentBuilder, From.TableSource> {

        public TransformBaseTimeBuilder() {}


        /*
        Quick into sub builder
         */

        /**
         * Quick set TableSource:BaseTable
         * into BaseTableBuilder and get-out
         * @param tableName TableName
         * @return THIS
         */
        public TransformBaseTimeBuilder<ParentBuilder> $(TableName tableName) {
            return new TableSourceBuilder<TransformBaseTimeBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::init)
                    ._Base()
                    .withTableName(tableName)
                    .and();
        }

        /**
         * Quick set TableSource:BaseTable
         * into BaseTableBuilder and get-out
         * @param tableName TableName
         * @param tableAlias table alias
         * @return THIS
         */
        public TransformBaseTimeBuilder<ParentBuilder> $(TableName tableName, String tableAlias) {
            return new TableSourceBuilder<TransformBaseTimeBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), this::init)
                    ._Base()
                    .withTableName(tableName)
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick transform TableSource:BaseTable
         * @param aliasName alias name
         * @return PARENT
         */
        public ParentBuilder $As(String aliasName){
            return new BaseTableBuilder<ParentBuilder>
                    ((From.BaseTable) target)
                    .in(this.and())
                    .withAs()
                    .withTableAlias(aliasName)
                    .and();
        }


        /**
         * Quick transform TableSource:BaseTable
         * @param tableHints TableHint
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> $With(TableHint... tableHints){
            return new BaseTableBuilder<ParentBuilder>
                    ((From.BaseTable) target)
                    .in(this.and())
                    .withTableHint(tableHints);
        }

        /**
         * Quick transform TableSource:BaseTable
         * @return TableSampleBuilder
         */
        public TableSampleBuilder<BaseTableBuilder<ParentBuilder>> $TableSample(){
            return new BaseTableBuilder<ParentBuilder>
                    ((From.BaseTable) target)
                    .in(this.and())
                    .withTableSample();
        }

        /**
         * Quick transform TableSource:BaseTable
         * @param system system
         * @param sampleNumber sample number
         * @param percent percent
         * @param rows rows
         * @param repeatSeed repeat seed
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> $TableSample(boolean system, Integer sampleNumber, boolean percent, boolean rows, Integer repeatSeed){
            return new BaseTableBuilder<ParentBuilder>
                    ((From.BaseTable) target)
                    .in(this.and())
                    .$TableSample(system, sampleNumber, percent, rows, repeatSeed);
        }

        /**
         * Quick transform to BaseWithTimeTableBuilder and SystemTimeBuilder
         * @return SystemTimeBuilder
         */
        public SystemTimeBuilder<ParentBuilder> $ForSystemTime() {
            //get BaseTable's TableName for use
            TableName tableName = ((From.BaseTable)target).getTableName();
            //replace target form BaseTable to BaseWithTimeTable
            From.BaseWithTimeTable timeTable = new BaseWithTimeTableBuilder<ParentBuilder>
                    (object(From.BaseWithTimeTable::new).set(this::init))
                    .withTableName(tableName)
                    .build();
            //in BaseWithTimeTable's SystemTime builder
            return new SystemTimeBuilder<ParentBuilder>
                    (object(timeTable::getSystemTime, timeTable::setSystemTime)
                            .init(From.SystemTime::new))
                    .in(this.and());
        }

    }

    /**
     * BaseTableBuilder
     * @param <ParentBuilder>
     */
    public static class BaseTableBuilder<ParentBuilder>
            extends ParentHoldLazyConfigBuilder<BaseTableBuilder<ParentBuilder>, ParentBuilder, From.BaseTable> {

        public BaseTableBuilder() {
            super(new From.BaseTable());
        }

        public BaseTableBuilder(From.BaseTable target) {
            super(target);
        }

        /**
         * set
         * @param tableName TableName
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableName(TableName tableName){
            target.setTableName(tableName);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        /**
         * set
         * @param aliasName alias name
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            target.setTableAlias(aliasName == null ? null : new Alias<>(aliasName));
            return this;
        }

        /**
         * set
         * @param tableSample TableSample
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableSample(From.TableSample tableSample){
            this.target.setTableSample(tableSample);
            return this;
        }

        /**
         * in
         * @return TableSampleBuilder
         */
        public TableSampleBuilder<BaseTableBuilder<ParentBuilder>> withTableSample(){
            return new TableSampleBuilder<BaseTableBuilder<ParentBuilder>>
                    (object(target::getTableSample, target::setTableSample)
                            .init(From.TableSample::new))
                    .in(this);
        }

        /**
         * set
         * @param tableHints TableHint
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableHint(TableHint... tableHints){
            list(target::getTableHintList, target::setTableHintList)
                    .addAll(tableHints);
            return this;
        }

        /**
         * set
         * @param tableHints TableHint
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableHint(List<TableHint> tableHints){
            list(target::getTableHintList, target::setTableHintList)
                    .addAll(tableHints);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set tableAlias
         * @param aliasName alias name
         * @return PARENT
         */
        public ParentBuilder $As(String aliasName){
            return withAs()
                    .withTableAlias(aliasName)
                    .and();
        }

        /**
         * Quick set tableAlias
         * @param aliasName alias name
         * @return PARENT
         */
        public ParentBuilder $(String aliasName) {
            return withTableAlias(aliasName)
                    .and();
        }

        /**
         * Quick set tableHints
         * @param tableHints TableHint
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> $With(TableHint... tableHints){
            return withTableHint(tableHints);
        }

        /**
         * Quick in TableSample
         * @return TableSampleBuilder
         */
        public TableSampleBuilder<BaseTableBuilder<ParentBuilder>> $TableSample(){
            return withTableSample();
        }

        /**
         * Quick set tableSample
         * @param system system
         * @param sampleNumber sample number
         * @param percent percent
         * @param rows rows
         * @param repeatSeed repeat seed
         * @return THIS
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
         * @return SystemTimeBuilder
         */
        //TODO
//        public SystemTimeBuilder<ParentBuilder> $ForSystemTime() {
//            From.BaseWithTimeTable timeTable = new From.BaseWithTimeTable();
//            timeTable.setTableName(target.getTableName());
//
//            BaseWithTimeTableBuilder<ParentBuilder> b = new BaseWithTimeTableBuilder<ParentBuilder>
//                    (timeTable)
//                    .in(this.and())
//                    .enter(this.out(), Getter.empty(), )
//                    .withTableName(target.getTableName());
//
//
//            return new SystemTimeBuilder<ParentBuilder>
//                    (initSet(From.SystemTime::new,
//                            timeTable::getSystemTime,
//                            timeTable::setSystemTime))
//                    .in(b.and());
//        }
//        public SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>> $ForSystemTime() {
//            From.BaseWithTimeTable timeTable = new From.BaseWithTimeTable();
//            timeTable.setTableName(target.getTableName());
//            return new BaseWithTimeTableBuilder<ParentBuilder>
//                    (timeTable,setter)
//                    .in(this.and())
//                    .$ForSystemTime();
//        }
    }

    /**
     * VariableTableBuilder
     * @param <ParentBuilder>
     */
    public static class VariableTableBuilder<ParentBuilder>
            extends ParentHoldBuilder<VariableTableBuilder<ParentBuilder>, ParentBuilder, From.VariableTable> {

        public VariableTableBuilder() {
            super(new From.VariableTable());
        }

        public VariableTableBuilder(From.VariableTable target) {
            super(target);
        }

        /**
         * set
         * @param variable LocalVariable
         * @return THIS
         */
        public VariableTableBuilder<ParentBuilder> withVariable(LocalVariable variable){
            target.setVariable(variable);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public VariableTableBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        /**
         * set
         * @param aliasName alias name
         * @return THIS
         */
        public VariableTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            target.setTableAlias(new Alias<>(aliasName));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set tableAlias
         * @param aliasName alias name
         * @return PARENT
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
            extends ParentHoldBuilder<DerivedTableBuilder<ParentBuilder>, ParentBuilder, From.DerivedTable> {

        public DerivedTableBuilder() {
            super(new From.DerivedTable());
        }
        
        public DerivedTableBuilder(From.DerivedTable target) {
            super(target);
        }


        /**
         * set
         * @param subQuery Select
         * @return THIS
         */
        public DerivedTableBuilder<ParentBuilder> withSubQuery(Select subQuery){
            target.setSubQuery(subQuery);
            return this;
        }

        /**
         * set
         * @param values TableValueConstructor
         * @return THIS
         */
        public DerivedTableBuilder<ParentBuilder> withValues(TableValueConstructor values){
            target.setValues(values);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public DerivedTableBuilder<ParentBuilder> withAs(){
            target.setUseAs(true);
            return this;
        }

        /**
         * set
         * @param aliasName alias name
         * @return THIS
         */
        public DerivedTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            target.setTableAlias(new Alias<>(aliasName));
            return this;
        }

        /**
         * set
         * @param columnAliass column alias
         * @return THIS
         */
        public DerivedTableBuilder<ParentBuilder> withColumnAlias(String... columnAliass){
            if(CheckUtil.isNullOrEmpty(columnAliass)){
                return this;
            }
            list(target::getColumnAliass, target::setColumnAliass)
                    .addAll(Arrays.stream(columnAliass).map(Alias::new));
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
         * @param aliasName alias name
         * @param columnAliass column alias
         * @return PARENT
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
            extends ParentHoldBuilder<JoinedTableBuilder<ParentBuilder>, ParentBuilder, From.JoinedTable> {

        public JoinedTableBuilder() {
            super(new From.JoinedTable());
        }

        public JoinedTableBuilder(From.JoinedTable target) {
            super(target);
        }


        /**
         * set
         * @param tableSource TableSource
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withTableSource(From.TableSource tableSource) {
            target.setTableSource(tableSource);
            return this;
        }

        /**
         * in
         * @return TableSourceBuilder
         */
        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::setTableSource);
        }

        /**
         * set
         * @param joinType JoinType
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinType joinType){
            target.setUseJoinOn(true);
            target.setJoinType(joinType);
            return this;
        }

        /**
         * set
         * @param joinType JoinTypeKeywords
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinTypeKeywords joinType){
            target.setUseJoinOn(true);
            target.setJoinType(new From.JoinType(joinType));
            return this;
        }

        /**
         * set
         * @param joinType JoinTypeKeywords
         * @param joinHint JoinHint
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinTypeKeywords joinType, JoinHint joinHint){
            target.setUseJoinOn(true);
            target.setJoinType(new From.JoinType(joinType,joinHint));
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withCrossJoin(){
            target.setUseCrossJoin(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withCrossApply() {
            target.setUseCrossApply(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withOuterApply() {
            target.setUseOuterApply(true);
            return this;
        }

        /**
         * set
         * @param tableSource TableSource
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withTableSource2(From.TableSource tableSource){
            this.target.setTableSource2(tableSource);
            return this;
        }

        /**
         * in
         * @return TableSourceBuilder
         */
        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource2(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target::setTableSource2);
        }

        /**
         * set
         * @param searchCondition SearchCondition
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
            this.target.setSearchCondition(searchCondition);
            return this;
        }

        /**
         * in
         * @return SearchConditionBuilder
         */
        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>>
                    (object(target::getSearchCondition, target::setSearchCondition)
                            .init(SearchCondition::new))
                    .in(this);
        }




        /*
        Quick into
         */

        /**
         * Quick in TableSourceBuilder
         * @return TableSourceBuilder
         */
        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> $(){
            if(target.getTableSource() == null) {
                return withTableSource();
            }
            return withTableSource2();
        }

        /**
         * Quick in BaseTableBuilder
         * And set tableName
         * @param tableName TableName
         * @return BaseTableBuilder
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
         * And set values
         * @param values TableValueConstructor
         * @return DerivedTableBuilder
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
         * Quick in DerivedTableBuilder
         * And set subQuery
         * @param subQuery QuerySpecification
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<JoinedTableBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery) {
            if(target.getTableSource() == null){
                return withTableSource()._Derived()
                        .withSubQuery($SubQuery(subQuery));
            }
            return withTableSource2()._Derived()
                    .withSubQuery($SubQuery(subQuery));
        }

        /**
         * Quick in VariableTableBuilder
         * And set variable
         * @param variable LocalVariable
         * @return VariableTableBuilder
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
         * @return SearchConditionBuilder
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
         * @param tableName TableName
         * @param tableAlias table alias
         * @return THIS
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
         * @param values TableValueConstructor
         * @param tableAlias table alias
         * @return THIS
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
         * @param subQuery QuerySpecification
         * @param tableAlias table alias
         * @return THIS
         */
        public JoinedTableBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias){
            if(target.getTableSource() == null){
                return withTableSource()._Derived()
                        .withSubQuery($SubQuery(subQuery))
                        .withAs()
                        .withTableAlias(tableAlias)
                        .and();
            }
            return withTableSource2()._Derived()
                    .withSubQuery($SubQuery(subQuery))
                    .withAs()
                    .withTableAlias(tableAlias)
                    .and();
        }

        /**
         * Quick set tableSource/tableSource2:VariableTable
         * into VariableTableBuilder and get-out
         * @param variable LocalVariable
         * @param tableAlias table alias
         * @return THIS
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
            extends ParentHoldBuilder<BaseWithTimeTableBuilder<ParentBuilder>, ParentBuilder, From.BaseWithTimeTable> {

        public BaseWithTimeTableBuilder() {
            super(new From.BaseWithTimeTable());
        }

        public BaseWithTimeTableBuilder(From.BaseWithTimeTable target) {
            super(target);
        }

        /**
         * set
         * @param tableName TableName
         * @return THIS
         */
        public BaseWithTimeTableBuilder<ParentBuilder> withTableName(TableName tableName){
            target.setTableName(tableName);
            return this;
        }

        /**
         * in
         * @return SystemTimeBuilder
         */
        public SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>> withSystemTime(){
            return new SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>>
                    (object(target::getSystemTime, target::setSystemTime)
                            .init(From.SystemTime::new))
                    .in(this);
        }

        /**
         * set
         * @param systemTime SystemTime
         * @return THIS
         */
        public BaseWithTimeTableBuilder<ParentBuilder> withSystemTime(From.SystemTime systemTime){
            this.target.setSystemTime(systemTime);
            return this;
        }

        /**
         * in
         * @return SystemTimeBuilder
         */
        public SystemTimeBuilder<BaseWithTimeTableBuilder<ParentBuilder>> $ForSystemTime() {
            return withSystemTime();
        }
    }

    /**
     * TableSampleBuilder
     * @param <ParentBuilder>
     */
    public static class TableSampleBuilder<ParentBuilder>
            extends ParentHoldBuilder<TableSampleBuilder<ParentBuilder>, ParentBuilder, From.TableSample> {

        public TableSampleBuilder() {
            super(new From.TableSample());
        }

        public TableSampleBuilder(From.TableSample target) {
            super(target);
        }

        /**
         * set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> withSystem(){
            target.setUseSystem(true);
            return this;
        }

        /**
         * set
         * @param sampleNumber sample number
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> withSampleNumber(Integer sampleNumber){
            target.setSampleNumber(c_unsigned_integer(sampleNumber));
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> withPercent(){
            target.setUsePercent(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> withRows(){
            target.setUseRows(true);
            return this;
        }

        /**
         * set
         * @param repeatSeed repeat seed
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> withRepeatSeed(Integer repeatSeed){
            target.setRepeatSeed(c_unsigned_integer(repeatSeed));
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> $System(){
            return withSystem();
        }

        /**
         * Quick set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> $(Integer sampleNumber){
            return withSampleNumber(sampleNumber);
        }

        /**
         * Quick set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> $Percent(Integer sampleNumber){
            return withSampleNumber(sampleNumber).withPercent();
        }

        /**
         * Quick set
         * @return THIS
         */
        public TableSampleBuilder<ParentBuilder> $Rows(Integer sampleNumber){
            return withSampleNumber(sampleNumber).withRows();
        }

        /**
         * Quick set
         * @return THIS
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
            extends ParentHoldBuilder<SystemTimeBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

        public SystemTimeBuilder() {
            super(new From.SystemTime());
        }

        public SystemTimeBuilder(From.SystemTime target) {
            super(target);
        }


        /**
         * back
         * @param dateTime date time
         * @return PARENT
         */
        public ParentBuilder _AsOf(StringConstant dateTime) {
            target.setDateTime(new From.DateTime(dateTime));
            return and();
        }

        /**
         * back
         * @param dateTime date time
         * @return PARENT
         */
        public ParentBuilder _AsOf(LocalVariable dateTime) {
            target.setDateTime(new From.DateTime(dateTime));
            return and();
        }

        /**
         * back
         * @param dateTime date time
         * @return PARENT
         */
        public ParentBuilder _AsOf(From.DateTime dateTime) {
            target.setDateTime(dateTime);
            return and();
        }

        /**
         * back in
         * @return FromToBuilder
         */
        public FromToBuilder<ParentBuilder> _From() {
            return new FromToBuilder<ParentBuilder>
                    (target)
                    .in(this.and());
        }

        /**
         * back in
         * @return BetweenAndBuilder
         */
        public BetweenAndBuilder<ParentBuilder> _Between() {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(this.and());
        }

        /**
         * back in
         * @return ContainedInBuilder
         */
        public ContainedInBuilder<ParentBuilder> _ContainedIn() {
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(this.and());
        }

        /**
         * back
         * @return PARENT
         */
        public ParentBuilder _All() {
            target.setUseAll(true);
            return and();
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @param dateTime date time
         * @return PARENT
         */
        public ParentBuilder $AsOf(String dateTime) {
            return _AsOf(c_string(dateTime));
        }

        /**
         * Quick set
         * @param dateTime date time
         * @return PARENT
         */
        public ParentBuilder $AsOf(StringConstant dateTime) {
            return _AsOf(dateTime);
        }

        /**
         * Quick set
         * @param dateTime date time
         * @return PARENT
         */
        public ParentBuilder $AsOf(LocalVariable dateTime) {
            return _AsOf(dateTime);
        }

        /**
         * Quick set
         * @param startDateTime date time
         * @param endDateTime date time
         * @return PARENT
         */
        public ParentBuilder $FromTo(String startDateTime, String endDateTime) {
            return _From()
                    .withFrom(c_string(startDateTime))
                    .withTo(c_string(endDateTime))
                    .and();
        }
        /**
         * Quick set
         * @param startDateTime date time
         * @param endDateTime date time
         * @return PARENT
         */
        public ParentBuilder $BetweenAnd(String startDateTime, String endDateTime) {
            return _Between()
                    .withBetween(c_string(startDateTime))
                    .withAnd(c_string(endDateTime))
                    .and();
        }

        /**
         * Quick set
         * @param startDateTime date time
         * @param endDateTime date time
         * @return PARENT
         */
        public ParentBuilder $ContainedIn(String startDateTime, String endDateTime) {
            return _ContainedIn()
                    .withStart(c_string(startDateTime))
                    .withEnd(c_string(endDateTime))
                    .and();
        }

        /**
         * Quick set
         * @return PARENT
         */
        public ParentBuilder $All() {
            return _All();
        }


        /**
         * Quick in
         * @param startDateTime date time
         * @return FromToBuilder
         */
        public FromToBuilder<ParentBuilder> $From(StringConstant startDateTime) {
            return new FromToBuilder<ParentBuilder>
                    (target)
                    .in(this.and())
                    .$From(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime date time
         * @return FromToBuilder
         */
        public FromToBuilder<ParentBuilder> $From(LocalVariable startDateTime) {
            return new FromToBuilder<ParentBuilder>
                    (target)
                    .in(this.and())
                    .$From(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime date time
         * @return BetweenAndBuilder
         */
        public BetweenAndBuilder<ParentBuilder> $Between(StringConstant startDateTime) {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(this.and())
                    .$Between(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime date time
         * @return BetweenAndBuilder
         */
        public BetweenAndBuilder<ParentBuilder> $Between(LocalVariable startDateTime) {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(this.and())
                    .$Between(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime date time
         * @return ContainedInBuilder
         */
        public ContainedInBuilder<ParentBuilder> $ContainedIn(StringConstant startDateTime) {
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(this.and())
                    .$In(startDateTime);
        }

        /**
         * Quick in
         * @param startDateTime date time
         * @return ContainedInBuilder
         */
        public ContainedInBuilder<ParentBuilder> $ContainedIn(LocalVariable startDateTime) {
            target.setUseBetween(true);
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(this.and())
                    .$In(startDateTime);
        }


        /**
         * FromToBuilder
         * @param <ParentBuilder>
         */
        public static class FromToBuilder<ParentBuilder>
                extends ParentHoldBuilder<FromToBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public FromToBuilder() {
                super(new From.SystemTime());
                this.target.setUseFrom(true);
            }
            
            public FromToBuilder(From.SystemTime target) {
                super(target);
                this.target.setUseFrom(true);
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> withFrom(StringConstant startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> withFrom(LocalVariable startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            /**
             * set
             * @param dateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> withFrom(From.DateTime dateTime) {
                target.setStartDateTime(dateTime);
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> withTo(StringConstant endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> withTo(LocalVariable endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> withTo(From.DateTime endDateTime) {
                target.setEndDateTime(endDateTime);
                return this;
            }




            /*
            Quick
             */

            /**
             * Quick set
             * @param startDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> $From(StringConstant startDateTime){
                return withFrom(startDateTime);
            }

            /**
             * Quick set
             * @param startDateTime date time
             * @return THIS
             */
            public FromToBuilder<ParentBuilder> $From(LocalVariable startDateTime){
                return withFrom(startDateTime);
            }

            /**
             * Quick back
             * @param endDateTime date time
             * @return PARENT
             */
            public ParentBuilder $To(LocalVariable endDateTime){
                return withTo(endDateTime)
                        .and();
            }

            /**
             * Quick back
             * @param endDateTime date time
             * @return PARENT
             */
            public ParentBuilder $To(StringConstant endDateTime){
                return withTo(endDateTime)
                        .and();
            }
        }

        /**
         * BetweenAndBuilder
         * @param <ParentBuilder>
         */
        public static class BetweenAndBuilder<ParentBuilder>
                extends ParentHoldBuilder<BetweenAndBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public BetweenAndBuilder() {
                super(new From.SystemTime());
                this.target.setUseBetween(true);
            }

            public BetweenAndBuilder(From.SystemTime target) {
                super(target);
                this.target.setUseBetween(true);
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> withBetween(StringConstant startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> withBetween(LocalVariable startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            /**
             * set
             * @param dateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> withBetween(From.DateTime dateTime) {
                target.setStartDateTime(dateTime);
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> withAnd(StringConstant endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> withAnd(LocalVariable endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> withAnd(From.DateTime endDateTime) {
                target.setEndDateTime(endDateTime);
                return this;
            }




            /*
            Quick
             */

            /**
             * Quick set
             * @param startDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> $Between(LocalVariable startDateTime){
                return withBetween(startDateTime);
            }

            /**
             * Quick set
             * @param startDateTime date time
             * @return THIS
             */
            public BetweenAndBuilder<ParentBuilder> $Between(StringConstant startDateTime){
                return withBetween(startDateTime);
            }

            /**
             * Quick back
             * @param endDateTime date time
             * @return PARENT
             */
            public ParentBuilder $And(LocalVariable endDateTime){
                return withAnd(endDateTime)
                        .and();
            }

            /**
             * Quick back
             * @param endDateTime date time
             * @return PARENT
             */
            public ParentBuilder $And(StringConstant endDateTime){
                return withAnd(endDateTime)
                        .and();
            }

        }

        /**
         * ContainedInBuilder
         * @param <ParentBuilder>
         */
        public static class ContainedInBuilder<ParentBuilder>
                extends ParentHoldBuilder<ContainedInBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public ContainedInBuilder() {
                super(new From.SystemTime());
                this.target.setUseContained(true);
            }
            
            public ContainedInBuilder(From.SystemTime target) {
                super(target);
                this.target.setUseContained(true);
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> withStart(StringConstant startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> withStart(From.DateTime startDateTime) {
                target.setStartDateTime(startDateTime);
                return this;
            }

            /**
             * set
             * @param startDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> withStart(LocalVariable startDateTime) {
                target.setStartDateTime(new From.DateTime(startDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> withEnd(StringConstant endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> withEnd(LocalVariable endDateTime) {
                target.setEndDateTime(new From.DateTime(endDateTime));
                return this;
            }

            /**
             * set
             * @param endDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> withEnd(From.DateTime endDateTime) {
                target.setEndDateTime(endDateTime);
                return this;
            }




            /*
            Quick
             */

            /**
             * Quick set
             * @param startDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> $In(LocalVariable startDateTime){
                return withStart(startDateTime);
            }

            /**
             * Quick set
             * @param startDateTime date time
             * @return THIS
             */
            public ContainedInBuilder<ParentBuilder> $In(StringConstant startDateTime){
                return withStart(startDateTime);
            }

            /**
             * Quick back
             * @param endDateTime date time
             * @return PARENT
             */
            public ParentBuilder $(LocalVariable endDateTime){
                return withEnd(endDateTime)
                        .and();
            }

            /**
             * Quick back
             * @param endDateTime date time
             * @return PARENT
             */
            public ParentBuilder $(StringConstant endDateTime){
                return withEnd(endDateTime)
                        .and();
            }

        }

    }

}
