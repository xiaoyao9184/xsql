package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
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
     *
     * @return
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> withItem(){
        initList(
                tar::getTableSourceList,
                tar::setTableSourceList);
        return new TableSourceBuilder<FromBuilder<ParentBuilder>>
                (tar.getTableSourceList()::add)
                .in(this);
    }


    /**
     * Quick in
     * @return
     */
    public TableSourceBuilder<FromBuilder<ParentBuilder>> $(){
        return withItem();
    }


    /**
     * Quick inout set BaseTableBuilder
     * @param tableName
     * @return
     */
    public FromBuilder<ParentBuilder> $(TableName tableName){
        return withItem()._Base()
                .withTableName(tableName)
                .and();
    }

    /**
     * Quick inout set DerivedTableBuilder subQuery
     * @param subQuery
     * @return
     */
    public FromBuilder<ParentBuilder> $(Select.QuerySpecification subQuery){
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .and();
    }

    /**
     * Quick inout set DerivedTableBuilder values
     * @param values
     * @return
     */
    public FromBuilder<ParentBuilder> $(TableValueConstructor values){
        return withItem()._Derived()
                .withValues(values)
                .and();
    }

    /**
     * Quick inout set VariableTableBuilder
     * @param variable
     * @return
     */
    public FromBuilder<ParentBuilder> $(LocalVariable variable){
        return withItem()._Variable()
                .withVariable(variable)
                .and();
    }






    /**
     * Quick in set BaseTableBuilder
     * @param tableName
     * @return
     */
    public BaseTableBuilder<FromBuilder<ParentBuilder>> $(TableName tableName, String tableAlias){
        return withItem()._Base()
                .withTableName(tableName)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in set DerivedTableBuilder
     * @param values
     * @return
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(TableValueConstructor values, String tableAlias){
        return withItem()._Derived()
                .withValues(values)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in set DerivedTableBuilder
     * @param subQuery
     * @return
     */
    public DerivedTableBuilder<FromBuilder<ParentBuilder>> $(Select.QuerySpecification subQuery, String tableAlias){
        return withItem()._Derived()
                .withSubQuery(subQuery)
                .withAs()
                .withTableAlias(tableAlias);
    }

    /**
     * Quick in set VariableTableBuilder
     * @param variable
     * @return
     */
    public VariableTableBuilder<FromBuilder<ParentBuilder>> $(LocalVariable variable, String tableAlias){
        return withItem()._Variable()
                .withVariable(variable)
                .withAs()
                .withTableAlias(tableAlias);
    }




    /**
     * Abstract TableSource Builder
     * @param <ParentBuilder>
     */
    public static class TableSourceBuilder<ParentBuilder>
            extends CodeTreeBuilder<TableSourceBuilder<ParentBuilder>,ParentBuilder,Setter<From.TableSource>> {

        public TableSourceBuilder(Setter<From.TableSource> setter) {
            super(setter);
        }

        public BaseTableBuilder<ParentBuilder> _Base(){
            From.BaseTable tableSource = new From.BaseTable();
            tar.set(tableSource);
            return new BaseTableBuilder<ParentBuilder>
                    (tableSource,tar)
                    .in(out());
        }

        public DerivedTableBuilder<ParentBuilder> _Derived(){
            From.DerivedTable tableSource = new From.DerivedTable();
            tar.set(tableSource);
            return new DerivedTableBuilder<ParentBuilder>
                    (tableSource)
                    .in(out());
        }

        public JoinedTableBuilder<ParentBuilder> _Joined(){
            From.JoinedTable tableSource = new From.JoinedTable();
            tar.set(tableSource);
            return new JoinedTableBuilder<ParentBuilder>
                    (tableSource)
                    .in(out());
        }

        public VariableTableBuilder<ParentBuilder> _Variable(){
            From.VariableTable tableSource = new From.VariableTable();
            tar.set(tableSource);
            return new VariableTableBuilder<ParentBuilder>
                    (tableSource)
                    .in(out());
        }

    }


    /**
     *
     * @param <ParentBuilder>
     */
    public static class BaseTableBuilder<ParentBuilder>
            extends CodeTreeBuilder<BaseTableBuilder<ParentBuilder>,ParentBuilder,From.BaseTable> {

        private Setter<From.TableSource> setter;

        public BaseTableBuilder(From.BaseTable tar) {
            super(tar);
        }

        public BaseTableBuilder(From.BaseTable tar, Setter<From.TableSource> setter) {
            super(tar);
            this.setter = setter;
        }

        @Override
        public ParentBuilder and(){
            //LAZY set
            setter.set(tar);
            return out();
        }


        public BaseTableBuilder<ParentBuilder> withTableName(TableName tableName){
            tar.setTableName(tableName);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withAs(){
            tar.setUseAs(true);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            tar.setTableAlias(new Alias<>(aliasName));
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

        /**
         * Quick transform to JoinedTableBuilder
         * @return
         */
        public JoinedTableBuilder<ParentBuilder> $Cross_Join(){
            From.JoinedTable tar = new From.JoinedTable();
            return new JoinedTableBuilder<ParentBuilder>(tar)
                    .withTableSource(this.tar)
                    .withCrossJoin()
                    .in(this.out());
        }

    }

    /**
     *
     * @param <ParentBuilder>
     */
    public static class DerivedTableBuilder<ParentBuilder>
            extends CodeTreeBuilder<DerivedTableBuilder<ParentBuilder>,ParentBuilder,From.DerivedTable> {

        public DerivedTableBuilder(From.DerivedTable tar) {
            super(tar);
        }


        public DerivedTableBuilder<ParentBuilder> withSubQuery(Select.QuerySpecification subQuery){
            tar.setSubQuery(subQuery);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withValues(TableValueConstructor values){
            tar.setValues(values);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withAs(){
            tar.setUseAs(true);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            tar.setTableAlias(new Alias<>(aliasName));
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withColumnAlias(String... columnAliass){
            List<Alias<Void>> list = Arrays.stream(columnAliass)
                    .map(Alias<Void>::new)
                    .collect(Collectors.toList());
            initAdd(list,
                    tar::getColumnAliass,
                    tar::setColumnAliass);
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
     * 
     * @param <ParentBuilder>
     */
    public static class JoinedTableBuilder<ParentBuilder>
            extends CodeTreeBuilder<JoinedTableBuilder<ParentBuilder>,ParentBuilder,From.JoinedTable> {

        public JoinedTableBuilder(From.JoinedTable joinedTable) {
            super(joinedTable);
        }


        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (tar::setTableSource)
                    .in(this);
        }

        public JoinedTableBuilder<ParentBuilder> withTableSource(From.TableSource tableSource) {
            tar.setTableSource(tableSource);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withJoinType(From.JoinType joinType){
            tar.setJoinType(joinType);
            return this;
        }

        public JoinedTableBuilder<ParentBuilder> withCrossJoin(){
            tar.setUseCrossJoin(true);
            return this;
        }

        public TableSourceBuilder<JoinedTableBuilder<ParentBuilder>> withTableSource2(){
            return new TableSourceBuilder<JoinedTableBuilder<ParentBuilder>>
                    (tar::setTableSource2)
                    .in(this);
        }

        public SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<JoinedTableBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            tar::setSearchCondition))
                    .in(this);
        }
    }

    /**
     *
     * @param <ParentBuilder>
     */
    public static class VariableTableBuilder<ParentBuilder>
            extends CodeTreeBuilder<VariableTableBuilder<ParentBuilder>,ParentBuilder,From.VariableTable> {

        public VariableTableBuilder(From.VariableTable tar) {
            super(tar);
        }


        public VariableTableBuilder<ParentBuilder> withVariable(LocalVariable variable){
            tar.setVariable(variable);
            return this;
        }

        public VariableTableBuilder<ParentBuilder> withAs(){
            tar.setUseAs(true);
            return this;
        }

        public VariableTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            tar.setTableAlias(new Alias<>(aliasName));
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
}
