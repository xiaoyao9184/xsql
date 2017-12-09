package com.xy.xsql.entity.core.meta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Created by xiaoyao9184 on 2017/10/1
 */
public class PropertyDescriptorAnnotationProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PropertyDescriptor propertyDescriptor;
    private Function<PropertyDescriptor,Field> getFieldFunction;
    private BiConsumer<PropertyDescriptor,Field> propertyDescriptorFieldBiConsumer;

    public PropertyDescriptorAnnotationProvider(PropertyDescriptor propertyDescriptor){
        this.propertyDescriptor = propertyDescriptor;
    }

    public PropertyDescriptorAnnotationProvider withFieldGetter(Function<PropertyDescriptor,Field> getFieldFunction){
        this.getFieldFunction = getFieldFunction;
        return this;
    }

    public PropertyDescriptorAnnotationProvider withFieldSetter(BiConsumer<PropertyDescriptor,Field> propertyDescriptorFieldBiConsumer){
        this.propertyDescriptorFieldBiConsumer = propertyDescriptorFieldBiConsumer;
        return this;
    }

    public <PRODUCT extends Annotation> Optional<PRODUCT> provide(Class<PRODUCT> clazz) {
        PRODUCT product = propertyDescriptor.getReadMethod().getAnnotation(clazz);
        if(product == null) {
            product = propertyDescriptor.getWriteMethod().getAnnotation(clazz);
        }
        if(product == null) {
            Field f = Optional.of(getFieldFunction)
                    .map(getFieldFunction -> getFieldFunction.apply(propertyDescriptor))
                    .orElseGet(() -> {
                        try {
                            Class propertyClazz = propertyDescriptor.getWriteMethod().getDeclaringClass();
                            Field field = propertyClazz.getDeclaredField(propertyDescriptor.getName());
                            Optional.of(propertyDescriptorFieldBiConsumer)
                                    .ifPresent(consumer -> consumer.accept(propertyDescriptor,field));
                            return field;
                        } catch (NoSuchFieldException e) {
                            logger.warn("Property '{}' missing field in {}!",propertyDescriptor.getName(),clazz);
                            return null;
                        }
                    });
            product = Optional.ofNullable(f)
                    .map(field -> field.getAnnotation(clazz))
                    .orElse(null);
        }

        return Optional.ofNullable(product);
    }
}
