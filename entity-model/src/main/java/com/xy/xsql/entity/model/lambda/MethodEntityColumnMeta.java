package com.xy.xsql.entity.model.lambda;

import com.xy.xsql.entity.model.entity.EntityColumnMeta;
import com.xy.xsql.entity.model.entity.EntityTableMeta;

import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/9/24
 */
public class MethodEntityColumnMeta extends EntityColumnMeta<Method,EntityTableMeta> {

    @Override
    public String getType() {
        return "MethodEntity";
    }

    @Override
    public String getParentId() {
        return null;
    }

}
