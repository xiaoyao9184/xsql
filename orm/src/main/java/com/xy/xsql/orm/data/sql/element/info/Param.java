package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.OperatorEnum;

/**
 * 参数
 * 符合元素
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Param
        implements Element, Cloneable{

    protected boolean and;
    protected Column column;
    protected OperatorEnum relationshipOperator;
    protected Expression valueExpression;

    public Param(){

    }

    public Param(boolean and, Column column, OperatorEnum relationshipOperator, Expression valueExpression){
        this.and = and;
        this.column = column;
        this.relationshipOperator = relationshipOperator;
        this.valueExpression = valueExpression;
    }

    public boolean isAnd() {
        return and;
    }

    public void setAnd(boolean and) {
        this.and = and;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public OperatorEnum getRelationshipOperator() {
        return relationshipOperator;
    }

    public void setRelationshipOperator(OperatorEnum relationshipOperator) {
        this.relationshipOperator = relationshipOperator;
    }

    public Expression getValueExpression() {
        return valueExpression;
    }

    public void setValueExpression(Expression valueExpression) {
        this.valueExpression = valueExpression;
    }


    /**
     * set And flag
     * @param and And flag
     * @return This
     */
    public Param withAnd(boolean and) {
        this.and = and;
        return this;
    }

    /**
     * set Column
     * @param column Column
     * @return This
     */
    public Param withColumn(Column column) {
        this.column = column;
        return this;
    }

    /**
     * set OperatorEnum relationshipOperator
     * @param relationship OperatorEnum
     * @return This
     */
    public Param withRelationship(OperatorEnum relationship) {
        this.relationshipOperator = relationship;
        return this;
    }

    /**
     * set Expression valueExpression
     * @param value Expression
     * @return This
     */
    public Param withValue(Expression value) {
        this.valueExpression = value;
        return this;
    }


    /**
     * 克隆
     * @return Param
     */
    @SuppressWarnings({"RedundantStringConstructorCall", "CloneDoesntCallSuperClone"})
    @Override
    public Param clone() {
        return new Param(this.and, this.column.clone(), this.relationshipOperator, this.valueExpression);
    }
}
