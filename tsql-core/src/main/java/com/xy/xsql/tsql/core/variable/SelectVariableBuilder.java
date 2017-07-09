package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.variable.SelectVariable;

import static com.xy.xsql.core.ListBuilder.initNew;
import static com.xy.xsql.tsql.core.expression.Expressions.e_variable;

/**
 * SelectVariableBuilder
 * Created by xiaoyao9184 on 2017/3/16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class SelectVariableBuilder<ParentBuilder>
        extends CodeTreeBuilder<SelectVariableBuilder<ParentBuilder>,ParentBuilder,SelectVariable> {

    public SelectVariableBuilder(SelectVariable tar) {
        super(tar);
    }

    public SelectVariableBuilder() {
        super(new SelectVariable());
    }

    /**
     * in
     * @return SelectVariableItemBuilder
     */
    public SelectVariableItemBuilder<SelectVariableBuilder<ParentBuilder>> withItem(){
        return new SelectVariableItemBuilder<SelectVariableBuilder<ParentBuilder>>
                (initNew(SelectVariable.Item::new,
                        target::getItems,
                        target::setItems))
                .in(this);
    }


    /*
    Quick
     */

    public static SelectVariableBuilder<Void> SELECT_V(){
        return new SelectVariableBuilder<>();
    }

    /**
     * Quick in
     * @param localVariable local_variable
     * @return SelectVariableItemBuilder
     */
    public SelectVariableItemBuilder<SelectVariableBuilder<ParentBuilder>> $(String localVariable) {
        return withItem()
                .withLocalVariable(localVariable);
    }


    /**
     * SelectVariableItemBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"WeakerAccess", "TypeParameterHidesVisibleType", "unused"})
    public class SelectVariableItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<SelectVariableItemBuilder<ParentBuilder>,ParentBuilder,SelectVariable.Item> {

        public SelectVariableItemBuilder(SelectVariable.Item tar) {
            super(tar);
        }

        public SelectVariableItemBuilder<ParentBuilder> withLocalVariable(String variable){
            target.setLocalVariable(e_variable(variable));
            return this;
        }

        public SelectVariableItemBuilder<ParentBuilder> withCompound(Compound compound){
            target.setCompound(compound);
            return this;
        }

        public SelectVariableItemBuilder<ParentBuilder> withExpression(Expression expression){
            target.setExpression(expression);
            return this;
        }


        /*
        Quick
         */

        /**
         * Quick set
         * @param localVariable local_variable
         * @return THIS
         */
        public SelectVariableItemBuilder<ParentBuilder> $(String localVariable){
            return withLocalVariable(localVariable);
        }

        /**
         * Quick set
         * @param compound Compound
         * @return THIS
         */
        public SelectVariableItemBuilder<ParentBuilder> $(Compound compound){
            return withCompound(compound);
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $Assign(Expression expression) {
            return withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $AddAssign(Expression expression) {
            return withCompound(Compound.ADD_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $SubtractAssign(Expression expression) {
            return withCompound(Compound.SUBTRACT_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $MultiplyAssign(Expression expression) {
            return withCompound(Compound.MULTIPLY_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $DivideAssign(Expression expression) {
            return withCompound(Compound.DIVIDE_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $ModuloAssign(Expression expression) {
            return withCompound(Compound.MODULO_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $BitwiseANDAssign(Expression expression) {
            return withCompound(Compound.BITWISE_AND_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $BitwiseXORAssign(Expression expression) {
            return withCompound(Compound.BITWISE_EXCLUSIVE_OR_EQUALS)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick end
         * @param expression expression
         * @return PARENT
         */
        public ParentBuilder $BitwiseORAssign(Expression expression) {
            return withCompound(Compound.BITWISE_OR_EQUALS)
                    .withExpression(expression)
                    .and();
        }
    }

}
