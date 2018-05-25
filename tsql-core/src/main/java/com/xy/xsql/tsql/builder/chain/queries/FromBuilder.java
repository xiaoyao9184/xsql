package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.CodeTreeLazyConfigBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.builder.chain.statements.MergeBuilder;
import com.xy.xsql.tsql.model.queries.From;
import com.xy.xsql.tsql.model.queries.SearchCondition;
import com.xy.xsql.tsql.model.queries.TableValueConstructor;
import com.xy.xsql.tsql.model.queries.hints.JoinHint;
import com.xy.xsql.tsql.model.queries.hints.TableHint;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.Alias;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.FiledBuilder.initSet2;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_unsigned_integer;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;

/**
 * FromBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"unused","WeakerAccess","UnusedReturnValue"})
public class FromBuilder<ParentBuilder>
        extends CodeTreeBuilder<FromBuilder<ParentBuilder>,ParentBuilder,From> {

    public FromBuilder() {
        super(new From());
    }

    public FromBuilder(From from) {
        super(from);
    }

    /**
     * set
     * @param tableSources TableSource
     * @return THIS
     */
    public FromBuilder<ParentBuilder> withItem(From.TableSource... tableSources) {
        initAdd(Arrays.asList(tableSources),
                target::getTableSourceList,
                target::setTableSourceList);
        return this;
    }

    /**
     * in
     * @return TableSourceBuilder
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
     * in
     * @return TableSourceBuilder
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> withItemAuto() {
        initList(
                target::getTableSourceList,
                target::setTableSourceList);
        return new TableSourceBuilder<FromBuilder<ParentBuilder>>
                (target.getTableSourceList()::add,true)
                .in(this);
    }




    /*
    Quick into
     */

    /**
     * Quick in TableSourceBuilder
     * @return TransformJoinedBuilder
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
     * Quick in BaseTableBuilder
     * And set tableName,tableAlias
     * @param tableName TableName
     * @param tableAlias table alias
     * @return BaseTableBuilder
     */
    public BaseTableBuilder<FromBuilder<ParentBuilder>> $(TableName tableName, String tableAlias) {
        return withItem()._Base()
                .withTableName(tableName)
//                .withAs()
                .withTableAlias(tableAlias);
    }

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
            extends CodeTreeBuilder<TableSourceBuilder<ParentBuilder>, ParentBuilder, Setter<From.TableSource>> {

        /**
         * Submit immediately after confirm the TableSource
         */
        private boolean autoSetter;

        public TableSourceBuilder(Setter<From.TableSource> setter) {
            super(setter);
        }

        public TableSourceBuilder(Setter<From.TableSource> setter, boolean autoSetter) {
            super(setter);
            this.autoSetter = autoSetter;
        }

        /**
         * Confirm type of TableSource
         * @return BaseTableBuilder
         */
        public BaseTableBuilder<ParentBuilder> _Base() {
            if(autoSetter){
                return new BaseTableBuilder<ParentBuilder>
                        (initSet2(From.BaseTable::new,
                                Getter.empty(),
                                target))
                        .in(out());
            }
            return new BaseTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return DerivedTableBuilder
         */
        public DerivedTableBuilder<ParentBuilder> _Derived() {
            if(autoSetter){
                return new DerivedTableBuilder<ParentBuilder>
                        (initSet2(From.DerivedTable::new,
                                Getter.empty(),
                                target))
                        .in(out());
            }
            return new DerivedTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return JoinedTableBuilder
         */
        public JoinedTableBuilder<ParentBuilder> _Joined() {
            if(autoSetter){
                return new JoinedTableBuilder<ParentBuilder>
                        (initSet2(From.JoinedTable::new,
                                Getter.empty(),
                                target))
                        .in(out());
            }
            return new JoinedTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return VariableTableBuilder
         */
        public VariableTableBuilder<ParentBuilder> _Variable() {
            if(autoSetter){
                return new VariableTableBuilder<ParentBuilder>
                        (initSet2(From.VariableTable::new,
                                Getter.empty(),
                                target))
                        .in(out());
            }
            return new VariableTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
        }

        /**
         * Confirm type of TableSource
         * @return BaseWithTimeTableBuilder
         */
        public BaseWithTimeTableBuilder<ParentBuilder> _BaseTime() {
            if(autoSetter){
                return new BaseWithTimeTableBuilder<ParentBuilder>
                        (initSet2(From.BaseWithTimeTable::new,
                                Getter.empty(),
                                target))
                        .in(out());
            }
            return new BaseWithTimeTableBuilder<ParentBuilder>
                    (target)
                    .in(out());
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
         * Quick in BaseTableBuilder
         * And set tableName
         * @param tableName TableName
         * @return BaseTableBuilder
         */
        public BaseTableBuilder<TransformJoinedBuilder<ParentBuilder>> $(TableName tableName) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
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
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
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
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
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
         * @param tableName TableName
         * @param tableAlias table alias
         * @return THIS
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
         * @param values TableValueConstructor
         * @param tableAlias table alias
         * @return THIS
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
         * @param subQuery QuerySpecification
         * @param tableAlias table alias
         * @return THIS
         */
        public TransformJoinedBuilder<ParentBuilder> $(Select.QuerySpecification subQuery, String tableAlias) {
            return new TableSourceBuilder<TransformJoinedBuilder<ParentBuilder>>
                    (tableSourceSetter)
                    .in(this)
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
         * @return THIS
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
         * @return THIS
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
         * @return THIS
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

        public BaseTableBuilder() {
            super(new From.BaseTable());
        }

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
                    (initSet(From.TableSample::new,
                            target::getTableSample,
                            target::setTableSample))
                    .in(this);
        }

        /**
         * set
         * @param tableHints TableHint
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableHint(TableHint... tableHints){
            initAdd(Arrays.asList(tableHints),
                    target::getTableHintList,
                    target::setTableHintList);
            return this;
        }

        /**
         * set
         * @param tableHints TableHint
         * @return THIS
         */
        public BaseTableBuilder<ParentBuilder> withTableHint(List<TableHint> tableHints){
            initAdd(tableHints,
                    target::getTableHintList,
                    target::setTableHintList);
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
            extends CodeTreeLazyConfigBuilder<JoinedTableBuilder<ParentBuilder>, ParentBuilder, From.JoinedTable, From.TableSource> {

        public JoinedTableBuilder() {
            super(new From.JoinedTable());
        }

        public JoinedTableBuilder(From.JoinedTable target) {
            super(target);
        }

        public JoinedTableBuilder(From.JoinedTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public JoinedTableBuilder(Setter<From.TableSource> setter) {
            super(new From.JoinedTable(),setter);
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
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (target::setTableSource)
                    .in(this);
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
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (target::setTableSource2)
                    .in(this);
        }

        //TODO remove
        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource2Auto(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (target::setTableSource2,true)
                    .in(this);
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
                    (set(SearchCondition::new,
                            target::setSearchCondition))
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
            extends CodeTreeLazyConfigBuilder<BaseWithTimeTableBuilder<ParentBuilder>, ParentBuilder, From.BaseWithTimeTable, From.TableSource> {

        public BaseWithTimeTableBuilder() {
            super(new From.BaseWithTimeTable());
        }

        public BaseWithTimeTableBuilder(From.BaseWithTimeTable target) {
            super(target);
        }

        public BaseWithTimeTableBuilder(From.BaseWithTimeTable target, Setter<From.TableSource> setter) {
            super(target,setter);
        }

        public BaseWithTimeTableBuilder(Setter<From.TableSource> setter) {
            super(new From.BaseWithTimeTable(),setter);
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
                    (initSet(From.SystemTime::new,
                            target::getSystemTime,
                            target::setSystemTime))
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
            extends CodeTreeBuilder<TableSampleBuilder<ParentBuilder>, ParentBuilder, From.TableSample> {

        public TableSampleBuilder() {
            super(new From.TableSample());
        }

        public TableSampleBuilder(From.TableSample tableSample) {
            super(tableSample);
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
            extends CodeTreeBuilder<SystemTimeBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

        public SystemTimeBuilder() {
            super(new From.SystemTime());
        }

        public SystemTimeBuilder(From.SystemTime systemTime) {
            super(systemTime);
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
                    .in(and());
        }

        /**
         * back in
         * @return BetweenAndBuilder
         */
        public BetweenAndBuilder<ParentBuilder> _Between() {
            return new BetweenAndBuilder<ParentBuilder>
                    (target)
                    .in(and());
        }

        /**
         * back in
         * @return ContainedInBuilder
         */
        public ContainedInBuilder<ParentBuilder> _ContainedIn() {
            return new ContainedInBuilder<ParentBuilder>
                    (target)
                    .in(and());
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
                    .in(and())
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
                    .in(and())
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
                    .in(and())
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
                    .in(and())
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
                    .in(and())
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
                    .in(and())
                    .$In(startDateTime);
        }


        /**
         * FromToBuilder
         * @param <ParentBuilder>
         */
        public static class FromToBuilder<ParentBuilder>
                extends CodeTreeBuilder<FromToBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public FromToBuilder(From.SystemTime systemTime) {
                super(systemTime);
                target.setUseFrom(true);
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
                extends CodeTreeBuilder<BetweenAndBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public BetweenAndBuilder(From.SystemTime systemTime) {
                super(systemTime);
                target.setUseBetween(true);
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
                extends CodeTreeBuilder<ContainedInBuilder<ParentBuilder>, ParentBuilder, From.SystemTime> {

            public ContainedInBuilder(From.SystemTime systemTime) {
                super(systemTime);
                target.setUseContained(true);
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
