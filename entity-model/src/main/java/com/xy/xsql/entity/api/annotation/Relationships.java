package com.xy.xsql.entity.api.annotation;

/**
 * Created by xiaoyao9184 on 2016/11/25.
 */
public enum Relationships {
    EQUAL("=",1),
    NOT_EQUAL("<>",1),
    GREATER(">",1),
    GREATER_EQUAL(">=", 1),
    LESS("<",1),
    LESS_EQUAL("<=",1),

    BETWEEN(2),

    IN(-1),

    START_WITH("LIKE",1),
    END_WITH("LIKE",1),
    CONTAIN("LIKE",1);

    private String keyword;
    private Integer parameterCount;

    /**
     *
     * @param parameterCount < 0 is unknown
     */
    Relationships(Integer parameterCount){
        this.keyword = name().toUpperCase();
        this.parameterCount = parameterCount;
    }


    Relationships(String keyword, Integer parameterCount){
        this.keyword = keyword;
        this.parameterCount = parameterCount;
    }

    public Integer getParameterCount() {
        return parameterCount;
    }

    public void setParameterCount(Integer parameterCount) {
        this.parameterCount = parameterCount;
    }

    public String getKeyword() {
        return keyword;
    }
}
