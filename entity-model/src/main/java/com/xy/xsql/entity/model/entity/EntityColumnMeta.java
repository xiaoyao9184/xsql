package com.xy.xsql.entity.model.entity;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public abstract class EntityColumnMeta<Entity,T extends EntityTableMeta> implements ColumnMeta<T> {

    private Entity entity;
    private String packages;
    private String name;
    //Type
    private String dbType;
    private Class javaType;
    private int length;
    private int precision;
    private int scale;
    //Constraint
    private boolean primary;
    private boolean unique;
    private boolean nullable;
    private boolean foreign;
    //Relationship
    private Class relationshipEntity;
    private RelationshipType relationshipType;

    //
    private T tableMeta;

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isForeign() {
        return foreign;
    }

    public void setForeign(boolean foreign) {
        this.foreign = foreign;
    }

    public Class getRelationshipEntity() {
        return relationshipEntity;
    }

    public void setRelationshipEntity(Class relationshipEntity) {
        this.relationshipEntity = relationshipEntity;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Entity entity(){
        return entity;
    }


    public void setJavaType(Class javaType) {
        this.javaType = javaType;
    }

    @Override
    public Class getJavaType() {
        return javaType;
    }

    @Override
    public T getTableMeta() {
        return tableMeta;
    }

    @Override
    public String getId() {
        return getPackage() + "." + getName();
    }

    @Override
    public String getPackage() {
        return packages;
    }

    @Override
    public String getName() {
        return name;
    }


    public void setTableMeta(T tableMeta) {
        this.tableMeta = tableMeta;
    }
}
