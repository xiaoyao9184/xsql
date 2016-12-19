package com.xy.xsql.orm.mapping.row;

import java.lang.reflect.Field;

/**
 * Created by xiaoyao9184 on 2016/12/19.
 */
public interface FieldRowNameHandler {

    /**
     * Handler Field to Row Name
     * @param field Field
     * @return Row Name
     */
    String handlerField(Field field);
}
