package com.xy.xsql.entity.core.dialect.type;

import com.xy.xsql.core.mapper.IndexClassMapper;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.core.provider.SuppliesOnceProvider;
import com.xy.xsql.entity.api.dialect.type.ETypeMapper;
import com.xy.xsql.entity.api.dialect.type.EntityTypeMapperRegistrar;
import com.xy.xsql.entity.api.dialect.type.JavaTypeMapper;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/9/23
 */
public class JavaTypeMapperProvider
        implements SuppliesOnceProvider<JavaTypeMapper,Class<?>> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Integer PRIORITY_HIGH = 1000;
    private final Integer PRIORITY_NORMAL = 0;
    private final Integer PRIORITY_LOW = -1000;

    private JavaTypeMapper defaultMapper;
    private IndexClassMapper<Class<?>,JavaTypeMapper> mapMapper;
    private SourceTargetMapper<Class<?>,Integer> mapPriority;

    public JavaTypeMapperProvider(JavaTypeMapper defaultMapper, String defaultPackage){
        this.defaultMapper = defaultMapper;
        this.mapMapper = new IndexClassMapper<>();
        this.mapPriority = new SourceTargetMapper<>();

        if(defaultPackage != null){
            this.initRegistrar(defaultPackage);
        }
    }

    public void initRegistrar(String defaultPackage){
        new Reflections(defaultPackage)
                .getSubTypesOf(EntityTypeMapperRegistrar.class)
                .stream()
                .peek(clazz -> logger.debug("Provider find {} with interface {}.",clazz,EntityTypeMapperRegistrar.class))
                .forEach(this::registeredInterface);

        new Reflections(defaultPackage)
                .getTypesAnnotatedWith(ETypeMapper.class)
                .stream()
                .peek(clazz -> logger.debug("Provider find {} with annotation {}.",clazz,ETypeMapper.class))
                .forEach(this::registeredAnnotation);
    }

    private void registeredInterface(Class<?> clazz){
        Class<?> entity = Stream.of(clazz.getGenericInterfaces())
                .filter(type -> type instanceof ParameterizedType)
                .map(type -> (ParameterizedType)type)
                .filter(parameterizedType -> parameterizedType.getRawType().equals(EntityTypeMapperRegistrar.class))
                .map(parameterizedType -> parameterizedType.getActualTypeArguments()[0])
                .map(type -> (Class)type)
                .findAny()
                .orElse(null);
        if(entity == null){
            logger.warn("This class {} is not mapper with empty interface generic param!",clazz);
            return;
        }

        Stream.of(clazz.getInterfaces())
                .filter(i -> i.equals(JavaTypeMapper.class))
                .findAny()
                .ifPresent(i -> registered(clazz, Collections.singletonList(entity), PRIORITY_HIGH));
    }

    private void registeredAnnotation(Class<?> clazz){
        ETypeMapper eTypeMapper = clazz.getAnnotation(ETypeMapper.class);
        Class mapper = null;
        Map<Class<?>,Integer> entity = null;
        if(eTypeMapper.mapper() == Void.class) {
            Optional<Class<?>> optionalMapper = Stream.of(clazz.getInterfaces())
                    .filter(i -> i.equals(JavaTypeMapper.class))
                    .findAny();
            if(optionalMapper.isPresent()){
                logger.debug("This class {} is mapper!",clazz);
                mapper = clazz;

                if(eTypeMapper.packages().length == 0 &&
                        eTypeMapper.entities().length == 0) {
                    logger.warn("This mapper {} is default mapper!", clazz);
                    if (defaultMapper == null) {
                        registered(mapper);
                    } else {
                        logger.warn("The default mapper has already been set {}!", this.defaultMapper.getClass());
                    }
                }
                return;
            }else{
                logger.warn("This class {} is not mapper with empty annotation filed mapper()!",clazz);
                return;
            }
        }else{
            mapper = eTypeMapper.mapper();
        }
        if(eTypeMapper.entities().length == 0 &&
                eTypeMapper.packages().length == 0){
            logger.debug("This class {} is entities!",clazz);
            entity = Collections.singletonMap(clazz,PRIORITY_HIGH);
        }

        if(eTypeMapper.entities().length != 0){
            entity = Stream.of(eTypeMapper.entities()).collect(Collectors.toMap(e -> e, e -> PRIORITY_NORMAL));
        }

        if(eTypeMapper.packages().length != 0){
            Map<Class<?>,Integer> entities = Stream.of(eTypeMapper.packages())
                    .map(packages -> new Reflections(packages,new SubTypesScanner(false))
                            .getSubTypesOf(Object.class)
                            .stream()
                            .peek(e -> logger.debug("Provider find {} in {}.",e,packages)))
                    .flatMap(eStream -> eStream)
                    .collect(Collectors.toMap(e -> e, e -> PRIORITY_LOW));
            if(entity != null){
                entity.putAll(entities);
            }else{
                entity = entities;
            }
        }

        if(entity == null){
            logger.warn("This class {} cant find any entities!",clazz);
            return;
        }

        Class finalMapper = mapper;
        entity.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .forEach(kv -> registered(finalMapper,kv.getKey(),kv.getValue()));
    }


    private void registered(JavaTypeMapper javaTypeMapper, Class<?> entity, Integer priority){
        if(this.mapMapper.check(entity)){
            Integer current = 0;
            if(this.mapPriority.checkLeft(entity)){
                current = this.mapPriority.getByLeft(entity);
            }
            if(current >= priority){
                logger.error("There is already a higher priority {} mapping relationship {}!", current, entity);
                return;
            }
            this.mapMapper.map(entity, javaTypeMapper);
        }else{
            this.mapMapper.map(entity, javaTypeMapper);
        }
    }

    private void registered(Class mapper, Class<?> entity, Integer priority){
        try {
            JavaTypeMapper javaTypeMapper = (JavaTypeMapper) mapper.newInstance();
            registered(javaTypeMapper,entity,priority);
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Cant instance mapper {}!", mapper);
            e.printStackTrace();
        }
    }

    private void registered(Class mapper, List<Class<?>> entity, Integer priority){
        try {
            JavaTypeMapper javaTypeMapper = (JavaTypeMapper) mapper.newInstance();
            entity.forEach(e -> registered(javaTypeMapper,e,priority));
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Cant instance mapper {}!", mapper);
            e.printStackTrace();
        }
    }

    public void registered(Class mapper, List<Class<?>> entity){
        registered(mapper,entity,PRIORITY_HIGH);
    }

    /**
     *
     * @param mapper default mapper
     */
    private void registered(Class mapper){
        try {
            this.defaultMapper = (JavaTypeMapper) mapper.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Cant instance mapper {}!", mapper);
            e.printStackTrace();
        }
    }

    @Override
    public JavaTypeMapper provide() {
        return defaultMapper;
    }

    @Override
    public JavaTypeMapper provide(Class<?> entity) {
        if(mapMapper.check(entity)){
            return mapMapper.get(entity);
        }
        return provide();
    }

}
