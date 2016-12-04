package com.xy.xsql.orm.data.cache;

import com.xy.xsql.orm.core.entity.template.AnnotationEntityTemplateBuilder;
import com.xy.xsql.orm.core.entity.sql.BaseEntitySqlBuilder;
import com.xy.xsql.orm.core.entity.sql.EntitySqlBuilder;
import com.xy.xsql.orm.data.config.ASqlConfig;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 模型转SQL
 * Created by xiaoyao9184 on 2016/1/13.
 */
@SuppressWarnings({"StringBufferReplaceableByString", "JavaDoc", "unused", "StatementWithEmptyBody", "WeakerAccess"})
public class ASqlCache {

/*x
 * Fields
 */

    private static Map<Class, Object> aSqlMap2 = new ConcurrentHashMap<>();

    /**
     * 创建ASql
     * @param config 配置
     * @param aSqlClass ASql泛型返回类
     * @param <T> ASql泛型
     * @return ASql
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    public static <T extends EntitySqlBuilder> T create(ASqlConfig config, Class<T> aSqlClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if(ASqlCache.aSqlMap2.containsKey(config.getClazz())){
            return (T) ASqlCache.aSqlMap2.get(config.getClazz());
        }else{
            T aSql = aSqlClass.getDeclaredConstructor(ASqlConfig.class).newInstance(config);
            ASqlCache.aSqlMap2.put(config.getClazz(),aSql);
            return aSql;
        }
    }

    /**
     * 创建ASql
     * @param clazz 类
     * @param aSqlClass ASql泛型返回类
     * @param <T> ASql泛型
     * @return ASql
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    public static <T extends EntitySqlBuilder> T create(Class clazz, Class<T> aSqlClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if(ASqlCache.aSqlMap2.containsKey(clazz)){
            return (T) ASqlCache.aSqlMap2.get(clazz);
        }else{
            T aSql = aSqlClass.getDeclaredConstructor(Class.class).newInstance(clazz);
            ASqlCache.aSqlMap2.put(clazz,aSql);
            return aSql;
        }
    }

    /**
     * 创建ASql
     * @param clazz 类
     * @return ASql
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static EntitySqlBuilder create(Class clazz) throws IllegalAccessException, InstantiationException {
        if(ASqlCache.aSqlMap2.containsKey(clazz)){
            return (EntitySqlBuilder) ASqlCache.aSqlMap2.get(clazz);
        }else{
            EntitySqlBuilder aSql = new BaseEntitySqlBuilder()
                    .cacheTemplate(new AnnotationEntityTemplateBuilder().build(clazz));
            ASqlCache.aSqlMap2.put(clazz,aSql);
            return aSql;
        }
    }

}
