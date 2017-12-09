package com.xy.xsql.entity.model.lambda;

import com.xy.xsql.entity.model.entity.EntityTableMeta;
import com.xy.xsql.entity.model.definition.RelationshipClass;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public class RelationshipEntityTableMeta extends EntityTableMeta<RelationshipClass,MethodEntityColumnMeta> {

    private Map<String,MethodEntityColumnMeta> nameEntityColumnMetaMap;
    private Map<Method,MethodEntityColumnMeta> methodEntityColumnMetaMap;

    public Map<String, MethodEntityColumnMeta> getNameEntityColumnMetaMap() {
        return nameEntityColumnMetaMap;
    }

    public void setNameEntityColumnMetaMap(Map<String, MethodEntityColumnMeta> nameEntityColumnMetaMap) {
        this.nameEntityColumnMetaMap = nameEntityColumnMetaMap;
    }

    public Map<Method, MethodEntityColumnMeta> getMethodEntityColumnMetaMap() {
        return methodEntityColumnMetaMap;
    }

    public void setMethodEntityColumnMetaMap(Map<Method, MethodEntityColumnMeta> methodEntityColumnMetaMap) {
        this.methodEntityColumnMetaMap = methodEntityColumnMetaMap;
    }

    @Override
    public Class getJavaType() {
        return RelationshipClass.class;
    }

    @Override
    public String getType() {
        return "RelationshipEntity";
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public Collection<MethodEntityColumnMeta> getColumns() {
        return nameEntityColumnMetaMap.values();
    }

    public MethodEntityColumnMeta getColumn(String name) {
        return nameEntityColumnMetaMap.get(name);
    }

    public MethodEntityColumnMeta getColumn(Method method) {
        return methodEntityColumnMetaMap.get(method);
    }

}
