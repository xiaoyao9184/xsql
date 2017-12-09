package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.model.lambda.MethodEntityColumnMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * EAnnotationEntityInfoBuilder
 * core EntityInfo by class with Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class BaseClassEntityTableMetaBuilder
        implements EntityTableMetaBuilder<BaseClassEntityTableMetaBuilder,JavaTypeMapper,Class,ClassEntityTableMeta> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaTypeMapper javaTypeMapper;

    public static boolean checkSupport(Class source) {
        return source.getDeclaredFields().length != 0;
    }

    @Override
    public BaseClassEntityTableMetaBuilder config(JavaTypeMapper javaTypeMapper) {
        this.javaTypeMapper = javaTypeMapper;
        return this;
    }

    @Override
    public ClassEntityTableMeta build(Class aClass) {
        ClassEntityTableMeta meta = new ClassEntityTableMeta();
        meta.setEntity(aClass);
        buildTable(meta)
                .buildColumn(meta);
        return meta;
    }

    private BaseClassEntityTableMetaBuilder buildTable(ClassEntityTableMeta meta){
        String name = meta.getEntity().getSimpleName();
        name = toDbName(name);
        logger.debug("Table name is {}", name);
        meta.setName(name);
        return this;
    }

    private BaseClassEntityTableMetaBuilder buildColumn(ClassEntityTableMeta meta){
        List<PropertyDescriptorEntityColumnMeta> columnMetaSet = Stream.of(meta.getEntity().getDeclaredFields())
                .map(field -> {
//                    String name = toDbName(field.getName());
                    MethodEntityColumnMeta columnMeta = new MethodEntityColumnMeta();
//                    columnMeta.setEntity(method);
                    columnMeta.setName(field.getName());
                    return columnMeta;
                }).map(m -> {
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(m.getName(),meta.getEntity());
                        PropertyDescriptorEntityColumnMeta columnMeta = new PropertyDescriptorEntityColumnMeta();
                        columnMeta.setEntity(propertyDescriptor);
                        columnMeta.setName(toDbName(m.getName()));
        //                        columnMeta.setLength(column.length());
        //                        columnMeta.setPrecision(column.precision());
        //                        columnMeta.setScale(column.scale());
        //                        columnMeta.setLength(column.length());

                        columnMeta.setJavaType(propertyDescriptor.getPropertyType());
                        columnMeta.setTableMeta(meta);
                        return columnMeta;
                    } catch (IntrospectionException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

//        List<PropertyDescriptorEntityColumnMeta> columnMetaSet = Stream.of(meta.getEntity().getDeclaredMethods())
//                .filter(method -> !method.getReturnType().equals(Void.class))
//                .filter(method -> method.getName().startsWith("get") || method.getName().startsWith("is"))
//                .map(method -> {
//                    String name = toDbName(method.getName());
//                    MethodEntityColumnMeta columnMeta = new MethodEntityColumnMeta();
//                    columnMeta.setEntity(method);
//                    columnMeta.setName(name);
//                    return columnMeta;
//                })
//                .map(m -> {
//                    try {
//                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(m.getName(),meta.getEntity());
//                        PropertyDescriptorEntityColumnMeta columnMeta = new PropertyDescriptorEntityColumnMeta();
//                        columnMeta.setEntity(propertyDescriptor);
//                        columnMeta.setName(m.getName());
////                        columnMeta.setLength(column.length());
////                        columnMeta.setPrecision(column.precision());
////                        columnMeta.setScale(column.scale());
////                        columnMeta.setLength(column.length());
//
//                        columnMeta.setJavaType(propertyDescriptor.getPropertyType());
//                        columnMeta.setTableMeta(meta);
//                        return columnMeta;
//                    } catch (IntrospectionException e) {
//                        return null;
//                    }
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());

        columnMetaSet.stream()
                .findFirst()
                .ifPresent(columnMeta -> columnMeta.setPrimary(true));

        meta.setColumns(columnMetaSet);
//
//        meta.setMethodEntityColumnMetaMap(columnMetaSet
//                .stream()
//                .collect(Collectors.toMap(
//                        EntityColumnMeta::getEntity,
//                        UnaryOperator.identity()
//                )));
//        meta.setNameEntityColumnMetaMap(columnMetaSet
//                .stream()
//                .collect(Collectors.toMap(
//                        EntityColumnMeta::getName,
//                        UnaryOperator.identity()
//                )));
        return this;
    }


    public static String toDbName(String name) {
        return uncapitalize(toSnakeCase(banishGetterSetters(name)));
    }

    private static String banishGetterSetters(String name) {
        return name.replaceAll("^(get|set|is)", "");
    }

    public static String uncapitalize(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public static String toSnakeCase(String s) {
        return s.replaceAll("([a-z])([A-Z])","$1_$2").toLowerCase();
    }

}
