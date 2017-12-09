package com.xy.xsql.entity.model.entity;

import com.xy.xsql.entity.api.meta.ColumnMeta;
import com.xy.xsql.entity.api.meta.TableMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;

import java.util.Collection;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public abstract class EntityTableMeta<Entity,C extends EntityColumnMeta> implements TableMeta<C> {

    private Entity entity;
    private String packages;
    private String name;

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

    public Entity entity(){
        return entity;
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

}
