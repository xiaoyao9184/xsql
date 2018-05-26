package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.With;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.handler.list.ListHandler.list;

/**
 * WithBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class WithBuilder<ParentBuilder>
        extends ParentHoldBuilder<WithBuilder<ParentBuilder>,ParentBuilder,With> {

    public WithBuilder() {
        super(new With());
    }

    public WithBuilder(With target) {
        super(target);
    }

    /**
     * set
     * @param commonTableExpression CommonTableExpression
     * @return THIS
     */
    public WithBuilder<ParentBuilder> withItem(With.CommonTableExpression commonTableExpression){
        list(target::getCommonTableExpressionList, target::setCommonTableExpressionList)
                .add(commonTableExpression);
        return this;
    }

    /**
     * in
     * @return CommonTableExpressionBuilder
     */
    public CommonTableExpressionBuilder<WithBuilder<ParentBuilder>> withItem(){
        return new CommonTableExpressionBuilder<WithBuilder<ParentBuilder>>
                (list(target::getCommonTableExpressionList, target::setCommonTableExpressionList)
                        .addNew(With.CommonTableExpression::new))
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
        extends ParentHoldBuilder<CommonTableExpressionBuilder<ParentBuilder>,ParentBuilder,With.CommonTableExpression> {

        public CommonTableExpressionBuilder() {
            super(new With.CommonTableExpression());
        }

        public CommonTableExpressionBuilder(With.CommonTableExpression target) {
            super(target);
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
            list(target::getColumnName, target::setColumnName)
                    .addAll(columnNames);
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
            list(target::getColumnName, target::setColumnName)
                    .addAll(columnNames);
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
            list(target::getColumnName, target::setColumnName)
                    .addAll(columnNames);
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
            list(target::getColumnName, target::setColumnName)
                    .addAll(Arrays.stream(columnNames).map(ColumnName::new));
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
