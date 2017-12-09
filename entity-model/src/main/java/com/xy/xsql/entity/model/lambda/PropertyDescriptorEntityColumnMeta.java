package com.xy.xsql.entity.model.lambda;

import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.entity.EntityTableMeta;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public class PropertyDescriptorEntityColumnMeta extends EntityColumnMeta<PropertyDescriptor,EntityTableMeta> {

    @Override
    public String getType() {
        return "PropertyDescriptorEntity";
    }

    @Override
    public String getParentId() {
        return null;
    }

}
