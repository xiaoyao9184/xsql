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




    @Override
    public EntityColumn getColumn() {
        return (EntityColumn) column;
    }

    public Relationships getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationships relationship) {
        this.relationship = relationship;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
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
     * get first arg
     * @return arg
     */
    public Object getArg() {
        return this.args == null ? null : this.args[0];
    }

    /**
     * get args count
     * @return count
     */
    public int getArgsCount(){
        return this.args == null ? 0 : this.args.length;
    }

    /**
     * placeholder must need arg value
     * @return True/False
     */
    public boolean isNeedValue(){
        return this.valueExpression == null ||
                this.valueExpression.toString().contains("?");
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
