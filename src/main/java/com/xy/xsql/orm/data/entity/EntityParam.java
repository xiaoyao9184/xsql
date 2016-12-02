package com.xy.xsql.orm.data.entity;

import com.xy.xsql.orm.annotation.Relationships;
import com.xy.xsql.orm.data.sql.element.info.Param;

/**
 * SQL参数
 * Created by xiaoyao9184 on 2016/1/16.
 */
public class EntityParam extends Param implements Cloneable {

    private Relationships relationship;
    private Object[] args;


    /**
     * 是否是关联参数（需要设置参数值，即包含 ? 号）
     * @return 是/否
     */
    public boolean isNeedValue(){
        return this.valueExpression.toString().contains("?");
    }



    @Override
    public EntityColumn getColumn() {
        return (EntityColumn) column;
    }

    public Relationships getRelationship() {
        return relationship;
    }

    public Object[] getArgs() {
        return args;
    }

    /**
     * set EntityColumn
     * @param entityColumn EntityColumn
     * @return This
     */
    public EntityParam withColumn(EntityColumn entityColumn) {
        this.column = entityColumn;
        return this;
    }

    /**
     * set Relationships
     * @param relationship Relationships
     * @return This
     */
    public EntityParam withRelationship(Relationships relationship) {
        this.relationship = relationship;
        return this;
    }

    /**
     * set args
     * @param args args
     * @return This
     */
    public EntityParam withArgs(Object... args) {
        this.args = args;
        return this;
    }


    /**
     * 变为 Param
     * @return Param
     */
    public Param toParam(){
        return this;
    }


    @Override
    public EntityParam clone(){
        EntityParam result = new EntityParam()
                .withArgs(this.args)
                .withRelationship(this.relationship);

        result.withColumn(this.column);
        return result;
    }
}
