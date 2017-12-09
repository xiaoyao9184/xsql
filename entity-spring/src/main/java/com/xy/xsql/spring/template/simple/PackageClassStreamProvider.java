package com.xy.xsql.spring.template.simple;

import com.xy.xsql.core.provider.SuppliesStreamProvider;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/9/22
 */
public class PackageClassStreamProvider implements SuppliesStreamProvider<Class<?>,String> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Consumer<Class<?>> consumer;
    private Set<Class<?>> cache;

    @Override
    public boolean provide(Consumer<Class<?>> consumer) {
        if(this.consumer != null){
            logger.warn("Already has a consumer!");
            return false;
        }
        this.consumer = consumer;
        //
        if(cache != null && !cache.isEmpty()){
            logger.debug("Provide cache product!");
            cache.forEach(consumer);
        }
        return true;
    }

    @Override
    public boolean provide(String packages) {
        if(this.consumer == null){
            logger.warn("Not provide consumer, cache it!");
            cache = new Reflections(packages)
                    .getSubTypesOf(Object.class)
                    .stream()
                    .peek(clazz -> logger.debug("Provider find {} in {}.",clazz,packages))
                    .collect(Collectors.toSet());
            return true;
        }
        new Reflections(packages)
                .getSubTypesOf(Object.class)
                .stream()
                .peek(clazz -> logger.debug("Provider find {} in {}.",clazz,packages))
                .forEach(consumer);
        return true;
    }

}
