package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.UpdateBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.queries.hints.TableHintLimited;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_UPDATE extends UpdateBuilder {

    public static class b_SET extends SetListBuilder<b_SET> {

        public b_SET(){
            this.in(this);
        }

        /*
        Item
         */

        public b_SET $$(ColumnName column_name, Expression expression){
            return withItem()._ColumnAssignment()
                            .withColumnName(column_name)
                            .withExpression(expression)
                            .and()
                            .and();
        }

        public b_SET $$(ColumnName column_name, k_DEFAULT default_){
            return withItem()._ColumnAssignment()
                            .withColumnName(column_name)
                            .withUseDefault(true)
                            .and()
                            .and();
        }

        public b_SET $$(ColumnName column_name, k_NULL null_){
            return withItem()._ColumnAssignment()
                            .withColumnName(column_name)
                            .withUseNull(true)
                            .and()
                            .and();
        }

        public b_SET $$(LocalVariable variable, Expression expression){
            return withItem()._VariableAssignment()
                            .withVariable(variable)
                            .withExpression(expression)
                            .and()
                            .and();
        }

        public b_SET $$(LocalVariable variable, ColumnName column_name, Expression expression){
            return withItem()._VariableColumnAssignment()
                            .withVariable(variable)
                            .withColumnName(column_name)
                            .withExpression(expression)
                            .and()
                            .and();
        }

        public b_SET $$(ColumnName column_name, Compound compound, Expression expression){
            return withItem()._ColumnCompound()
                            .withColumnName(column_name)
                            .withCompound(compound)
                            .withExpression(expression)
                            .and()
                            .and();
        }

        public b_SET $$(LocalVariable variable, Compound compound, Expression expression){
            return withItem()._VariableCompound()
                            .withVariable(variable)
                            .withCompound(compound)
                            .withExpression(expression)
                            .and()
                            .and();
        }

        public b_SET $$(LocalVariable variable, ColumnName column_name, Compound compound, Expression expression){
            return withItem()._VariableColumnCompound()
                            .withVariable(variable)
                            .withColumnName(column_name)
                            .withCompound(compound)
                            .withExpression(expression)
                            .and()
                            .and();
        }
    }

    public static class k_DEFAULT {}

    public static class k_NULL {}

}
