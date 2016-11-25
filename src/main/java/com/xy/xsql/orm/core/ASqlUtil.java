package com.xy.xsql.orm.core;

import com.xy.xsql.orm.annotation.EntityTable;
import com.xy.xsql.orm.annotation.EntitySql;
import com.xy.xsql.orm.data.param.EntityParam;
import com.xy.xsql.orm.data.entity.SqlEntity;
import com.xy.xsql.orm.util.CheckUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ASql Util
 * Created by xiaoyao9184 on 2016/6/26.
 */
@SuppressWarnings("WeakerAccess")
public class ASqlUtil {

    /**
     * 根据 SqlEntity 创建 EntityParam
     * @param sqlEntityList SqlEntity List
     * @param entityParamList EntityParam List
     * @param defaultEnable Default Enable
     * @return EntityParam List
     */
    public static List<EntityParam> createFullEntityParam(List<SqlEntity> sqlEntityList, List<EntityParam> entityParamList, boolean defaultEnable){
        List<EntityParam> result = new ArrayList<>();
        //类 当前数量
        Map<Class,AtomicInteger> entityClassNowIndexMap = new HashMap<>();
        //类 EntityParam 映射
        Map<Class,List<EntityParam>> entityClassMap = new HashMap<>();
        //字段名 EntityParam 映射
        Map<String,EntityParam> columnNameMap = new HashMap<>();

        //默认
        for (SqlEntity sqlEntity: sqlEntityList) {
            EntityParam entityParam = new EntityParam();
            Class clazz = sqlEntity.getClazz();
            entityParam.setClazz(clazz);
            String name = null;
            if(sqlEntity.isCoreBean()){
                entityParam.setUse(true);
            }else{
                entityParam.setUse(defaultEnable);
                name = sqlEntity.getSqlColumn().getName();
            }
            //in ColumnMap
            entityParam.setBindColumnName(name);
            columnNameMap.put(name,entityParam);
            //in ClassMap
            if(entityClassMap.containsKey(clazz)){
                entityClassMap.get(clazz).add(entityParam);
            }else{
                List<EntityParam> list = new ArrayList<>();
                list.add(entityParam);
                entityClassMap.put(clazz,list);
            }
            entityClassNowIndexMap.put(clazz,new AtomicInteger());

            result.add(entityParam);
        }

        //自定义修改
        for (EntityParam entityParam: entityParamList) {
            if(entityParam.isUseColumn()){
                if(columnNameMap.containsKey(entityParam.getBindColumnName())){
                    EntityParam indexEntityParam = columnNameMap.get(entityParam.getBindColumnName());
                    int index = result.indexOf(indexEntityParam);
                    result.set(index,entityParam);
                }else{
                    throw new UnsupportedOperationException("参数非法，未找到与 " + entityParam.getBindColumnName() + " 相关的实体，可能名称错误！");
                }
            }else if(entityParam.isUseClass()){
                if(entityClassMap.containsKey(entityParam.getClazz())){
                    AtomicInteger classIndex = entityClassNowIndexMap.get(entityParam.getClazz());
                    EntityParam indexEntityParam = entityClassMap.get(entityParam.getClazz()).get(classIndex.get());
                    int index = result.indexOf(indexEntityParam);
                    result.set(index,entityParam);
                    classIndex.getAndIncrement();
                }else{
                    throw new UnsupportedOperationException("参数非法，未找到与 " + entityParam.getClazz() + " 相关的实体，可能名称错误！");
                }
            }else{
                //核心实体
                entityParam.setUse(true);
                result.set(0,entityParam);
            }
        }

        return result;
    }


    /**
     * 获取参数
     * @param param EntityParam List
     * @param removeNull 是/否删除NULL参数
     * @return Object Array
     */
    public static Object[] getArgsByParam(List<EntityParam> param, boolean removeNull) {
        List<Object> list = new ArrayList<>();
        for (EntityParam kv: param) {
            if(kv.isUseArgs()){
                if(removeNull){
                    list.addAll(kv.toNoNullArgsList());
                }else{
                    list.addAll(kv.toArgsList());
                }
            }
        }
        return list.toArray();
    }

    /**
     * 通过类生成SQL语句
     * @param clazz
     * @param args
     * @return SQL
     */
    public static String createSqlByClass(Class clazz, Object...args) throws Exception {
        EntitySql entitySql = (EntitySql) clazz.getAnnotation(EntitySql.class);
        if(entitySql != null){
            return createSqlByVo(entitySql,clazz,args);
        }

        EntityTable entityTable = (EntityTable) clazz.getAnnotation(EntityTable.class);
        if(entityTable != null){
            return createSqlByPo(entityTable,clazz,args);
        }

        return null;
    }

    /**
     * 通过VO标注生成SQL语句
     * @param entitySql 标注
     * @param clazz SQLVO
     * @param args NULL或空 不使用参数
     * @return SQL
     */
    public static String createSqlByVo(EntitySql entitySql, Class clazz, Object...args) throws Exception {
        String sql;
        if (CheckUtil.isNullOrEmpty(entitySql.sql())){
            EntityTable entityTable = (EntityTable) clazz.getAnnotation(EntityTable.class);
            if(entityTable != null){
                sql = "SELECT * FROM " + entityTable.name();
            }else{
                throw new UnsupportedOperationException(clazz.getName() + " 没有表名，无法生成SQL！");
            }
        }
        sql = entitySql.sql();

        if(entitySql.args().length == 0){
            // TODO: 2016/6/21 空参数，不代表没有参数
        }else if(entitySql.args().length >= args.length){
            int index = 0;
            for (Object arg: args) {
                if(CheckUtil.isNull(arg)){
                }else if (arg instanceof String &&
                        CheckUtil.isNullOrEmpty((String)arg)) {
                }else{
                    sql = sql + entitySql.args()[index];
                }
                index++;
            }
        }else{
            throw new UnsupportedOperationException(clazz.getName() + " 实际参数数量大于标注参数数量，无法生成SQL！");
        }

        return sql;
    }

    /**
     * 通过PO标注生成SQL语句
     * @param entityTable
     * @param clazz
     * @param args
     * @return SQL
     */
    public static String createSqlByPo(EntityTable entityTable, Class clazz, Object...args) throws Exception {
        A2Sql aSql = new A2Sql(clazz);
        List<EntityParam> entityParams = new ArrayList<>();
        entityParams.add(new EntityParam(args));
        return aSql.createFullSelectSql(entityParams);
    }

    /**
     * 获取有效参数（NULL或空将被移除）
     * @param args 参数
     * @return 有效参数
     */
    public static Object[] createSqlUseArgs(Object... args){
        List<Object> list = new ArrayList<>();
        for (Object arg: args) {
            if(CheckUtil.isNull(arg)){
            }else if (arg instanceof String &&
                    CheckUtil.isNullOrEmpty((String)arg)) {
            }else{
                list.add(arg);
            }
        }
        return list.toArray();
    }

}
