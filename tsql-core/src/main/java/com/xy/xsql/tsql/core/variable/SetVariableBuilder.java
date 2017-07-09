package com.xy.xsql.tsql.core.variable;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Compound;
import com.xy.xsql.tsql.model.variable.SetVariable;

import static com.xy.xsql.tsql.core.expression.Expressions.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class SetVariableBuilder<ParentBuilder>
        extends CodeTreeBuilder<SetVariableBuilder<ParentBuilder>,ParentBuilder,SetVariable> {


    public SetVariableBuilder(SetVariable tar) {
        super(tar);
    }

    public SetVariableBuilder() {
        super(new SetVariable());
    }

    public SetVariableBuilder<ParentBuilder> withLocalVariable(String variable){
        target.setLocalVariable(e_variable(variable));
        return this;
    }

    public SetVariableBuilder<ParentBuilder> withCompound(Compound compound){
        target.setCompound(compound);
        return this;
    }

    public SetVariableBuilder<ParentBuilder> withExpression(Expression expression){
        target.setExpression(expression);
        return this;
    }

    
    /*
    Quick
     */

    public static SetVariableBuilder<Void> SET_V(){
        return new SetVariableBuilder<>();
    }

    /**
     * Quick set
     * @param localVariable local_variable
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $(String localVariable) {
        return withLocalVariable(localVariable);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $Assign(Expression expression) {
        return withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $AddAssign(Expression expression) {
        return withCompound(Compound.ADD_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $SubtractAssign(Expression expression) {
        return withCompound(Compound.SUBTRACT_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $MultiplyAssign(Expression expression) {
        return withCompound(Compound.MULTIPLY_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $DivideAssign(Expression expression) {
        return withCompound(Compound.DIVIDE_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $ModuloAssign(Expression expression) {
        return withCompound(Compound.MODULO_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $BitwiseANDAssign(Expression expression) {
        return withCompound(Compound.BITWISE_AND_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $BitwiseXORAssign(Expression expression) {
        return withCompound(Compound.BITWISE_EXCLUSIVE_OR_EQUALS)
                .withExpression(expression);
    }

    /**
     * Quick set
     * @param expression expression
     * @return THIS
     */
    public SetVariableBuilder<ParentBuilder> $BitwiseORAssign(Expression expression) {
        return withCompound(Compound.BITWISE_OR_EQUALS)
                .withExpression(expression);
    }


}
