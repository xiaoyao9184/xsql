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
@SuppressWarnings({"WeakerAccess", "unused"})
public class WithBuilder<ParentBuilder>
        extends CodeTreeBuilder<WithBuilder<ParentBuilder>,ParentBuilder,With> {

    public WithBuilder() {
        super(new With());
    }

    public WithBuilder(With with) {
        super(with);
    }

    /**
     * set
     * @param commonTableExpression CommonTableExpression
     * @return THIS
     */
    public WithBuilder<ParentBuilder> withItem(With.CommonTableExpression commonTableExpression){
        initAdd(commonTableExpression,
                this.target::getCommonTableExpressionList,
                this.target::setCommonTableExpressionList);
        return this;
    }

    /**
     * in
     * @return CommonTableExpressionBuilder
     */
    public CommonTableExpressionBuilder<WithBuilder<ParentBuilder>> withItem(){
        return new CommonTableExpressionBuilder<WithBuilder<ParentBuilder>>
                (initNew(With.CommonTableExpression::new,
                        this.target::getCommonTableExpressionList,
                        this.target::setCommonTableExpressionList))
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set CommonTableExpression
     * into CommonTableExpressionBuilder get-out
     * @param expressionName Expression Name
     * @param cteQueryDefinition Select
     * @param columnNames ColumnName
     * @return THIS
     */
    public WithBuilder<ParentBuilder> $(String expressionName, Select cteQueryDefinition, ColumnName... columnNames){
        return withItem()
                .withExpressionName(expressionName)
                .withCteQueryDefinition(cteQueryDefinition)
                .withColumnName(columnNames)
                .and();
    }

    /**
     * Quick set CommonTableExpression
     * into CommonTableExpressionBuilder get-out
     * @param expressionName Expression Name
     * @param columnNames ColumnName
     * @return THIS
     */
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

        /**
         * set
         * @param expressionName Expression Name
         * @return THIS
         */
        public CommonTableExpressionBuilder<ParentBuilder> withExpressionName(String expressionName){
            this.target.setExpressionName(expressionName);
            return this;
        }

        /**
         * set
         * @param columnNames ColumnName
         * @return THIS
         */
        public CommonTableExpressionBuilder<ParentBuilder> withColumnName(ColumnName... columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(Arrays.asList(columnNames),
                     this.target::getColumnName,
                    this.target::setColumnName);
            return this;
        }

        /**
         * set
         * @param columnNames ColumnName
         * @return THIS
         */
        public CommonTableExpressionBuilder<ParentBuilder> withColumnName(List<ColumnName> columnNames){
            if(CheckUtil.isNullOrEmpty(columnNames)){
                return this;
            }
            initAdd(columnNames,
                    this.target::getColumnName,
                    this.target::setColumnName);
            return this;
        }

        /**
         * set
         * @param cteQueryDefinition Select
         * @return THIS
         */
        public CommonTableExpressionBuilder<ParentBuilder> withCteQueryDefinition(Select cteQueryDefinition){
            this.target.setCteQueryDefinition(cteQueryDefinition);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @param columnNames ColumnName
         * @return THIS
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

        /**
         * Quick set
         * @param columnNames ColumnName
         * @return THIS
         */
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
         * @param cteQueryDefinition Select
         * @return PARENT
         */
        public ParentBuilder $As(Select cteQueryDefinition){
            this.target.setCteQueryDefinition(cteQueryDefinition);
            return and();
        }
    }

}
