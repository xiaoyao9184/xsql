package com.xy.xsql.entity.model.lambda;

import com.xy.xsql.entity.model.entity.EntityTableMeta;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public class ClassEntityTableMeta
        extends EntityTableMeta<Class,PropertyDescriptorEntityColumnMeta> {

    private List<PropertyDescriptorEntityColumnMeta> columns;
    private Set<String> uniqueConstraints;

    @Override
    public String getType() {
        return "ClassEntity";
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public Class getJavaType() {
        return entity();
    }

    @Override
    public List<PropertyDescriptorEntityColumnMeta> getColumns() {
        return columns;
    }

    public void setColumns(List<PropertyDescriptorEntityColumnMeta> columns) {
        this.columns = columns;
    }

    public Set<String> getUniqueConstraints() {
        return uniqueConstraints;
    }

    public void setUniqueConstraints(Set<String> uniqueConstraints) {
        this.uniqueConstraints = uniqueConstraints;
    }

    //index not in this bean
//    @Override
//    public PropertyDescriptorEntityColumnMeta getColumn(String name) {
//        return null;
//    }

}
