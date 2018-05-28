package com.xy.xsql.tsql.style.constructor.builder.elements.variables;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.elements.variables.SetVariableBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Compound;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_WITH;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_CONTAINS;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_FREETEXT;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_INSERT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_SET extends SetVariableBuilder<b_SET> {

    public b_SET() {
        this.in(this);
    }

    public b_SET $assign(Expression expression) {
        return withExpression(expression)
                .and();
    }

    public b_SET $add_assign(Expression expression) {
        return withCompound(Compound.ADD_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $subtract_assign(Expression expression) {
        return withCompound(Compound.SUBTRACT_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $multiply_assign(Expression expression) {
        return withCompound(Compound.MULTIPLY_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $divide_assign(Expression expression) {
        return withCompound(Compound.DIVIDE_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $modulo_assign(Expression expression) {
        return withCompound(Compound.MODULO_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $bitwise_and_assign(Expression expression) {
        return withCompound(Compound.BITWISE_AND_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $bitwise_xor_assign(Expression expression) {
        return withCompound(Compound.BITWISE_EXCLUSIVE_OR_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }

    public b_SET $bitwise_or_assign(Expression expression) {
        return withCompound(Compound.BITWISE_OR_ASSIGNMENT)
                .withExpression(expression)
                .and();
    }


    public static class k_CURSOR {}

    public static class k_FORWARD_ONLY {}

    public static class k_SCROLL {}

    public static class k_STATIC {}

    public static class k_KEYSET {}

    public static class k_DYNAMIC {}

    public static class k_FAST_FORWARD {}

    public static class k_READ_ONLY {}

    public static class k_SCROLL_LOCKS {}

    public static class k_OPTIMISTIC {}

    public static class k_TYPE_WARNING {}

    public static class b_FOR_select_statement extends SelectBuilder {}

    public static class k_FOR {}

    public static class k_UPDATE {}


    /**
     * same as
     * @see b_SET.b_OF
     * @see b_WITH.b$column_name_list
     * @see b_FREETEXT.b$column_list
     * @see b_CONTAINS.b$column_list
     * @see b_INSERT.b$column_list
     */
    public static class b_OF extends CodeBuilder<List<ColumnName>> {

        public b_OF() {
            super(new ArrayList<>());
        }

        /*
        Item
         */

        public b_OF $$(ColumnName... columnNames){
            this.target.addAll(Arrays.asList(columnNames));
            return this;
        }

    }
}
