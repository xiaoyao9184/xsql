package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.queries.With;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * WithBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WithBuilder<ParentBuilder>
        extends CodeTreeBuilder<WithBuilder<ParentBuilder>,ParentBuilder,With> {

    public WithBuilder() {
        super(new With());
    }

    public WithBuilder(With with) {
        super(with);
    }

    public CommonTableExpressionBuilder<WithBuilder<ParentBuilder>> withItem(){
        return new CommonTableExpressionBuilder<WithBuilder<ParentBuilder>>
                (initNew(With.CommonTableExpression::new,
                        this.target::getCommonTableExpressionList,
                        this.target::setCommonTableExpressionList))
                .in(this);
    }

    public WithBuilder<ParentBuilder> withItem(With.CommonTableExpression commonTableExpression){
        initAdd(commonTableExpression,
                this.target::getCommonTableExpressionList,
                this.target::setCommonTableExpressionList);
        return this;
    }



    public static WithBuilder<Void> WITH(){
        return new WithBuilder<>();
    }


    /*
    Quick set
     */

    /**
     * Quick set CommonTableExpression
     * into CommonTableExpressionBuilder get-out
     * @param expressionName
     * @param cteQueryDefinition
     * @param columnNames
     * @return
     */
    public WithBuilder<ParentBuilder> $(String expressionName, Select cteQueryDefinition, ColumnName... columnNames){
        return withItem()
                .withExpressionName(expressionName)
                .withCteQueryDefinition(cteQueryDefinition)
                .withColumnName(columnNames)
                .and();
    }

    public CommonTableExpressionBuilder<WithBuilder<ParentBuilder>> $(String expressionName, ColumnName... columnNames){
        return withItem()
                .withExpressionName(expressionName)
                .withColumnName(columnNames);
    }


    /**
     * CommonTableExpressionBuilder
     * @param <ParentBuilder>
     */
    public static class CommonTableExpressionBuilder<ParentBuilder>
        extends CodeTreeBuilder<CommonTableExpressionBuilder<ParentBuilder>,ParentBuilder,With.CommonTableExpression> {

        public CommonTableExpressionBuilder() {
            super(new With.CommonTableExpression());
        }

        public CommonTableExpressionBuilder(With.CommonTableExpression commonTableExpression) {
            super(commonTableExpression);
        }


        public CommonTableExpressionBuilder<ParentBuilder> withExpressionName(String expressionName){
            this.target.setExpressionName(expressionName);
            return this;
        }

        public CommonTableExpressionBuilder<ParentBuilder> withColumnName(ColumnName... columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(Arrays.asList(columnNames),
                     this.target::getColumnName,
                    this.target::setColumnName);
            return this;
        }

        public CommonTableExpressionBuilder<ParentBuilder> withColumnName(List<ColumnName> columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(columnNames,
                    this.target::getColumnName,
                    this.target::setColumnName);
            return this;
        }

        public CommonTableExpressionBuilder<ParentBuilder> withCteQueryDefinition(Select cteQueryDefinition){
            this.target.setCteQueryDefinition(cteQueryDefinition);
            return this;
        }


        /**
         * Quick set
         * @param columnNames
         * @return
         */
        public CommonTableExpressionBuilder<ParentBuilder> $(ColumnName... columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(Arrays.asList(columnNames),
                    this.target::getColumnName,
                    this.target::setColumnName);
            return this;
        }

        public CommonTableExpressionBuilder<ParentBuilder> $(String... columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(Arrays
                        .stream(columnNames)
                        .map(ColumnName::new)
                        .collect(Collectors.toList()),
                    this.target::getColumnName,
                    this.target::setColumnName);
            return this;
        }

        /**
         * Quick back
         * @param cteQueryDefinition
         * @return
         */
        public ParentBuilder $As(Select cteQueryDefinition){
            this.target.setCteQueryDefinition(cteQueryDefinition);
            return and();
        }
    }

}
