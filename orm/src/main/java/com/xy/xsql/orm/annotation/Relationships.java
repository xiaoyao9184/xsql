package com.xy.xsql.orm.annotation;

import com.xy.xsql.orm.data.sql.element.OperatorEnum;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public enum Relationships {
    EQUAL(OperatorEnum.EQUAL,1),
    NOT_EQUAL(OperatorEnum.NOT_EQUAL,1),
    GREATER(OperatorEnum.GREATER,1),
    GREATER_EQUAL(OperatorEnum.GREATER_EQUAL,1),
    LESS(OperatorEnum.LESS,1),
    LESS_EQUAL(OperatorEnum.LESS_EQUAL,1),

    BETWEEN(OperatorEnum.BETWEEN,2),

    IN(OperatorEnum.IN,-1),

    START_WITH(OperatorEnum.LIKE,1),
    END_WITH(OperatorEnum.LIKE,1),
    CONTAIN(OperatorEnum.LIKE,1);

    private OperatorEnum operator;
    private Integer parameterCount;

    /**
     *
     * @param operator Sql OperatorEnum
     * @param parameterCount < 0 is unknown
     */
    Relationships(OperatorEnum operator, Integer parameterCount){
        this.operator = operator;
        this.parameterCount = parameterCount;
    }

    public OperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(OperatorEnum operator) {
        this.operator = operator;
    }

    public Integer getParameterCount() {
        return parameterCount;
    }

    public void setParameterCount(Integer parameterCount) {
        this.parameterCount = parameterCount;
    }

    public String getName() {
        return this.operator.toString();
    }
}
