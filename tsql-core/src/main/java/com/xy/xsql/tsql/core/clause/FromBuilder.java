package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder;
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

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

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


//    public FromBuilder<ParentBuilder> $TableName(TableName tableName){
//
//    }


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
                    (tableSource)
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

        public BaseTableBuilder(From.BaseTable tar) {
            super(tar);
        }


        public BaseTableBuilder<ParentBuilder> withTableName(TableName tableName){
            tar.setTableName(tableName);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withAs (){
            tar.setUseAs(true);
            return this;
        }

        public BaseTableBuilder<ParentBuilder> withTableAlias (String aliasName){
            tar.setTableAlias(new Alias<>(aliasName));
            return this;
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

        public TableValueConstructorBuilder<DerivedTableBuilder<ParentBuilder>> withValue(){
            return new TableValueConstructorBuilder<DerivedTableBuilder<ParentBuilder>>
                    (set(TableValueConstructor::new,
                            tar::setValues))
                    .in(this);
        }

        public DerivedTableBuilder<ParentBuilder> withAs(){
            tar.setUseAs(true);
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withTableAlias(String aliasName){
            tar.setTableAlias(new Alias<>(aliasName));
            return this;
        }

        public DerivedTableBuilder<ParentBuilder> withTableAlias(String... columnAliass){
            List<Alias<Void>> list = Arrays.stream(columnAliass)
                    .map(Alias<Void>::new)
                    .collect(Collectors.toList());
            initAdd(list,
                    tar::getColumnAliass,
                    tar::setColumnAliass);
            return this;
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

    }
}
