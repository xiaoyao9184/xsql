package com.xy.xsql.spring.config;

import com.xy.xsql.orm.mapping.row.FieldRowNameHandler;
import com.xy.xsql.spring.annotation.Mapping;

import java.lang.reflect.Field;

/**
 * Created by xiaoyao9184 on 2017/1/3.
 */
public class MappingFieldRowNameHandler implements FieldRowNameHandler {
    @Override
    public String handlerField(Field field) {
        Mapping column = field.getAnnotation(Mapping.class);
        if (column != null) {
            return column.value();
        }
        return null;
    }
}
