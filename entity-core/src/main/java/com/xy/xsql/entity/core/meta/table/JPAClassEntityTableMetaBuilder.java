package com.xy.xsql.entity.core.meta.table;

import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import com.xy.xsql.entity.core.dialect.none.AllVarcharTypeMapper;
import com.xy.xsql.entity.core.meta.PropertyDescriptorAnnotationProvider;
import com.xy.xsql.entity.model.entity.RelationshipType;
import com.xy.xsql.entity.model.lambda.ClassEntityTableMeta;
import com.xy.xsql.entity.model.lambda.PropertyDescriptorEntityColumnMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JPAClassEntityTableMetaBuilder
 * build Meta by class with JPA Annotation
 * Created by xiaoyao9184 on 2016/10/15.
 */
public class JPAClassEntityTableMetaBuilder
        implements EntityTableMetaBuilder<JPAClassEntityTableMetaBuilder,JavaTypeMapper,Class,ClassEntityTableMeta> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaTypeMapper javaTypeMapper;
    private BeanInfo beanInfo;
    private SourceTargetMapper<PropertyDescriptor,Field> propertyDescriptorFieldMapper;

    @Override
    public ClassEntityTableMeta build(Class clazz) {
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            logger.error("Cant get bean info",e);
            e.printStackTrace();
        }
        propertyDescriptorFieldMapper = new SourceTargetMapper<>();
        if(javaTypeMapper == null){
            javaTypeMapper = new AllVarcharTypeMapper();
        }

        ClassEntityTableMeta meta = new ClassEntityTableMeta();
        meta.setEntity(clazz);
        buildTable(meta);
        buildColumn(meta);
        return meta;
    }

    @Override
    public JPAClassEntityTableMetaBuilder config(JavaTypeMapper javaTypeMapper) {
        this.javaTypeMapper = javaTypeMapper;
        return this;
    }



    private JPAClassEntityTableMetaBuilder buildTable(ClassEntityTableMeta meta){
        Table table = (Table) meta.getEntity().getAnnotation(Table.class);
        String name = table.name();
        logger.debug("Table name is {}", name);
        meta.setName(name);
        meta.setPackages(table.schema());

        meta.setUniqueConstraints(Stream.of(table.uniqueConstraints())
                .flatMap(uniqueConstraint -> Stream.of(uniqueConstraint.columnNames()))
                .collect(Collectors.toSet()));

        return this;
    }

    private JPAClassEntityTableMetaBuilder buildColumn(ClassEntityTableMeta meta){
        List<PropertyDescriptorEntityColumnMeta> columnMetaSet = Stream.of(beanInfo.getPropertyDescriptors())
                .map(propertyDescriptor -> {
                    String name = propertyDescriptor.getName();
                    Method getterMethod = propertyDescriptor.getReadMethod();
                    Method setterMethod = propertyDescriptor.getWriteMethod();
                    if(getterMethod == null || setterMethod == null){
                        logger.warn("Property '{}' missing getter or setter method in {}!",name,meta.getEntity());
                        return null;
                    }

                    Optional<Column> columnOptional = new PropertyDescriptorAnnotationProvider(propertyDescriptor)
                            .withFieldGetter((pd) -> propertyDescriptorFieldMapper.getByLeft(pd))
                            .withFieldSetter((pd,f) -> propertyDescriptorFieldMapper.map(pd, f))
                            .provide(Column.class);

                    if(!columnOptional.isPresent()){
                        logger.debug("Property '{}' missing JPA annotation:Column in {}!",name,meta.getEntity());
                        return null;
                    }

                    boolean isPrimary = new PropertyDescriptorAnnotationProvider(propertyDescriptor)
                            .withFieldGetter((pd) -> propertyDescriptorFieldMapper.getByLeft(pd))
                            .withFieldSetter((pd,f) -> propertyDescriptorFieldMapper.map(pd, f))
                            .provide(Id.class)
                            .isPresent();

                    Column column = columnOptional.get();

                    if(!column.name().equals("")){
                        name = column.name();
                    }

                    PropertyDescriptorEntityColumnMeta columnMeta = new PropertyDescriptorEntityColumnMeta();
                    columnMeta.setEntity(propertyDescriptor);
                    columnMeta.setName(name);

                    //Type
                    columnMeta.setDbType(javaTypeMapper.mapType(propertyDescriptor.getPropertyType()));
                    columnMeta.setJavaType(propertyDescriptor.getPropertyType());
                    columnMeta.setLength(column.length());
                    columnMeta.setPrecision(column.precision());
                    columnMeta.setScale(column.scale());
                    //Constraint
                    columnMeta.setPrimary(isPrimary);
                    columnMeta.setUnique(column.unique());
                    columnMeta.setNullable(column.nullable());
                    //Relationship(Foreign)
                    buildRelationshipEntity(
                            propertyDescriptor,
                            columnMeta::setRelationshipEntity,
                            columnMeta::setRelationshipType)
                            .ifPresent(isForeignNullable -> {
                                columnMeta.setForeign(true);
                                columnMeta.setNullable(isForeignNullable);
                            });

                    columnMeta.setJavaType(propertyDescriptor.getPropertyType());
                    columnMeta.setTableMeta(meta);
                    return columnMeta;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        //
        meta.setColumns(columnMetaSet);
        return this;
    }

    /**
     * Build Relationship
     * @param propertyDescriptor PropertyDescriptor
     * @param relationshipEntitySetter RelationshipEntity Setter
     * @param relationshipTypeSetter RelationshipType Setter
     * @return Use Relationship/not use
     */
    private Optional<Boolean> buildRelationshipEntity(PropertyDescriptor propertyDescriptor,
                                                      Setter<Class> relationshipEntitySetter,
                                                      Setter<RelationshipType> relationshipTypeSetter) {
        Class relationshipEntity;
        RelationshipType relationshipType;

        /*
          one column store one ID
          it must foreign
         */
        Optional<OneToOne> oneToOneOptional = new PropertyDescriptorAnnotationProvider(propertyDescriptor)
                .withFieldGetter((pd) -> propertyDescriptorFieldMapper.getByLeft(pd))
                .withFieldSetter((pd,f) -> propertyDescriptorFieldMapper.map(pd, f))
                .provide(OneToOne.class);
        if(oneToOneOptional.isPresent()){
            relationshipType = RelationshipType.ONE_TO_ONE;
            relationshipEntity = oneToOneOptional.get().targetEntity();
            relationshipTypeSetter.set(relationshipType);
            relationshipEntitySetter.set(relationshipEntity);
            return Optional.of(oneToOneOptional.get().optional());
        }

        /*
          multiple columns store one ID
          it must foreign
         */
        Optional<ManyToOne> manyToOneOptional = new PropertyDescriptorAnnotationProvider(propertyDescriptor)
                .withFieldGetter((pd) -> propertyDescriptorFieldMapper.getByLeft(pd))
                .withFieldSetter((pd,f) -> propertyDescriptorFieldMapper.map(pd, f))
                .provide(ManyToOne.class);
        if(manyToOneOptional.isPresent()){
            relationshipType = RelationshipType.MANY_TO_ONE;
            relationshipEntity = manyToOneOptional.get().targetEntity();
            relationshipEntitySetter.set(relationshipEntity);
            relationshipTypeSetter.set(relationshipType);
            return Optional.of(manyToOneOptional.get().optional());
        }

        /*
          Maybe one column store multiple IDs
          no foreign
         */
        Optional<OneToMany> oneToManyOptional = new PropertyDescriptorAnnotationProvider(propertyDescriptor)
                .withFieldGetter((pd) -> propertyDescriptorFieldMapper.getByLeft(pd))
                .withFieldSetter((pd,f) -> propertyDescriptorFieldMapper.map(pd, f))
                .provide(OneToMany.class);
        if(oneToManyOptional.isPresent()){
            relationshipType = RelationshipType.ONE_TO_MANY;
            relationshipEntity = oneToManyOptional.get().targetEntity();
            relationshipEntitySetter.set(relationshipEntity);
            relationshipTypeSetter.set(relationshipType);
            return Optional.empty();
        }

        /*
          Maybe one column store multiple IDs
          no foreign
         */
        Optional<ManyToMany> manyToManyOptional = new PropertyDescriptorAnnotationProvider(propertyDescriptor)
                .withFieldGetter((pd) -> propertyDescriptorFieldMapper.getByLeft(pd))
                .withFieldSetter((pd,f) -> propertyDescriptorFieldMapper.map(pd, f))
                .provide(ManyToMany.class);
        if(manyToManyOptional.isPresent()){
            relationshipType = RelationshipType.MANY_TO_MANY;
            relationshipEntity = manyToManyOptional.get().targetEntity();
            relationshipEntitySetter.set(relationshipEntity);
            relationshipTypeSetter.set(relationshipType);
            return Optional.empty();
        }

        return Optional.empty();
    }

    /**
     * Check Class is JPA Entity
     * @param clazz Entity Class
     * @return support/not support
     */
    public static boolean checkSupport(Class clazz) {
        Predicate<Class> predicate = (c) ->  c.getAnnotation(Entity.class) != null;
        predicate.and((c) -> c.getAnnotation(Table.class) != null);
        predicate.and((c) -> c.getAnnotation(Embeddable.class) == null);

        return predicate.test(clazz);
    }

}
